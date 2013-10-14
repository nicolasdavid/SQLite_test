// thank's to 
// http://www.vogella.com/articles/AndroidSQLite/article.html#overview_sqlite
// for this usefull tuto

package com.example.sqlitetest;

import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class MainActivity extends ListActivity {
	
	private CommentsDataSource datasource;
	Button add = null;
	Button delete = null;
	
	
	//creating the widget for AsyncTask
	//private ProgressBar mProgressBar;
	private Button mButton;
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = null;
        layout = (LinearLayout) LinearLayout.inflate(this, R.layout.activity_main, null);
        setContentView(layout);
        
        //Link to id the Widget for updating database in local
        add = (Button)findViewById(R.id.add);
        delete = (Button)findViewById(R.id.delete);
        
        //Link to id the Widget for the AsyncTask
	   //mProgressBar = (ProgressBar) findViewById(R.id.pBAsync);
	    mButton = (Button) findViewById(R.id.btnLaunch);
        
	    
        datasource = new CommentsDataSource(this);
        datasource.open();
        
        List<Comment> values = recupComment();
        
        //use the SimpleCursorAdapter to show the elements in a ListView
        ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this, android.R.layout.simple_expandable_list_item_1,values);
        setListAdapter(adapter);
        
        
        add.setOnClickListener(clickListenerBoutonsAdd);
        delete.setOnClickListener( clickListenerBoutonsDelete);
        mButton.setOnClickListener( clickListenerBackTask);
        
        
        
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
    
    
    public void refreshList(){      
    	List<Comment> values = recupComment();
        ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this, android.R.layout.simple_expandable_list_item_1,values);
        setListAdapter(adapter);  
   }

    public List<Comment> recupComment() {
        datasource = new CommentsDataSource(this);
        datasource.open();
        List<Comment> values = datasource.getAllComments();
        return values;
         
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
    
    private OnClickListener clickListenerBackTask = new OnClickListener(){
    	public void onClick(View view){
				BackTask calcul = new BackTask(MainActivity.this);
				calcul.execute();
				

    	};
    };
    
    
    
}
