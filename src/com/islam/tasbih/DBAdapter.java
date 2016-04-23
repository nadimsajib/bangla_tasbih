package com.islam.tasbih;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {

    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_THUMB = "thumb";

    private static final String DATABASE_NAME = "db_app_name";
    private static final String DATABASE_TABLE_NAME = "tbl_app_name";

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE_INFORMATION = "create table tbl_app_name (id text not null, "
            + "title text not null, thumb text not null);";

    private final Context context;

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_CREATE_INFORMATION);

        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS tbl_app_name");

            onCreate(db);
        }
    }

    // ---opens the database---
    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    // ---closes the database---
    public void close() {
        DBHelper.close();
    }

    // ---insert a title into the database---
    public long insertData(String id, String title, String thumb) {
        ContentValues initialValues = new ContentValues();

        initialValues.put(KEY_ID, id);
        initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_THUMB, thumb);
        return db.insert(DATABASE_TABLE_NAME, null, initialValues);
    }

    public boolean deleteItem(String id) {
        return db.delete(DATABASE_TABLE_NAME, KEY_ID + "=" + id,
                null) > 0;
    }

    public void deleteAllItemsFrom(String table_name) {
        SQLiteDatabase db = DBHelper.getWritableDatabase();
        db.delete(table_name, null, null);
    }

    public Cursor getAllData() {
        return db.query(DATABASE_TABLE_NAME, new String[] { KEY_ID,
                KEY_TITLE, KEY_THUMB }, null, null, null, null, null);
    }

    public Cursor getParticularItem(String id) throws SQLException {
        Cursor mCursor = db.query(true, DATABASE_TABLE_NAME, new String[] {
                KEY_ID, KEY_TITLE, KEY_THUMB }, KEY_ID + "=" + id, null,
                null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor getSearchItem(String id) throws SQLException {
        Cursor mCursor = db.query(DATABASE_TABLE_NAME, new String[] {
                KEY_ID, KEY_TITLE, KEY_THUMB }, KEY_ID + " LIKE " + "'%"
                + id + "%'", null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

}
