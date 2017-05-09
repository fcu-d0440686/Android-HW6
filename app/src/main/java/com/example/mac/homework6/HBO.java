package com.example.mac.homework6;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HBO extends SQLiteOpenHelper {


    public HBO(Context context) {
        super(context, "note.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +
                NoteDB.NOTETABLE + "(title, body);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
    }

}
