class CreateLocations < ActiveRecord::Migration
  def self.up
    create_table :locations do |t|
      t.integer :id
      t.string :title
      t.string :description
      t.integer :latitude
      t.integer :longitude
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
    drop_table :locations
  end
end
