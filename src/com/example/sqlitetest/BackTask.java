package com.example.sqlitetest;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class BackTask extends AsyncTask<String, Integer, Integer>{
	Activity mContext;
	private ProgressBar mProgressBar;
	
	public BackTask(Activity context){
		super();
		this.mContext = context;
	}
	
	@Override
	protected void onPreExecute(){
		this.mProgressBar = (ProgressBar) mContext.findViewById(R.id.pBAsync);
		this.mProgressBar.setVisibility(View.VISIBLE);
		super.onPreExecute();
		Toast.makeText(mContext,  "Début du traitement asynchrone", Toast.LENGTH_SHORT).show();
	}
		
	protected Integer doInBackground(String... params) { 
		// Here we do useless work, you have to put here what you have to do
				for (int i = 0; i< 100; i++){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		 
					int progress = (i);
					Log.i("test", " progress : " + progress);
					// Cette fonction appelle la fonction onProgressUpdate()
					// This function call onProgressUpdate()
					publishProgress(progress);
				}
				return null;
				
    }
 
 
     //mise en place d'une progress bar : cf tuto : http://www.tutos-android.com/asynctask-android-traitement-asynchrone-background
    @Override
    protected void onProgressUpdate(Integer... values){
		this.mProgressBar.setProgress(values[0]);
		super.onProgressUpdate(values);
    }
     
    @Override
    protected void onPostExecute(Integer result) {
    	this.mProgressBar.setVisibility(View.GONE);
        Toast.makeText(mContext, "Le traitement asynchrone est terminé", Toast.LENGTH_SHORT).show();
        super.onPostExecute(result);
    }



}
