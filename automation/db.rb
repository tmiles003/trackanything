#!/usr/bin/env ruby
require "sqlite3"

class Db
  def initialize(args)
    ret = `adb shell "run-as com.kleetus.trackanything chmod 666 databases/Tracker"`
    
    if(args == "push") 
      #tell the sqlite browser to commit its changes and close first
      ret = `adb push Tracker /data/data/com.kleetus.trackanything/databases/Tracker`
    else 
      #tell the sqlite to (re)load the db
      #ret = `osascript -e 'tell application "SQLite Database Browser 2.0 b1" to activate'`
      ret = `adb pull /data/data/com.kleetus.trackanything/databases/Tracker .`
    end
  end
  

  def load_tracker
     
  end
end



Db.new(ARGV)





