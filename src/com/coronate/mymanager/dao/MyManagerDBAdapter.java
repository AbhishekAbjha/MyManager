package com.coronate.mymanager.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.sql.SQLException;

public class MyManagerDBAdapter {

    public static final String DATABASE_NAME = "MyManager.db";

    public static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_PasswordAccount =
            "CREATE TABLE IF NOT EXISTS PasswordAccount "
            + "(" + PasswordManagerDBAdapter.ACCOUNT_ID + " Integer Primary key Autoincrement,"
            + PasswordManagerDBAdapter.ACCOUNT_NAME +" text not null,"
            + PasswordManagerDBAdapter.USERNAME + " text not null,"
            + PasswordManagerDBAdapter.PASSWORD + " text not null,"
            + PasswordManagerDBAdapter.NOTES + " text,"
            + PasswordManagerDBAdapter.IS_DELETED + " Integer,"
            + PasswordManagerDBAdapter.CREATED_DATE + " text, "
            + PasswordManagerDBAdapter.LAST_UPDATED + " text)";

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    /**
     * Constructor
     * @param ctx
     */
    public MyManagerDBAdapter(Context ctx)
    {
        this.context = ctx;
        this.DBHelper = new DatabaseHelper(this.context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(CREATE_TABLE_PasswordAccount);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion) {

        }
    }

    /**
     * open the db
     * @return this
     * @throws SQLException
     * return type: DBAdapter
     */
    public MyManagerDBAdapter open() throws SQLException
    {
        this.db = this.DBHelper.getWritableDatabase();
        return this;
    }

    /**
     * close the db
     * return type: void
     */
    public void close()
    {
        this.DBHelper.close();
    }
}


