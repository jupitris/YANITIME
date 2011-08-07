class CreateUsers < ActiveRecord::Migration
  def self.up
    create_table :users do |t|
      t.integer :id
      t.string :user_id
      t.string :password
      t.string :name
      t.string :created_by
      t.datetime :created_on
      t.string :updated_by
      t.datetime :updated_on
      t.integer :delete_no
      t.integer :version_no

      t.timestamps
    end
  end

  def self.down
    drop_table :users
  end
end
