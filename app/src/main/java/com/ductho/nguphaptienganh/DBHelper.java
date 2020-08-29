package com.ductho.nguphaptienganh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ductho.nguphaptienganh.Model.Note;

import java.util.ArrayList;
import java.util.List;

import static com.ductho.nguphaptienganh.NoteContract.NoteEntry.COLUMN_DESCRIPTION;
import static com.ductho.nguphaptienganh.NoteContract.NoteEntry.COLUMN_TITLE;
import static com.ductho.nguphaptienganh.NoteContract.NoteEntry.NOTE_TABLE;
import static com.ductho.nguphaptienganh.NoteContract.NoteEntry._ID;

/**
 * Created by Aman on 9/18/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String TAG = DBHelper.class.getSimpleName();
    public static final String DB_NAME = "singin.db";

    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;

    public static final String USER_TABLE = "users";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_CNFRMPASS = "cnfrmpassword";


    public SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + NOTE_TABLE + "(" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT " +
                ")");
        db.execSQL(" CREATE TABLE " + USER_TABLE +
                "(" + COLUMN_NAME + " TEXT, " + COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT, " + COLUMN_CNFRMPASS + " TEXT " + ")");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + NOTE_TABLE);
        onCreate(db);
    }

    /* Storing User details*/

    public void addUser(String name, String username, String password, String cnfrmpassword) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_CNFRMPASS, cnfrmpassword);

        db.insert(USER_TABLE, null, values);
        db.close();
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + USER_TABLE, null);
    }

    public void addNote(String title, String description) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_DESCRIPTION, description);
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            mWritableDB.insert(NOTE_TABLE, null, cv);
        } catch (Exception e) {
            Log.d(TAG, "INSERT EXCEPTION! " + e.getMessage());
        }
    }

    public void deleteNote(int id) {
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            mWritableDB.delete(NOTE_TABLE,
                    _ID + "= ?", new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.d(TAG, "DELETE EXCEPTION! " + e.getMessage());
        }

    }

    public void deleteAllNote() {
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            mWritableDB.execSQL("DELETE FROM " + NOTE_TABLE);
        } catch (Exception e) {
            Log.d(TAG, "DELETE EXCEPTION! " + e.getMessage());
        }

    }

    public void updateNote(int id, String title, String description) {
        // New value for one column
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            ContentValues values = new ContentValues();
            values.put(_ID, id);
            values.put(COLUMN_TITLE, title);
            values.put(COLUMN_DESCRIPTION, description);

            mWritableDB.update(NOTE_TABLE, //table to change
                    values, // new values to insert
                    _ID + " = ?", // selection criteria for row (in this case, the _id column)
                    new String[]{String.valueOf(id)}); //selection args; the actual value of the id

        } catch (Exception e) {
            Log.d(TAG, "UPDATE EXCEPTION! " + e.getMessage());
        }
    }

    public Note query(int position) {
        String query = "SELECT  * FROM " + NOTE_TABLE +
                " ORDER BY " + _ID + " ASC " +
                "LIMIT " + position + ",1";

        Cursor cursor = null;
        Note entry = new Note();

        try {
            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();
            }
            cursor = mReadableDB.rawQuery(query, null);
            cursor.moveToFirst();
            entry.setID(cursor.getInt(cursor.getColumnIndex(_ID)));
            entry.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
            entry.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
        } catch (Exception e) {
            Log.d(TAG, "QUERY EXCEPTION! " + e.getMessage());
        } finally {
            // Must close cursor and db now that we are done with it.
            cursor.close();
            return entry;
        }
    }

    public long count() {
        if (mReadableDB == null) {
            mReadableDB = getReadableDatabase();
        }
        return DatabaseUtils.queryNumEntries(mReadableDB, NOTE_TABLE);
    }

    public List<Note> getAllNotes(){
        List<Note> notes = new ArrayList<>();
        if (mReadableDB == null) {
            mReadableDB = getReadableDatabase();
        }
        Cursor c = mReadableDB.rawQuery("SELECT * FROM " + NOTE_TABLE,null);
        if(c.moveToFirst()){
            do{
                Note note = new Note();
                note.setID(c.getInt(c.getColumnIndex(_ID)));
                note.setTitle(c.getString(c.getColumnIndex(COLUMN_TITLE)));
                note.setDescription(c.getString(c.getColumnIndex(COLUMN_DESCRIPTION)));
                notes.add(note);
            }
            while (c.moveToNext());
        }
        c.close();
        return notes;
    }

    public List<Note> getNote(String text){
        List<Note> notes = new ArrayList<>();
        if (mReadableDB == null) {
            mReadableDB = getReadableDatabase();
        }
        Cursor c = mReadableDB.rawQuery("SELECT * FROM " + NOTE_TABLE + " WHERE " + COLUMN_TITLE + " LIKE ?",new String[]{text});
        if(c.moveToFirst()){
            do{
                Note note = new Note();
                note.setID(c.getInt(c.getColumnIndex(_ID)));
                note.setTitle(c.getString(c.getColumnIndex(COLUMN_TITLE)));
                note.setDescription(c.getString(c.getColumnIndex(COLUMN_DESCRIPTION)));
                notes.add(note);
            }
            while (c.moveToNext());
        }
        c.close();
        return notes;
    }
}
