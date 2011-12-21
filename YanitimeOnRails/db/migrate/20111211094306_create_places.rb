class CreatePlaces < ActiveRecord::Migration
  def self.up
    create_table :places do |t|
      t.integer :id
      t.integer :user_id
      t.string :place_name
      t.decimal :latitude
      t.decimal :longitude
      t.string :comment
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
    drop_table :places
  end
end
