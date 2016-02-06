package com.coronate.mymanager.dao;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import com.coronate.mymanager.domain.PasswordAccount;
import com.coronate.mymanager.util.TimeStamp;

import java.sql.SQLException;

public class PasswordManagerDBAdapter {

    public static final String ACCOUNT_ID = "accountId";
    public static final String ACCOUNT_NAME = "accountName";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String NOTES = "notes";
    public static final String IS_DELETED = "isDeleted";
    public static final String CREATED_DATE = "createdDate";
    public static final String LAST_UPDATED = "lastUpdated";

    private static final String DATABASE_TABLE = "PasswordAccount";

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, MyManagerDBAdapter.DATABASE_NAME, null, MyManagerDBAdapter.DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx
     *            the Context within which to work
     */
    public PasswordManagerDBAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    /**
     * Open the MyManager database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     *
     * @return this (self reference, allowing this to be chained in an
     *         initialization call)
     * @throws SQLException
     *             if the database could be neither opened or created
     */
    public PasswordManagerDBAdapter open() throws SQLException {
        this.mDbHelper = new DatabaseHelper(this.mCtx);
        this.mDb = this.mDbHelper.getWritableDatabase();
        return this;
    }

    /**
     * close return type: void
     */
    public void close() {
        this.mDbHelper.close();
    }

    /**
     * Create a new PasswordAccount. If the PasswordAccount is successfully created return the new
     * rowId for that PasswordAccount, otherwise return a -1 to indicate failure.
     *
     * @param passwordAccount
     * @return rowId or -1 if failed
     */

    /**
     * Create a new PasswordAccount. If the PasswordAccount is successfully created return the new
     * rowId for that PasswordAccount, otherwise return a -1 to indicate failure.
     *
     * @param passwordAccount
     * @return rowId or -1 if failed
     */
    public long createPasswordAccount(PasswordAccount passwordAccount){
        ContentValues initialValues = new ContentValues();
        initialValues.put(ACCOUNT_NAME, passwordAccount.getAccountName()); //Account Name
        initialValues.put(USERNAME, passwordAccount.getUserName()); // UserName
        initialValues.put(PASSWORD, passwordAccount.getPassword()); //Password
        initialValues.put(NOTES, passwordAccount.getNotes()); //Notes
        initialValues.put(IS_DELETED, "0"); //Deleted
        initialValues.put(CREATED_DATE, TimeStamp.getCurrentTimeStamp()); //Created Date
        initialValues.put(LAST_UPDATED, TimeStamp.getCurrentTimeStamp()); //Last Updated Date

        return this.mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Delete the car with the given rowId
     *
     * @param rowId
     * @return true if deleted, false otherwise
     */
    public boolean deletePasswordAccount(String rowId) {

        ContentValues args = new ContentValues();
        args.put(PasswordManagerDBAdapter.IS_DELETED, "1"); //Deleted
        args.put(PasswordManagerDBAdapter.LAST_UPDATED, TimeStamp.getCurrentTimeStamp()); //Last Updated Date

        return this.mDb.update(DATABASE_TABLE, args, ACCOUNT_ID + "=" + rowId, null) > 0;
    }

    /**
     * Return a Cursor over the list of all password account in the database
     *
     * @return Cursor over all Password Account
     */
    public Cursor getAllPasswordAccount() {

        return this.mDb.query(DATABASE_TABLE, new String[] { ACCOUNT_ID,
                ACCOUNT_NAME, USERNAME, PASSWORD, NOTES }, null, null, null, null, null);
    }

    /**
     * Return a Cursor positioned at the password account that matches the given rowId
     * @param rowId
     * @return Cursor positioned to matching PasswordAccount, if found
     * @throws SQLException if PasswordAccount could not be found/retrieved
     */
    public Cursor getPasswordAccount(String rowId) throws SQLException {

        Cursor mCursor = this.mDb.query(true, DATABASE_TABLE, new String[] { ACCOUNT_ID,
                ACCOUNT_NAME, USERNAME, PASSWORD, NOTES }, ACCOUNT_ID + "=" + rowId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    /**
     * Update the PasswordAccount.
     *
     * @param passwordAccount
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateCar(PasswordAccount passwordAccount){
        ContentValues args = new ContentValues();
        args.put(ACCOUNT_NAME, passwordAccount.getAccountName()); //Account Name
        args.put(USERNAME, passwordAccount.getUserName()); // UserName
        args.put(PASSWORD, passwordAccount.getPassword()); //Password
        args.put(NOTES, passwordAccount.getNotes()); //Notes
        args.put(LAST_UPDATED, TimeStamp.getCurrentTimeStamp()); //Last Updated Date

        return this.mDb.update(DATABASE_TABLE, args, ACCOUNT_ID + "=" + passwordAccount.getAccountId(), null) > 0;
    }

}

