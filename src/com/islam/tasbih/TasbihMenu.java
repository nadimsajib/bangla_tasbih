package com.islam.tasbih;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TasbihMenu extends Activity implements OnClickListener{

	Button bTasbih1,bTasbih2,bTasbih3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tasbih_menu);
		bTasbih1 = (Button)findViewById(R.id.bT1);
		bTasbih2 = (Button)findViewById(R.id.bT2);
		bTasbih3 = (Button)findViewById(R.id.bT3);
		bTasbih1.setOnClickListener(this);
		bTasbih2.setOnClickListener(this);
		bTasbih3.setOnClickListener(this);
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
		case R.id.bT1:
			Intent my = new Intent(TasbihMenu.this, Count_tasbih.class);
			my.putExtra("bTasbihName",bTasbih1.getText().toString());
			startActivity(my);
			break;
		case R.id.bT2:
			Intent my1 = new Intent(TasbihMenu.this, Count_tasbih.class);
			my1.putExtra("bTasbihName",bTasbih2.getText().toString());
			startActivity(my1);
			break;
		case R.id.bT3:
			Intent my2 = new Intent(TasbihMenu.this, Count_tasbih.class);
			my2.putExtra("bTasbihName",bTasbih3.getText().toString());
			startActivity(my2);
			break;
		//String name = ((Button) bTasbih1).getText().toString();
	
		}
	}
	

}
