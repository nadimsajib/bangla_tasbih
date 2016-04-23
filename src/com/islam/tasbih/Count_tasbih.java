package com.islam.tasbih;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Count_tasbih extends Activity implements OnClickListener{

	TextView tv,tv2;
	Button countAdd,bReset;
	int counter;
	SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.count_tasbih);
		
		Intent intent = getIntent();
		String addLabel = intent.getStringExtra("bTasbihName");
		tv = (TextView)findViewById(R.id.tvCountName);
		tv.setText(addLabel);
		tv2 = (TextView)findViewById(R.id.tvCountValue);
		bReset = (Button)findViewById(R.id.bReset);
		db = openOrCreateDatabase("Tasbih_db", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS tasbih(_id INTEGER PRIMARY KEY,name VARCHAR,count VARCHAR);");
		Cursor c=db.rawQuery("SELECT * FROM tasbih WHERE name='"+tv.getText().toString()+"'", null);
		if(c.moveToFirst()){
        	try {
        	    counter = Integer.parseInt(c.getString(2));
        	    tv2.setText(String.valueOf(counter));
        	} catch(NumberFormatException nfe) {
        	   System.out.println("Could not parse " + nfe);
        	} 
        	
        }else{
		counter = 0;
		db.execSQL("INSERT INTO tasbih(name , count) VALUES('"+tv.getText()+"' , '"+counter+"')");
        }
		countAdd = (Button)findViewById(R.id.bAddCount);
		countAdd.setOnClickListener(this);
		bReset.setOnClickListener(this);
		
	}
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			finish();
			
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bAddCount:
		counter ++;
		tv2.setText(""+counter);
        String count = Integer.toString(counter);
		db.execSQL("UPDATE tasbih SET count='"+count+"' WHERE name='"+tv.getText().toString()+"'");
		break;
		case R.id.bReset:
			counter = 0;
			String cnt = Integer.toString(counter);
			tv2.setText(cnt);
			db.execSQL("UPDATE tasbih SET count='"+cnt+"' WHERE name='"+tv.getText().toString()+"'");
			break;
		}
	}
	

}
