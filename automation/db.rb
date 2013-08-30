#!/usr/bin/env ruby
require "sqlite3"

class Db
  def initialize(args)
    ret = `adb shell "run-as com.kleetus.trackanything chmod 666 databases/Tracker"`
    ret = `adb pull /data/data/com.kleetus.trackanything/databases/Tracker .`
  end
  
  def load_tracker
    @db = SQLite3::Database.new "Tracker"         
    count=0
    20.times do
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
    ret = `adb push Tracker /data/data/com.kleetus.trackanything/databases/Tracker`
  end
end



Db.new(ARGV).load_tracker.show_rows.write_back





