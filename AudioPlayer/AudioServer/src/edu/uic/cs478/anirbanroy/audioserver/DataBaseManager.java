package edu.uic.cs478.anirbanroy.audioserver;

import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

// Class which creates server log database
public class DataBaseManager extends SQLiteOpenHelper {

	public final static String DB_NAME = "AUDIO_SERVER_DB";
	public final static String TABLE_NAME = "TRAN_LOG";
	public final static String TRANSAC = "_TRANSACTION";
	public final static String LOG_TIME = "LOGTIME";
	public final static int VERSION = 1;
	private Context context;
	private String LOGCAT ="DataBaseManager";
	
	
	public DataBaseManager(Context context) {
		super(context, DB_NAME, null, VERSION);
		// TODO Auto-generated constructor stub
		this.context = context;
		
		Log.i(LOGCAT,"Created");
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		// TODO Auto-generated method stub
		String table_create = "CREATE TABLE "+TABLE_NAME+ "(_ID"
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ TRANSAC + " TEXT NOT NULL, "+ LOG_TIME+" TEXT NOT NULL)";
		database.execSQL(table_create);
		Log.i(LOGCAT,"Created AudioServer DB log table..");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	public void deleteDatabase() {
		context.deleteDatabase(DB_NAME);
		Log.i(LOGCAT,"Deleted AudioServerDB");
	}

}
