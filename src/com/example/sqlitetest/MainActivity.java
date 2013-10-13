// thank's to 
// http://www.vogella.com/articles/AndroidSQLite/article.html#overview_sqlite
// for this usefull tuto

package com.example.sqlitetest;

import java.util.List;
import java.util.Random;

import com.example.sqlitetest.R;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
/*
public class MainActivity extends ListActivity {
  private CommentsDataSource datasource;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    datasource = new CommentsDataSource(this);
    datasource.open();

    List<Comment> values = datasource.getAllComments();

    // use the SimpleCursorAdapter to show the
    // elements in a ListView
    ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this,
        android.R.layout.simple_list_item_1, values);
    setListAdapter(adapter);
  }

  // Will be called via the onClick attribute
  // of the buttons in main.xml
  public void onClick(View view) {
    @SuppressWarnings("unchecked")
    ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) getListAdapter();
    Comment comment = null;
    switch (view.getId()) {
    case R.id.add:
      String[] comments = new String[] { "Cool", "Very nice", "Hate it" };
      int nextInt = new Random().nextInt(3);
      // save the new comment to the database
      comment = datasource.createComment(comments[nextInt]);
      adapter.add(comment);
      break;
    case R.id.delete:
      if (getListAdapter().getCount() > 0) {
        comment = (Comment) getListAdapter().getItem(0);
        datasource.deleteComment(comment);
        adapter.remove(comment);
      }
      break;
    }
    adapter.notifyDataSetChanged();
  }

  @Override
  protected void onResume() {
    datasource.open();
    super.onResume();
  }

  @Override
  protected void onPause() {
    datasource.close();
    super.onPause();
  }

} 

*/
public class MainActivity extends ListActivity {
	
	private CommentsDataSource datasource;
	Button add = null;
	Button delete = null;
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = null;
        layout = (LinearLayout) LinearLayout.inflate(this, R.layout.activity_main, null);
        setContentView(layout);
        
        add = (Button)findViewById(R.id.add);
        delete = (Button)findViewById(R.id.delete);
        
        datasource = new CommentsDataSource(this);
        datasource.open();
        
        List<Comment> values = datasource.getAllComments();
        
        //use the SimpleCursorAdapter to show the elements in a ListView
        ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this, android.R.layout.simple_expandable_list_item_1,values);
        setListAdapter(adapter);
        
        
        add.setOnClickListener(clickListenerBoutonsAdd);
        delete.setOnClickListener( clickListenerBoutonsDelete);
        
        
    }

    @Override
    protected void onResume(){
    	datasource.open();
    	super.onResume();
    }

    @Override
    protected void onPause(){
    	datasource.close();
    	super.onPause();
    }

    //redefine the Onclick actions thanks to an attribute 
    private OnClickListener clickListenerBoutonsAdd = new OnClickListener(){
    	public void onClick(View view){
    		ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) getListAdapter();
    		Comment comment = null;
    			String[] comments = new String[] {"Cool", "Very nice", "Hate it"};
    			int nextInt = new Random().nextInt(3);
    			//save the new comment to database
    			comment = datasource.createComment(comments[nextInt]);
    			adapter.add(comment);
    			adapter.notifyDataSetChanged();
    	};
    };
    
    private OnClickListener clickListenerBoutonsDelete = new OnClickListener(){
    	public void onClick(View view){
    		ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) getListAdapter();
    		Comment comment = null;
    		if (getListAdapter().getCount()>0){
				comment = (Comment) getListAdapter().getItem(0);
				datasource.deleteComment(comment);
				adapter.remove(comment);
			}
    		
    			adapter.notifyDataSetChanged();
    	};
    };
    
    
}
