#!/usr/bin/env ruby
require "sqlite3"

class Db
  def initialize(args)
    @device = args.length > 0 ? "-s #{args[0]}" : ""
    ret = `adb #{@device} shell "run-as com.kleetus.trackanything chmod 666 databases/Tracker"`
    ret = `adb #{@device} pull /data/data/com.kleetus.trackanything/databases/Tracker .`
  end
  
  def load_tracker
    @db = SQLite3::Database.new "Tracker"         
    delete_trackers
    count=1
    5.times do
      @db.execute "insert into trackers (name, date_created) values ( ?, ? ) ", ["tracker#{count}", "100#{count}"] 
      count+=1
    end
    self
  end

  def show_rows
    @db.execute("select * from trackers") do |r|
      p r
    end
    self
  end
  
  def write_back
    ret = `adb #{@device} push Tracker /data/data/com.kleetus.trackanything/databases/Tracker`
  end

  def delete_trackers
    @db.execute("delete from trackers")
    self
  end
  
  def delete_extra_files
    ret = `adb #{@device} shell "run-as com.kleetus.trackanything rm databases/Tracker-shm"`
    ret = `adb #{@device} shell "run-as com.kleetus.trackanything rm databases/Tracker-wal"`
    self
  end
end



Db.new(ARGV).delete_extra_files.load_tracker.show_rows.write_back





