package com.example.sqlitetest;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CommentsDataSource {
	
	//Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	
	public MySQLiteHelper getDbHelper() {
		return dbHelper;
	}


	private String[] allColumns = {MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_DESCRIPTION};
	
	
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
	public Comment createComment (String str){
		ContentValues values = new ContentValues(); // se comporte comme une hashmap
		values.put(MySQLiteHelper.COLUMN_DESCRIPTION, str);
		long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null, values);
		Cursor cursor = 
				database.query(
						MySQLiteHelper.TABLE_COMMENTS,
						allColumns,
						MySQLiteHelper.COLUMN_ID+" = "+insertId,
						null, null, null, null);
		cursor.moveToFirst();
		Comment newComment = cursorToComment(cursor);//method at the end of the class
		cursor.close();
		return newComment;
	}
	
	//surcharge
	public Comment createComment (long id, String str){
        Boolean exist = existCommentWithId(id);
        
        if(exist == true){
            Comment existComment = getCommentWithId(id);
            Comment updatedComment = updateComment(id, existComment, str);
            return updatedComment;
        }
        else {
            ContentValues values = new ContentValues();
            values.put(MySQLiteHelper.COLUMN_ID, id);
            values.put(MySQLiteHelper.COLUMN_DESCRIPTION, str);
            long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
                    values);
            Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                    allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                    null, null, null);
            cursor.moveToFirst();
            Comment newComment = cursorToComment(cursor);
            cursor.close();
            return newComment;
        }
    }
	
	public Comment updateComment(Long id, Comment comment, String descr){
        ContentValues values = new ContentValues();
 
        //values.put(MySQLiteHelper.COLUMN_ID, comment.getId());
        //values.put(MySQLiteHelper.COLUMN_COMMENT, comment.getComment());
        values.put(MySQLiteHelper.COLUMN_DESCRIPTION, descr);
 
        database.update(MySQLiteHelper.TABLE_COMMENTS, values, MySQLiteHelper.COLUMN_ID + " = " +comment.getId(), null);
 
        return getCommentWithId(comment.getId());
    }
	
    public Comment getCommentWithId(Long id){
        Cursor c = database.query(MySQLiteHelper.TABLE_COMMENTS, allColumns, MySQLiteHelper.COLUMN_ID + " = \"" + id +"\"", null, null, null, null);
        c.moveToFirst();
        Comment contact = cursorToComment(c);
        c.close();
        return contact;
    }
    
    public Boolean existCommentWithId(Long id){
        Cursor c = database.query(MySQLiteHelper.TABLE_COMMENTS, allColumns, MySQLiteHelper.COLUMN_ID + " = \"" + id +"\"", null, null, null, null);
        if(c.getCount()>0){
            c.close();
            return true;
        }
        else {
            c.close();
            return false;
        }
    }
	
	
	
	public void deleteComment(Comment comment){
		long id = comment.getId();
		System.out.println("Comment deleted with id: "+ id);
		database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID+" = "+ id, null);
	}
	
	public List<Comment> getAllComments(){
		List<Comment> comments = new ArrayList<Comment>();
		
		Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS, allColumns, null, null, null, null, null);
		
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Comment comment = cursorToComment(cursor);
			comments.add(comment);
			cursor.moveToNext();
		}
		cursor.close();
		return comments;
	}


  private Comment cursorToComment(Cursor cursor) {
    Comment comment = new Comment();
    comment.setId(cursor.getLong(0));
    comment.setDescription(cursor.getString(1));
    return comment;
	
  }
}