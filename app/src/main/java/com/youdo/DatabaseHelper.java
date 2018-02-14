package com.youdo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Lyle on 12/02/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    //Databasename
    private static final String DATABASE_NAME = "YouDo";

    //Table names
    private static final String TABLE_TODO = "tasks";
    private static final String TABLE_EVENT = "events";

    //Common column names
    private static final String ID_COL = "ID";
    private static final String NAME_COL = "name";
    private static final String DATE_COL = "date";

    // To do table create statement
    private static final String CREATE_TABLE_TODO = "CREATE TABLE "
            + TABLE_TODO + "(" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME_COL
            + " TEXT,"  + DATE_COL + " DATETIME" + ")";

    // Event table create statement
    private static final String CREATE_TABLE_EVENTS = "CREATE TABLE "
            + TABLE_EVENT+ "(" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME_COL
            + " TEXT,"  + DATE_COL + " DATETIME" + ")";

    //Constructor for the databasehelper class
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //When the database helper is created it checks for the databses and creates them if they don't exist
        db.execSQL(CREATE_TABLE_TODO);
        db.execSQL(CREATE_TABLE_EVENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);

        onCreate(db);
    }

    //adds a to do task to the database
    public boolean addToDo(Todo todo) {
        SQLiteDatabase db = this.getWritableDatabase(); //Returns a writable version of the database

        ContentValues values = new ContentValues(); //Content values is used to assign input values to the db columns
        values.put(NAME_COL, todo.getName()); //Gets the to do task name and assigns it to name column in db
        values.put(DATE_COL, todo.getDate()); //Gets the to do task date and assigns it to date column in db

        // inserts row into the db
        long result = db.insert(TABLE_TODO, null, values);

        if(result == -1){ //If the insert return -1 then the insert didnt work
            return false;
        }else{
            return true;
        }
    }

    //Fetches data from the database
    public ArrayList getTaskData(){
        SQLiteDatabase db = this.getReadableDatabase(); //Returns a readable version of the database

        String selectQuery = "SELECT  * FROM " + TABLE_TODO; // Sets up select query to pull all records

        Cursor data = db.rawQuery(selectQuery,null); //Cursor is a handler for the data form that sql returns

        //Get tasks from the database
        ArrayList<Todo> tasksList = new ArrayList<>(); //Sets up an array list to store the tasks in
        while(data.moveToNext()){ //Whilst the cursor still has rows of the table to return
            Todo task = new Todo(); //Create a to do object
            task.setName(data.getString(data.getColumnIndex("name"))); //Set the task name to the data returned from the name column
            task.setDate(data.getString(data.getColumnIndex("date"))); //Set the task date to the data returned from the date column
            tasksList.add(task); // Adds the task from the database to the array list we set up.
        }

        //Close the connections to the database
        data.close();
        db.close();

        return tasksList;
    }
}
