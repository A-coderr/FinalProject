package com.example.ak_lg_sa_finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * This class is used to handle database operations including creating database, table, and CRUD
 * @author Sara Asefi
 * @version 1.0
 */
public class DbOperations extends SQLiteOpenHelper {
    private static final int DB_version = 1;
    private static final String DB_name = "person_info.db";

    // creating a constant variables for our database
    public static final String PERSON_TABLE = "PERSON_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_FNAME = "FIRST_NAME";
    public static final String COLUMN_LNAME = "LAST_NAME";
    public static final String COLUMN_PHONE = "PHONE_NUMBER";
    public static final String COLUMN_EMAIL = "EMAIL_ADDRESS";
    public static final String COLUMN_NOTE = "NOTE";

    // Constructor for Database Helper
    public DbOperations(Context ctx) {
        super(ctx, DB_name, null, DB_version);
    }

    /**
     * creating a database by running a sqlite query
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createPersonTable = "CREATE TABLE " + PERSON_TABLE + " (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_FNAME + " TEXT,"
                + COLUMN_LNAME + " TEXT,"+ COLUMN_PHONE + " TEXT,"
                + COLUMN_EMAIL + " TEXT," + COLUMN_NOTE + " TEXT)";

        db.execSQL(createPersonTable);

    }

    /**
     * if newer version of database is available android executes onUpgrade method.
     * @param db
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " +PERSON_TABLE);
        onCreate(db);
    }

    /**
     * add new Person Record to SQLite Database
     * @param personModel
     */
    public void addRecord(PersonModel personModel){

        // calling writable method as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // ContentValues stores the Key Value pair for column name and its data
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_FNAME,personModel.getFirstName());
        cv.put(COLUMN_LNAME,personModel.getLastName());
        cv.put(COLUMN_PHONE,personModel.getPhoneNumber());
        cv.put(COLUMN_EMAIL,personModel.getEmail());
        cv.put(COLUMN_NOTE,personModel.getNote());

        // insert content values to our Student table.
        db.insert(PERSON_TABLE, null, cv);

        // close the Database connection
        db.close();
    }

    /**
     * Read data from Person Table
     * @return
     */
    public List<PersonModel> viewRecords(){

        ArrayList<PersonModel> viewList = new ArrayList<>();

        String queryString = "SELECT * FROM " + PERSON_TABLE;

        // calling a method to get readable database.
        SQLiteDatabase db = this.getReadableDatabase();

        //creating a cursor to loops through table and reads data
        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst())
        {
            do {
                int personID = cursor.getInt(0);
                String first_name = cursor.getString(1);
                String last_name = cursor.getString(2);
                String phone = cursor.getString((3));
                String email = cursor.getString(4);
                String note = cursor.getString(5);

                PersonModel personModel = new PersonModel(personID,first_name, last_name,
                                                            phone,email,note);
                //adding data (Person object) to arraylist
                viewList.add(personModel);

            }while (cursor.moveToNext());

        }
        return viewList;
    }

    /**
     * Update a Person Record
     * @param ID
     * @param f_name
     * @param l_name
     * @param phone
     * @param email
     * @param note
     */
    public void updateRecord(int ID, String f_name, String l_name, String phone,
                             String email, String note) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // passing all values along with its key and value pair.
        values.put(COLUMN_FNAME, f_name);
        values.put(COLUMN_LNAME, l_name);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_NOTE, note);

        String where = COLUMN_ID +"= ?";
        String[] whereArgs = {String.valueOf(ID)};

        // calling a update method to update our database and passing our values.
        db.update(PERSON_TABLE, values,where,whereArgs);
        db.close();
    }

    /**
     * Delete a record from Person Table
     * @param id
     */
    public void deleteRecord(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(PERSON_TABLE, "ID=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
