package edu.uic.cs478.anirbanroy.audioclient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import edu.uic.cs478.anirbanroy.servicebinder.ServiceBinder;

public class AudioClient extends Activity {

	protected static final String TAG = "AudioClient";
	private ServiceBinder mServiceBinder;
	private boolean mIsBound=false;
	private boolean isPlaying = false;
	private ListView lview;
	public final static String TRANSACTIONS = "Transactions";
	private List<String> trackList; 
	private int mCurrentTrackSelection;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		mCurrentTrackSelection = 1;
		
		lview = (ListView) findViewById(R.id.list);
		trackList = Arrays.asList("Track-1", "Track-2", "Track-3", "Track-4 (short duration)");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		         R.layout.trackslistview, R.id.track, trackList);
		lview.setAdapter(adapter);
		
		// set list click listerener
		lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
		      public void onItemClick(AdapterView<?> adapter, View v, int position,
		            long arg3){
				try {
					mServiceBinder.play(position+1);
					((Button)findViewById(R.id.play)).setText("Pause");
					isPlaying = true;
					mCurrentTrackSelection = position + 1;
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					Log.e(TAG,e.toString());
				}
			}
		});
	}

	// Bind to KeyGenerator Service
	@Override
	protected void onResume() {
		super.onResume();

		if (!mIsBound) {
			
			Intent intent = new Intent(ServiceBinder.class.getName());
			bindService(intent, this.mConnection, Context.BIND_AUTO_CREATE);
		}
	}

	// Unbind from KeyGenerator Service
	@Override
	protected void onDestroy() {

		if (mIsBound) {

			unbindService(this.mConnection);
		}

		super.onDestroy();
	}

	// create service connection object in client 
	private final ServiceConnection mConnection = new ServiceConnection() {

		public void onServiceConnected(ComponentName className, IBinder iservice) {

			mServiceBinder = ServiceBinder.Stub.asInterface(iservice);

			mIsBound = true;

		}

		public void onServiceDisconnected(ComponentName className) {

			mServiceBinder = null;

			mIsBound = false;

		}
	};
	
	// Method to handle the button clicked
	public void clickListener(View v) {
		int choice;
		switch (v.getId()){
		
			case R.id.play:
			{
				try {
					
					if(((Button)v).getText().equals("Play"))
					{
						((Button)v).setText("Pause");
						mServiceBinder.play(mCurrentTrackSelection);
						isPlaying=true;
					}
					else{
						((Button)v).setText("Play");
						mServiceBinder.pause();
						isPlaying = false;
					}
					
				} catch (RemoteException e) {

					Log.e(TAG, e.toString());

				}
				break;
			}
			/*case R.id.pause:
			{
				try {
					mServiceBinder.pause();
				} catch (RemoteException e) {

					Log.e(TAG, e.toString());
				}
				break;
			}*/
			case R.id.resume:
			{
				try {
					if(((Button)findViewById(R.id.play)).getText().equals("Play") && isPlaying==true)
						((Button)findViewById(R.id.play)).setText("Pause");
					mServiceBinder.resume();
					isPlaying = true;
				} catch (RemoteException e) {

					Log.e(TAG, e.toString());

				}
				break;
			}
			case R.id.stop:
			{
				try {
					if(!((Button)findViewById(R.id.play)).getText().equals("Play"))
						((Button)findViewById(R.id.play)).setText("Play");
					isPlaying = false;
					mServiceBinder.stop();
				} catch (RemoteException e) {

					Log.e(TAG, e.toString());

				}
				break;
			}
			case R.id.gtrns:
			{
				try {
					Intent intent = new Intent(AudioClient.this,ListAudioTransactions.class);
					intent.putStringArrayListExtra(TRANSACTIONS, (ArrayList<String>)mServiceBinder.getAllTransactions());
					startActivity(intent);
				} catch (RemoteException e) {

					Log.e(TAG, e.toString());

				}
				break;
			}
		}
	}

}
