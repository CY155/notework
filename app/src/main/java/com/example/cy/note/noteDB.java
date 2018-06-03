package com.example.cy.note;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*数据库*/
public class noteDB extends SQLiteOpenHelper {
    public static final String NOTE_TABLE="note";
    public static final String NAME_ID="id";
    public static final String NOTE_TITLE="title";
    public static final String NOTE_CONTENT="content";
    public static final String NOTE_TIME="time";

    public noteDB(Context context) {
        super(context,"note.db",null,2);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="CREATE TABLE "+NOTE_TABLE+"("+NAME_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NOTE_TITLE+" TEXT NOT NULL DEFAULT\"\","+NOTE_CONTENT+" TEXT NOT NULL DEFAULT\"\","+NOTE_TIME+" TEXT NOT NULL DEFAULT\"\"" +")";
        sqLiteDatabase.execSQL(sql);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
