package edu.uic.cs478.anirbanroy.audioserver;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.ContactsContract.Contacts.Data;
import android.util.Log;
import android.widget.Toast;
import edu.uic.cs478.anirbanroy.servicebinder.ServiceBinder;

public class AudioServer extends Service {

	// Set of already assigned IDs
	// Note: These keys are not guaranteed to be unique if the Service is killed 
	// and restarted.
	private MediaPlayer mPlayer;
	private DataBaseManager mdbManager;
	private ContentValues contentValues;
	private String mCurrentTrack;
	private Cursor cursor;

	// Implement the Stub for this Object
	private final ServiceBinder.Stub mBinder = new ServiceBinder.Stub(){

		@Override
		public void play(int songID) throws RemoteException { // plays song
			// TODO Auto-generated method stub
			if(mPlayer!=null && mPlayer.isPlaying())
				{
					mPlayer.stop();
					mPlayer.release();
				}
			switch(songID){  // choice of track from client
			case 1:
				mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song1);
				mCurrentTrack = "Track-1";
				break;
			case 2:
				mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song2);
				mCurrentTrack = "Track-2";
				break;
			case 3:
				mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song3);
				mCurrentTrack = "Track-3";
				break;
			case 4:
				mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song4);
				mCurrentTrack = "Track-4";
				break;
			}
			
			// set listener as when track completes a broadcast is sent
			mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				
				@Override
				public void onCompletion(MediaPlayer mp) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					intent.putExtra("TRACKID", mCurrentTrack);
					intent.setAction("edu.uic.cs478.anirbanroy.audioclient.AUDI_BRO_RECEI");
					sendBroadcast(intent);
					Log.i("AudioServer","Broadcast on track completion sent");
				}
			});
			mPlayer.start();
			logTransaction("Playing ");
		}

		@Override
		public void pause() throws RemoteException {
			// TODO Auto-generated method stub
			if(mPlayer!=null && mPlayer.isPlaying())
				mPlayer.pause();
			logTransaction("Paused while playing ");
		}

		@Override
		public void resume() throws RemoteException {
			// TODO Auto-generated method stub
			if(mPlayer!=null && !(mPlayer.isPlaying()))
				mPlayer.start();
			logTransaction("Resumed playing ");
		}

		@Override
		public void stop() throws RemoteException {
			// TODO Auto-generated method stub
			if(mPlayer!=null && mPlayer.isPlaying())
				mPlayer.stop();

			logTransaction("Stopped while playing ");
		}
		// retrieve all transaction log from server DB
		public List<String> getAllTransactions() throws RemoteException {
			ArrayList<String> transactions = new ArrayList<String>();
			String result;
			String[] columns = {DataBaseManager.TRANSAC, DataBaseManager.LOG_TIME};
			cursor = mdbManager.getWritableDatabase().query(DataBaseManager.TABLE_NAME,
					columns, null, new String[] {}, null, null,
					null);
			try{
				if (cursor.moveToFirst()){
					while (cursor.isAfterLast() == false){
						result = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseManager.TRANSAC)) + " at timestamp "+
								 cursor.getString(cursor.getColumnIndexOrThrow(DataBaseManager.LOG_TIME));
						transactions.add(result);
						//Log.i("DBRESULT",result);
						cursor.moveToNext();
					}
				}
			}
			catch(Exception e){
				Log.e("AudioServer",e.toString());
			}
			return transactions;
		}
		
	};

	// Return the Stub defined above
	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}
	@Override
	public void onCreate(){
		mdbManager = new DataBaseManager(getApplicationContext());
		
		//mdbManager = new DataBaseManager(getApplicationContext());
		contentValues = new ContentValues();
	}
	@Override
	public boolean onUnbind(Intent intent){
		mPlayer.stop();
	    mPlayer.release();
	    mdbManager.getWritableDatabase().close();
	    return super.onUnbind(intent);
	}
	
	 @Override
	public void onDestroy(){
		// mdbManager.deleteDatabase();
	     super.onDestroy();
	}
	 
	// Log Transaction to the Server Database
	protected void logTransaction(String transaction){
		String time = Calendar.getInstance().getTime().toString();
		contentValues.put(DataBaseManager.LOG_TIME, time);
		contentValues.put(DataBaseManager.TRANSAC, transaction + mCurrentTrack);
		mdbManager.getWritableDatabase().insert(DataBaseManager.TABLE_NAME, null, contentValues);
		contentValues.clear();
		Log.i("AudioServer","Transaction saved to DB!");
	}
	 
}

