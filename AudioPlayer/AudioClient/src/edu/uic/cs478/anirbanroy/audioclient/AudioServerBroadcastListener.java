package edu.uic.cs478.anirbanroy.audioclient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AudioServerBroadcastListener extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(context, "Finished playing "+intent.getStringExtra("TRACKID"), Toast.LENGTH_LONG).show();
	}

}
