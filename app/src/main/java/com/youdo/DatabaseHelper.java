package com.youdo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import com.youdo.Calendar.Event;
import com.youdo.ImportantDates.Date;
import com.youdo.Schedule.ScheduleItem;
import com.youdo.TodoList.Task;

/**
 * Created by Lyle on 12/02/2018.
 * Fixed by Callum on Valentines day. (I know, how romantic)
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String TAG = "DatabaseHelper";
    //Databasename
    private static final String DATABASE_NAME = "YouDo";

    //Table names
    private static final String TABLE_TODO = "tasks";
    private static final String TABLE_EVENT = "events";
    private static final String TABLE_SCHEDULE = "schedule";
    private static final String TABLE_GOALS = "goals";
    private static final String TABLE_DATES = "dates";

    //Common column names
    private static final String ID_COL = "ID";
    private static final String NAME_COL = "name";
    private static final String DATE_COL = "date";
    private static final String DESCR_COL = "description";
    private static final String TIME_COL = "time";
    private static final String END_TIME_COL = "start_time";
    private static final String START_TIME_COL = "end_time";
    private static final String DAY_COL = "day";
    private static final String TASK_CAT_COL = "category";

    // To do table create statement
    private static final String CREATE_TABLE_TODO = "CREATE TABLE "
            + TABLE_TODO + "(" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME_COL
            + " TEXT," + TASK_CAT_COL + " TEXT," + DATE_COL + " DATETIME" + ")";

    private static final String CREATE_TABLE_GOALS = "CREATE TABLE "
            + TABLE_GOALS + "(" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME_COL
            + " TEXT" + ")";

    // Event table create statement
    private static final String CREATE_TABLE_EVENTS = "CREATE TABLE "
            + TABLE_EVENT+ "(" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME_COL
            + " TEXT," + DATE_COL + " TEXT," + TIME_COL + " TEXT," + DESCR_COL + " TEXT" + ")";

    // Event table create statement
    private static final String CREATE_TABLE_DATES = "CREATE TABLE "
            + TABLE_DATES+ "(" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME_COL
            + " TEXT," + DATE_COL + " TEXT" + ")";

    // com.youdo.Schedule table create statement
    private static final String CREATE_TABLE_SCHEDULE = "CREATE TABLE "
            + TABLE_SCHEDULE + "(" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME_COL
            + " TEXT," + DAY_COL + " TEXT," + START_TIME_COL + " TEXT," + END_TIME_COL + " TEXT" + ")";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TODO);
        db.execSQL(CREATE_TABLE_EVENTS);
        db.execSQL(CREATE_TABLE_SCHEDULE);
        db.execSQL(CREATE_TABLE_GOALS);
        db.execSQL(CREATE_TABLE_DATES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEDULE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOALS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATES);

        onCreate(db);
    }

    //Create a to do task
    public boolean addToDo(Task todo) {
        SQLiteDatabase db = this.getWritableDatabase(); //Gets a writeable reference to the db

        ContentValues values = new ContentValues();
        values.put(NAME_COL, todo.getName());
        values.put(TASK_CAT_COL,todo.getCat());
        values.put(DATE_COL, todo.getDate());
        // insert row
        long result = db.insert(TABLE_TODO, null, values);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    //Create a to do task
    public void deleteTodo(String todo) {
        SQLiteDatabase db = this.getWritableDatabase(); //Gets a writeable reference to the db

        String query = "DELETE FROM " + TABLE_TODO + " WHERE " + NAME_COL + " = '" + todo +"'";

        // insert row
        db.execSQL(query);
    }

    //Create a to do task
    public boolean addGoal(String goal) {
        SQLiteDatabase db = this.getWritableDatabase(); //Gets a writeable reference to the db

        ContentValues values = new ContentValues();
        values.put(NAME_COL, goal);
        // insert row
        long result = db.insert(TABLE_GOALS, null, values);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    //Create a to do task
    public void deleteGoals(String goal) {
        SQLiteDatabase db = this.getWritableDatabase(); //Gets a writeable reference to the db

        String query = "DELETE FROM " + TABLE_GOALS + " WHERE " + NAME_COL + " = '" + goal +"'";

        // insert row
        db.execSQL(query);
    }

    public ArrayList getGoalData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery;

        selectQuery = "SELECT  * FROM " + TABLE_GOALS;

        Cursor data = db.rawQuery(selectQuery,null);

        //Get tasks from the database
        ArrayList<String> goalList = new ArrayList<>();
        while(data.moveToNext()){
            goalList.add(data.getString(data.getColumnIndex("name")));
        }
        data.close();
        db.close();
        return goalList;
    }

    //Create a to do task
    public boolean addDate(Date date) {
        SQLiteDatabase db = this.getWritableDatabase(); //Gets a writeable reference to the db

        ContentValues values = new ContentValues();
        values.put(NAME_COL, date.getName());
        values.put(DATE_COL,date.getDate());
        // insert row
        long result = db.insert(TABLE_DATES, null, values);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    //Create a to do task
    public void deleteDate(Date date) {
        SQLiteDatabase db = this.getWritableDatabase(); //Gets a writeable reference to the db

        String query = "DELETE FROM " + TABLE_DATES + " WHERE " + NAME_COL + " = '" + date.getName() +"'";

        // insert row
        db.execSQL(query);
    }

    public ArrayList getDateData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery;

        selectQuery = "SELECT  * FROM " + TABLE_DATES;

        Cursor data = db.rawQuery(selectQuery,null);

        //Get tasks from the database
        ArrayList<Date> dateList = new ArrayList<>();
        while(data.moveToNext()){
            Date date = new Date();
            date.setName(data.getString(data.getColumnIndex("name")));
            date.setDate(data.getString(data.getColumnIndex("date")));
            dateList.add(date);
        }
        data.close();
        db.close();
        return dateList;
    }

    public boolean addEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase(); //Gets a writeable reference to the db

        ContentValues values = new ContentValues();
        values.put(NAME_COL, event.getName());
        values.put(DESCR_COL, event.getDescription());
        values.put(DATE_COL, event.getDate());
        values.put(TIME_COL, event.getTime());

        // insert row
        long result = db.insert(TABLE_EVENT, null, values);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public ArrayList getTaskData(String selectedFilter){
        SQLiteDatabase db = this.getReadableDatabase();
String selectQuery;

        if(selectedFilter == ""){
        selectQuery = "SELECT  * FROM " + TABLE_TODO;
        }else{
            selectQuery = "SELECT  * FROM " + TABLE_TODO + " WHERE category ='" + selectedFilter +"'";
        }

        Cursor data = db.rawQuery(selectQuery,null);

        //Get tasks from the database
        ArrayList<Task> tasksList = new ArrayList<>();
        while(data.moveToNext()){
            Task task = new Task();
            task.setName(data.getString(data.getColumnIndex("name")));
            task.setCat(data.getString(data.getColumnIndex("category")));
            task.setDate(data.getString(data.getColumnIndex("date")));
            tasksList.add(task);
        }
        data.close();
        db.close();
        return tasksList;
    }

    public ArrayList getEventData(String date){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_EVENT + " WHERE date = '" + date +"'";
        Log.d("com/youdo/Calendar",selectQuery);
        Cursor data = db.rawQuery(selectQuery,null);

        //Get tasks from the database
        ArrayList<Event> eventList = new ArrayList<>();

        while(data.moveToNext()){
            Event event = new Event();
            event.setName(data.getString(data.getColumnIndex("name")));
            event.setDescription(data.getString(data.getColumnIndex("description")));
            event.setTime(data.getString(data.getColumnIndex("time")));
            event.setDate(data.getString(data.getColumnIndex("date")));
            eventList.add(event);
        }
        data.close();
        db.close();
        return eventList;
    }

    public boolean addScheduleItem(ScheduleItem newItem, String day) {

        SQLiteDatabase db = this.getWritableDatabase(); //Gets a writeable reference to the db

        ContentValues values = new ContentValues();
        values.put(NAME_COL, newItem.getName());
        values.put(START_TIME_COL, newItem.getStart());
        values.put(END_TIME_COL, newItem.getEnd());
        values.put(DAY_COL, day);

        // insert row
        long result = db.insert(TABLE_SCHEDULE, null, values);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public ArrayList getScheduleData(String day){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_SCHEDULE + " WHERE day = '" + day +"'";
        Log.d("Query", selectQuery);
        Cursor data = db.rawQuery(selectQuery,null);
        Log.d("Query", data.toString());
        //Get tasks from the database
        ArrayList<ScheduleItem> itemList = new ArrayList<>();

        while(data.moveToNext()){
            ScheduleItem item = new ScheduleItem();
            item.setName(data.getString(data.getColumnIndex("name")));
            item.setStart(data.getString(data.getColumnIndex("start_time")));
            item.setEnd(data.getString(data.getColumnIndex("end_time")));
            itemList.add(item);
        }
        data.close();
        db.close();
        return itemList;
    }
}
