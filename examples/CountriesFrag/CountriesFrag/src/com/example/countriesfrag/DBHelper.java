package com.example.countriesfrag;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public DBHelper(Context context) {
		super(context, "country.db", null, 1); // special type of cursor is required - third param
	}

	@Override
	public void onCreate(SQLiteDatabase db) { // _id is not there - simpleCursorAdapter will not work
		String query = "create table country(_id integer primary key, " +
						"name text not null)";
		db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String query = "drop table if exists";
		db.execSQL(query); // migrate the data before doing this
		onCreate(db);
	}

}
