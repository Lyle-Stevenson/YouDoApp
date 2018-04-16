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

public class DatabaseHelper extends SQLiteOpenHelper{

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
    private static final String END_TIME_COL = "end_time";
    private static final String START_TIME_COL = "start_time";
    private static final String DAY_COL = "day";
    private static final String TASK_CAT_COL = "category";

    //Create table statements
    private static final String CREATE_TABLE_TODO = "CREATE TABLE "
            + TABLE_TODO + "(" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME_COL
            + " TEXT," + TASK_CAT_COL + " TEXT," + DATE_COL + " DATETIME" + ")";

    private static final String CREATE_TABLE_GOALS = "CREATE TABLE "
            + TABLE_GOALS + "(" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME_COL
            + " TEXT" + ")";

    private static final String CREATE_TABLE_EVENTS = "CREATE TABLE "
            + TABLE_EVENT+ "(" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME_COL
            + " TEXT," + DATE_COL + " TEXT," + TIME_COL + " TEXT" + ")";

    private static final String CREATE_TABLE_DATES = "CREATE TABLE "
            + TABLE_DATES+ "(" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME_COL
            + " TEXT," + DATE_COL + " TEXT" + ")";

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
    public void addToDo(Task todo) {
        SQLiteDatabase db = this.getWritableDatabase(); //Gets a writeable reference to the db

        //Put the values from the passed in task into a values object that database understands.
        ContentValues values = new ContentValues();
        values.put(NAME_COL, todo.getName());
        values.put(TASK_CAT_COL,todo.getCat());
        values.put(DATE_COL, todo.getDate());

        db.insert(TABLE_TODO, null, values);
    }

    //Delete a to do task
    public void deleteTodo(String todo) {
        SQLiteDatabase db = this.getWritableDatabase(); //Gets a writeable reference to the db

        //Set up delete query
        String query = "DELETE FROM " + TABLE_TODO + " WHERE " + NAME_COL + " = '" + todo +"'";

        //execute delete query
        db.execSQL(query);
    }

    //Fetch all to do task from database
    public ArrayList getTaskData(String selectedFilter){
        SQLiteDatabase db = this.getReadableDatabase(); // Get readable version of database

        String selectQuery;

        if(selectedFilter == ""){ //If the selected filer is black dont use filtered search
            selectQuery = "SELECT  * FROM " + TABLE_TODO;
        }else{ // If a filter was selected on fetch filtered category objects.
            selectQuery = "SELECT  * FROM " + TABLE_TODO + " WHERE category ='" + selectedFilter +"'";
        }

        //Put fetched rows in cursor object
        Cursor data = db.rawQuery(selectQuery,null);

        //Array list to hold all task.
        ArrayList<Task> tasksList = new ArrayList<>();
        while(data.moveToNext()){ // Whilst there is a new row to look at
            //Convert row data into a task object.
            Task task = new Task();
            task.setName(data.getString(data.getColumnIndex("name")));
            task.setCat(data.getString(data.getColumnIndex("category")));
            task.setDate(data.getString(data.getColumnIndex("date")));
            tasksList.add(task);
        }

        //Close connections to the database.
        data.close();
        db.close();

        return tasksList;
    }

    //Fetch what to do list categories currently exist to populate the dropdown with.
    public ArrayList getCat(){
        SQLiteDatabase db = this.getReadableDatabase(); //Get readable version of db

        //Query to fetch all unique category names.
        String selectQuery = "SELECT DISTINCT " +  TASK_CAT_COL + " FROM " + TABLE_TODO;

        //Set up cursor object to hold the fetched rows.
        Cursor data = db.rawQuery(selectQuery,null);

        //Array list to hold categories
        ArrayList<String> catList = new ArrayList<>();
        while(data.moveToNext()){ //Whilst there is a new row to be read.
            //Convert the cursor object into a string object.
            catList.add(data.getString(data.getColumnIndex("category")));
        }

        //Close database connections
        data.close();
        db.close();

        return catList;
    }

    //Create a goal
    public void addGoal(String goal) {
        SQLiteDatabase db = this.getWritableDatabase(); //Gets a writeable reference to the db

        //Put the values from the passed in into a values object that database understands.
        ContentValues values = new ContentValues();
        values.put(NAME_COL, goal);
        // insert row
        db.insert(TABLE_GOALS, null, values);
    }

    //Delete a goal
    public void deleteGoals(String goal) {
        SQLiteDatabase db = this.getWritableDatabase(); //Gets a writeable reference to the db

        String query = "DELETE FROM " + TABLE_GOALS + " WHERE " + NAME_COL + " = '" + goal +"'";

        // insert row
        db.execSQL(query);
    }

    //Fetch all goals in database
    public ArrayList getGoalData(){
        SQLiteDatabase db = this.getReadableDatabase(); //Get readable version of the database

        //Set up query string
        String selectQuery = "SELECT  * FROM " + TABLE_GOALS;

        //Add fetched rows to a cursor object
        Cursor data = db.rawQuery(selectQuery,null);

        //Array to store the goals in
        ArrayList<String> goalList = new ArrayList<>();
        while(data.moveToNext()){ //While the database has a new rwo to read.
            goalList.add(data.getString(data.getColumnIndex("name"))); //Convert data from cursor object to string.
        }
        //Close database connection
        data.close();
        db.close();

        return goalList;
    }

    //Add a date to the database
    public void addDate(Date date) {
        SQLiteDatabase db = this.getWritableDatabase(); //Gets a writeable reference to the db

        //Put the values passed in into a values object that database understands.
        ContentValues values = new ContentValues();
        values.put(NAME_COL, date.getName());
        values.put(DATE_COL,date.getDate());

        //Insert new row into database
        db.insert(TABLE_DATES, null, values);
    }

    //Delete dates from the database.
    public void deleteDate(Date date) {
        SQLiteDatabase db = this.getWritableDatabase(); //Gets a writeable reference to the db

        String query = "DELETE FROM " + TABLE_DATES + " WHERE " + NAME_COL + " = '" + date.getName() +"'";

        // insert row
        db.execSQL(query);
    }

    //Fetch all dates from database.
    public ArrayList getDateData(){
        SQLiteDatabase db = this.getReadableDatabase(); // Get readable reference to databsase.

        //Set up the query
        String selectQuery = "SELECT  * FROM " + TABLE_DATES;

        //Transfer fetch rows into a cursor object
        Cursor data = db.rawQuery(selectQuery,null);

        //Set up array list to hold dates.
        ArrayList<Date> dateList = new ArrayList<>();
        while(data.moveToNext()){ //Whilst a row is still to be read
            //Transfer row data into a date object.
            Date date = new Date();
            date.setName(data.getString(data.getColumnIndex("name")));
            date.setDate(data.getString(data.getColumnIndex("date")));
            dateList.add(date);
        }

        //Close database connections
        data.close();
        db.close();

        return dateList;
    }

    //Add event to the database
    public void addEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase(); //Gets a writeable reference to the db

        //Put the values passed into a values object that database understands.
        ContentValues values = new ContentValues();
        values.put(NAME_COL, event.getName());
        values.put(DATE_COL, event.getDate());
        values.put(TIME_COL, event.getTime());

        // insert row
        db.insert(TABLE_EVENT, null, values);
    }

    //Fetch all events for a given date.
    public ArrayList getEventData(String date){
        SQLiteDatabase db = this.getReadableDatabase(); //Readable version of database.

        //Query to fetch events on a given date.
        String selectQuery = "SELECT * FROM " + TABLE_EVENT + " WHERE date = '" + date +"'";

        //Cursor to hold queried data.
        Cursor data = db.rawQuery(selectQuery,null);

        //Array list to hold events
        ArrayList<Event> eventList = new ArrayList<>();

        while(data.moveToNext()){//Whilst new event is to be read in.
            //Convert cursor data into event objects.
            Event event = new Event();
            event.setName(data.getString(data.getColumnIndex("name")));
            event.setTime(data.getString(data.getColumnIndex("time")));
            event.setDate(data.getString(data.getColumnIndex("date")));
            eventList.add(event);
        }

        //Close database connections.
        data.close();
        db.close();

        return eventList;
    }

    //Add schedule items to database on given day.
    public void addScheduleItem(ScheduleItem newItem, String day) {
        SQLiteDatabase db = this.getWritableDatabase(); //Gets a writeable reference to the db

        //Put the values passed in into a values object that database understands.
        ContentValues values = new ContentValues();
        values.put(NAME_COL, newItem.getName());
        values.put(START_TIME_COL, newItem.getStart());
        values.put(END_TIME_COL, newItem.getEnd());
        values.put(DAY_COL, day);

        // insert row
        db.insert(TABLE_SCHEDULE, null, values);
    }

    //Update current schedule items with new tasks.
    public void updateScheduleItem(ScheduleItem newItem, String day) {

        SQLiteDatabase db = this.getWritableDatabase(); //Gets a writeable reference to the db

        //Query to update selected schedule timeslot.
        String query = "UPDATE " + TABLE_SCHEDULE + " SET " + NAME_COL + " = '" + newItem.getName() +
                "' WHERE " + START_TIME_COL +  " = '" + newItem.getStart()+
                "' AND " + END_TIME_COL + " = '" + newItem.getEnd() +
                "' AND " + DAY_COL + " = '" + day +
                "'";

        db.execSQL(query);
    }

    //Fetches all schedule data.
    public ArrayList getScheduleData(String day){
        SQLiteDatabase db = this.getReadableDatabase(); //REadable version of database.

        //Set up query to fetch all schedule data on given day.
        String selectQuery = "SELECT * FROM " + TABLE_SCHEDULE + " WHERE day = '" + day +"'";

        //Cursor object to hold all queried rows.
        Cursor data = db.rawQuery(selectQuery,null);

        //Arraylist to hold scheduled item.
        ArrayList<ScheduleItem> itemList = new ArrayList<>();

        while(data.moveToNext()){
            //Convert cursor data to shedule item objects.
            ScheduleItem item = new ScheduleItem();
            item.setName(data.getString(data.getColumnIndex("name")));
            item.setStart(data.getString(data.getColumnIndex("start_time")));
            item.setEnd(data.getString(data.getColumnIndex("end_time")));
            itemList.add(item);
        }

        //Close database connections
        data.close();
        db.close();

        return itemList;
    }
}
