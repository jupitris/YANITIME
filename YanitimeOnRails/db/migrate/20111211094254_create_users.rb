class CreateUsers < ActiveRecord::Migration
  def self.up
    create_table :users do |t|
      t.integer :id
      t.string :user_name
      t.string :password
      t.string :mail_address
      t.string :nick_name
      t.integer :age
      t.integer :gender
      t.string :created_by
      t.datetime :created_at
      t.string :updated_by
      t.datetime :updated_at
      t.integer :delete_no
      t.integer :version_no

      t.timestamps
    end
  end

  def self.down
    drop_table :users
  end
end
