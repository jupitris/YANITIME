class CreateUsers < ActiveRecord::Migration
  def self.up
    create_table :users do |t|
      t.integer :id
      t.string :user_name, :limit => 50, :null => false
      t.string :password, :limit => 100, :null => false
      t.string :mail_address, :limit => 100, :null => false
      t.string :nick_name, :limit => 50, :null => false
      t.integer :age
      t.integer :gender
      t.string :created_by, :limit => 50, :null => false
      t.datetime :created_at, :null => false
      t.string :updated_by, :limit => 50
      t.datetime :updated_at
      t.integer :delete_no, :default => 0, :null => false
      t.integer :version_no, :default => 0, :null => false

      t.timestamps
    end
  end

  def self.down
    drop_table :users
  end
end
