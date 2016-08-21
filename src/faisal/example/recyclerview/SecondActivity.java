package faisal.example.recyclerview;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class SecondActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		
		Toolbar mToolBar = (Toolbar) findViewById(R.id.imagePreviewToolbar);
		setSupportActionBar(mToolBar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		getSupportActionBar().setHomeButtonEnabled(true);
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	   switch(item.getItemId()){
	   
	   case android.R.id.home: 
	        finish();
	        break;
	    }
	    return super.onOptionsItemSelected(item);
	}
}
