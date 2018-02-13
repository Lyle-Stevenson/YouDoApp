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

    private static final String TAG = "DatabaseHelper";
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

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
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

    //Create a to do task
    public boolean addToDo(Todo todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME_COL, todo.getName());
        values.put(DATE_COL, todo.getDate());

        // insert row
        long result = db.insert(TABLE_TODO, null, values);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public ArrayList getTaskData(){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_TODO;

        Cursor data = db.rawQuery(selectQuery,null);

        //Get tasks from the database
        ArrayList<Todo> tasksList = new ArrayList<>();
        while(data.moveToNext()){
            Todo task = new Todo();
            task.setName(data.getString(data.getColumnIndex("name")));
            task.setDate(data.getString(data.getColumnIndex("date")));
            tasksList.add(task);
        }
        data.close();
        db.close();
        return tasksList;
    }
}
