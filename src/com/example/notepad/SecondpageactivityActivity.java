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

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class SecondpageactivityActivity extends Activity {

	private EditText title_text,body_text;
	private SaveToDisk std;
	private static Button save_button,cancel_button,saveas_button;
	private static int neworold = 0;
	final Context context = this;
	//static String filename="",directory="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secondpageactivity);
		// Show the Up button in the action bar.
		title_text = (EditText)findViewById(R.id.editText1);
		title_text.setText(getIntent().getStringExtra("mytitle"));
	    body_text = (EditText)findViewById(R.id.editText2);
	    body_text.setText(getIntent().getStringExtra("mybody"));
	    neworold = getIntent().getIntExtra("oldnew", 0);
	    save_button = (Button) findViewById(R.id.button1);
	    
	    
	    save_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				std = new SaveToDisk();
				std.writeFile(title_text.getText().toString(), body_text.getText().toString(),neworold);
				finish();
				
			}
		});
	    cancel_button = (Button) findViewById(R.id.button3);
	    cancel_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				
			}
		});
	    saveas_button = (Button) findViewById(R.id.button2);
	    saveas_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Dialog dialog = new Dialog(context);
				dialog.setContentView(R.layout.save_as_layout);
				dialog.setTitle("Save as");
				
				//Toast.makeText(context, body, Toast.LENGTH_SHORT).show();
				Button dialogOkButton = (Button) dialog.findViewById(R.id.button_dialog1);
				// if button is clicked, close the custom dialog
				dialogOkButton.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						EditText new_title = (EditText) dialog.findViewById(R.id.editTextbody1);
						String title = new_title.getText().toString();
						EditText new_body = (EditText) findViewById(R.id.editText2);
						String body = new_body.getText().toString();
						std = new SaveToDisk();
						std.writeFile(title, body, 0);
						finish();
						
					}
				});
				Button dialogCancelButton = (Button) dialog.findViewById(R.id.button_dialog2);
				dialogCancelButton.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						finish();
						
					}
				});
				dialog.show();
				
				
			}
		});
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.secondpageactivity, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		std = new SaveToDisk();
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.save:
			std = new SaveToDisk();
			std.writeFile(title_text.getText().toString(), body_text.getText().toString(),neworold);
			finish();
			return true;
			
		case R.id.backgroundcolor:
			Toast.makeText(context, "Coming in next version.", Toast.LENGTH_SHORT).show();
			break;
		case R.id.textcolor:
			Toast.makeText(context, "Coming in next version.", Toast.LENGTH_SHORT).show();
			break;
		case R.id.TextSize:
			Toast.makeText(context, "Coming in next version.", Toast.LENGTH_SHORT).show();
			break;
			
		case R.id.saveas:
			final Dialog dialog = new Dialog(context);
			dialog.setContentView(R.layout.save_as_layout);
			dialog.setTitle("Save as");
			
			//Toast.makeText(context, body, Toast.LENGTH_SHORT).show();
			Button dialogOkButton = (Button) dialog.findViewById(R.id.button_dialog1);
			// if button is clicked, close the custom dialog
			dialogOkButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					EditText new_title = (EditText) dialog.findViewById(R.id.editTextbody1);
					String title = new_title.getText().toString();
					EditText new_body = (EditText) findViewById(R.id.editText2);
					String body = new_body.getText().toString();
					std = new SaveToDisk();
					std.writeFile(title, body, 0);
					finish();
					
				}
			});
			Button dialogCancelButton = (Button) dialog.findViewById(R.id.button_dialog2);
			dialogCancelButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					finish();
					
				}
			});
			dialog.show();
			return true;
		case R.id.exit:
			finish();
			return true;
		case R.id.recent:
			Intent i = new Intent(SecondpageactivityActivity.this,MainPageActivity.class);
			startActivity(i);
			overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
