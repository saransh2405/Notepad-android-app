/*
 * author : Saransh Singh
 * This class file defines the first page of notepad app 
 * Functionalities included:
 * 1. New document.
 * 2. Make quick notes.
 * 3. Display recent documents.
 * 4. Exit app.
 * Version 1.0.11*/


package com.example.notepad;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class MainPageActivity extends Activity {

	private static Button new_button,save_button;
	private static EditText text1,text2;
	private NotesDbAdapter mDbHelper;
	private SaveToDisk std;
    @SuppressLint("NewApi") @Override
    
    //This functions defines what happens when activity is created
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        mDbHelper = new NotesDbAdapter(this);
        mDbHelper.open();
        std = new SaveToDisk();
        std.makedirectory();
        
        GridView gridView;
    	ArrayList<Item> gridArray = new ArrayList<Item>();
    	CustomGridViewAdapter customGridAdapter;
    	String[][] files = std.listmaker();
    	
    	for(int i=0;i<files.length;i++)
    		gridArray.add(new Item(files[i][0],files[i][1],files[i][2]));
		
		
		
		
		gridView = (GridView) findViewById(R.id.gridView1);
		customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid, gridArray);
		gridView.setAdapter(customGridAdapter);
		
		gridView.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		    	String[][] files = std.listmaker();
				String title_text = files[position][0].substring(0, files[position][0].length()-4);
				String body_text = std.readFile(title_text);
				Intent i = new Intent(MainPageActivity.this,SecondpageactivityActivity.class);
				i.putExtra("mytitle",title_text);
				i.putExtra("mybody", body_text);
				i.putExtra("oldnew", 1);
				startActivity(i);
		    }
			
			
		});
        
        text1 = (EditText) findViewById(R.id.editText1);
        text2 = (EditText) findViewById(R.id.editText2);
        new_button = (Button) findViewById(R.id.button1);
        
        //the new button listener
        new_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String title_text = text1.getText().toString();
				String body_text = text2.getText().toString();
				Context context = getApplicationContext();
				CharSequence text = "Start Note Created";
				int duration = Toast.LENGTH_SHORT;
				
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
				text1.setText("");
				text2.setText("");
				Intent i = new Intent(MainPageActivity.this,SecondpageactivityActivity.class);
				i.putExtra("mytitle",title_text);
				i.putExtra("mybody", body_text);
				startActivity(i);
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
				Bundle tempBundle = new Bundle();
				onCreate(tempBundle);
			}
		});
        
        save_button = (Button) findViewById(R.id.button2);
        
        //the save button listener
        save_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//SimpleDateFormat s = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
				//Calendar cal = Calendar.getInstance();
				//String format = s.format(cal.getTime());
				String title_text = text1.getText().toString();
				String body_text = text2.getText().toString();
				Context context = getApplicationContext();
				CharSequence text = "Quick Note Saved";
				int duration = Toast.LENGTH_SHORT;
				
				Toast toast = Toast.makeText(context, text, duration);
				
				std = new SaveToDisk();
				std.writeFile(title_text, body_text,0);
				toast.show();
				text1.setText("");
				text2.setText("");
				Bundle tempBundle = new Bundle();
				onCreate(tempBundle);
			}
		});
    }

    
    
    
    @Override
    //Menu items initializer
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
    }
    
    @Override
    public void onResume(){
    	Bundle tempBundle = new Bundle();
		onCreate(tempBundle);
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.New:
			String title_text = text1.getText().toString();
			Context context = getApplicationContext();
			CharSequence text = "Start New Note";
			int duration = Toast.LENGTH_SHORT;
			
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
			text1.setText("");
			Intent i = new Intent(MainPageActivity.this,SecondpageactivityActivity.class);
			i.putExtra("mytext",title_text);
			startActivity(i);
			overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			return true;
			
		case R.id.action_settings:
			Toast.makeText(getApplicationContext(), "More options coming in next version.", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.exit:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
    
}
