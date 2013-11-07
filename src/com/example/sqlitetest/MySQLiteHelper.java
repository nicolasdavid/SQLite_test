package com.example.sqlitetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

		/**
		 * declaration of tables
		 */
		public static final String TABLE_PROJECT = "Project";
		public static final String TABLE_GPSGEOM = "gpsGeom";

		
		//creation of tables getters

		public static String getTableProject() {
			return TABLE_PROJECT;
		}


		public static String getTableGpsgeom() {
			return TABLE_GPSGEOM;
		}

		//creation of columns
		
		public static final String COLUMN_PROJECTID = "project_id";
		public static final String COLUMN_PROJECTNAME = "project_name";
		
		public static final String COLUMN_GPSGEOMID = "gpsGeom_id";
		public static final String COLUMN_GPSGEOMCOORD = "gpsGeom_coord";
		
		public static final String DATABASE_NAME = "local.db";
		public static final int DATABASE_VERSION = 1;
		
		// query to create the database
		private static final String 

					DATABASE_CREATE = 

					"CREATE TABLE "
						+ TABLE_GPSGEOM + "(" 
						+ COLUMN_GPSGEOMID   + "INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
						+ COLUMN_GPSGEOMCOORD + " VARCHAR(255) " 
					+");"

				    +"CREATE TABLE "
					    + TABLE_PROJECT + "(" 
					    + COLUMN_PROJECTID   + "INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
					    + COLUMN_PROJECTNAME + " VARCHAR(255) " 
					    + COLUMN_GPSGEOMID + " INTEGER " 
					    + "FOREIGN KEY("+ COLUMN_GPSGEOMID +") REFERENCES "+TABLE_GPSGEOM+"("+COLUMN_GPSGEOMID+")"
					+");"
		;

			     
		
		//constructor
		public MySQLiteHelper(Context context){
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		

		@Override
		public void onCreate(SQLiteDatabase database) {
			database.execSQL(DATABASE_CREATE);		
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(MySQLiteHelper.class.getName(), 
			"Upgrading database from version"+oldVersion+" to "+newVersion+", which will destroy all your data");
			db.execSQL(
					"DROP TABLE IF EXISTS" 
							+ TABLE_PROJECT +", "
							+ TABLE_GPSGEOM +"; "
					);
			onCreate(db);		
		}
		
}
