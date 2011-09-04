# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended to check this file into your version control system.

ActiveRecord::Schema.define(:version => 20110903025317) do

  create_table "places", :force => true do |t|
    t.string   "place_name", :limit => 100,                                                   :null => false
    t.decimal  "latitude",                   :precision => 17, :scale => 14, :default => 0.0, :null => false
    t.decimal  "longitude",                  :precision => 17, :scale => 14, :default => 0.0, :null => false
    t.string   "comment",    :limit => 4000
    t.string   "created_by", :limit => 50,                                                    :null => false
    t.datetime "created_at"
    t.string   "updated_by", :limit => 50
    t.datetime "updated_at"
    t.integer  "delete_no",                                                  :default => 0,   :null => false
    t.integer  "version_no",                                                 :default => 0,   :null => false
    t.integer  "user_id"
  end

  create_table "users", :force => true do |t|
    t.string   "user_name",    :limit => 50,                 :null => false
    t.string   "password",     :limit => 100,                :null => false
    t.string   "mail_address", :limit => 100,                :null => false
    t.string   "nick_name",    :limit => 50,                 :null => false
    t.integer  "age"
    t.integer  "gender"
    t.string   "created_by",   :limit => 50,                 :null => false
    t.datetime "created_at"
    t.string   "updated_by",   :limit => 50
    t.datetime "updated_at"
    t.integer  "delete_no",                   :default => 0, :null => false
    t.integer  "version_no",                  :default => 0, :null => false
  end

end
