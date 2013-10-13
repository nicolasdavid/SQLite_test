package com.example.testdatabaseactivity;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CommentsDataSource {
	
	//Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = {MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_COMMENT};
	
	
	//constructor
	public CommentsDataSource(Context context){
		dbHelper = new MySQLiteHelper(context);
	}
	
	
	//Open and close database
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close(){
		dbHelper.close();
	}
	
	//updating content
	// need to complete it
	
}
