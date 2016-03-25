package edu.uic.cs478.anirbanroy.audioclient;

//import android.support.v7.app.ActionBarActivity;
import java.util.ArrayList;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.Activity;
import android.content.Intent;

// This activity displays the list of all transactions perfromed between client and server
public class ListAudioTransactions extends Activity {
	
	private ArrayList<String> mdisplayList;
	private ListView lview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_audio_transactions);
		mdisplayList = getIntent().getStringArrayListExtra(AudioClient.TRANSACTIONS);
		lview = (ListView) findViewById(R.id.list);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	         R.layout.textview, R.id.tview, mdisplayList);
		lview.setAdapter(adapter);
	}
	@Override	
	protected void onResume() {
		super.onResume();
	}
	
}
