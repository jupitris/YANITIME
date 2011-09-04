class CreatePlaces < ActiveRecord::Migration
  def self.up
    create_table :places do |t|
      t.integer :id
      t.string :place_name, :limit => 100, :null => false
      t.decimal :latitude, :precision => 17, :scale => 14, :default => 0.0, :null => false
      t.decimal :longitude, :precision => 17, :scale => 14, :default => 0.0, :null => false
      t.string :comment, :limit => 4000
      t.string :created_by, :limit => 50, :null => false
      t.datetime :created_at, :null => false
      t.string :updated_by, :limit => 50
      t.datetime :updated_at
      t.integer :delete_no, :default => 0, :null => false
      t.integer :version_no, :default => 0, :null => false

      t.references :user
      t.timestamps
    end
  end

  def self.down
    drop_table :places
  end
end
