package com.serenidad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


public class DataSource {

    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public DataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertCustomHabit() {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            didSucceed = database.insert("habit", null, initialValues) > 0;
        }
        catch (Exception e) {
            //Do nothing -will eturn false if there is an exception
            e.printStackTrace();
        }
        return didSucceed;
    }

    public boolean updateHabit(int min,int max,int habitid,int userId) {
        boolean didSucceed = false;
        try {


                 ContentValues insertValues = new ContentValues();

                insertValues.put("userid", userId);
                insertValues.put("habitid", habitid);
                insertValues.put("isdeleted", 0);


            int id = (int) database.insertWithOnConflict("userhabit", null, insertValues, SQLiteDatabase.CONFLICT_REPLACE);


            ContentValues updateValues = new ContentValues();

            updateValues.put("min", min);
            updateValues.put("max", max);
            didSucceed = database.update("habit", updateValues, "habitid=" + habitid, null) > 0;


        }
        catch (Exception e) {
            //Do nothing -will return false if there is an exception
            e.printStackTrace();
        }
        return didSucceed;
    }

    public boolean deleteContact(int contactId) {
        boolean didDelete = false;
        try {
            didDelete = database.delete("contact", "_id=" + contactId, null) > 0;
        }
        catch (Exception e) {
            //Do nothing -return value already set to false
            e.printStackTrace();
        }
        return didDelete;
    }

    public ArrayList<Habit> getDefaultHabits() {
        ArrayList<Habit> habits = new ArrayList<Habit>();
        try {
            String query = "SELECT  * FROM Habit  where iscustom='N'";

            Cursor cursor = database.rawQuery(query, null);

            Habit newHabit;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                newHabit = new Habit();                                          //1
                newHabit .setHabitid(cursor.getInt(0));
                newHabit .setHabitname(cursor.getString(1));
                newHabit .setHabiticon(cursor.getString(2));
                newHabit .setMin(cursor.getInt(3));
                newHabit .setMax(cursor.getInt(4));
                newHabit .setScale(cursor.getString(5));
                newHabit .setIsCustom(cursor.getInt(6));

                habits.add(newHabit);
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            habits = new ArrayList<Habit>();
        }
        return habits;
    }

    public ArrayList<Habit> getUserHabits() {
        ArrayList<Habit> habits = new ArrayList<Habit>();
        try {
            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String query = "SELECT  habit.habitid,habitname,iconname,min,max,scale,value FROM userhabit" +
                    " join habit on habit.habitid=userhabit.habitid" +
                    " left  join userhabitentry on userhabit.habitid=userhabitentry.habitid"  +
                    "          and userhabit.userid=userhabitentry.userid" +
                    "          and entrydate='"+today+"'"+
                    " where isdeleted=0" ;

            /*String query = "SELECT  habit.habitid,habitname,iconname,min,max,scale,value" +
                    " FROM Habit,userhabit,userhabitentry " +
                    " where userhabit.habitid=habit.habitid" +
                    " and isdeleted=0" +
                    " and userhabit.habitid=userhabitentry.habitid" +
                    " and userhabit.userid=userhabitentry.userid" +
                    " and entrydate='"+today+"'";
*/
            Cursor cursor = database.rawQuery(query, null);

            Habit newHabit;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                newHabit = new Habit();                                          //1
                newHabit .setHabitid(cursor.getInt(0));
                newHabit .setHabitname(cursor.getString(1));
                newHabit .setHabiticon(cursor.getString(2));
                newHabit .setMin(cursor.getInt(3));
                newHabit .setMax(cursor.getInt(4));
                newHabit .setScale(cursor.getString(5));
                newHabit .setValue(cursor.getInt(6));
                habits.add(newHabit);
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            habits = new ArrayList<Habit>();
        }
        return habits;
    }

    public ArrayList<JournalThoughts> getUserThoughtLog(String Uname, String _date) {
        ArrayList<JournalThoughts> _journalThought = new ArrayList<JournalThoughts>();
        try {
            //String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String query = "SELECT  * FROM thoughtlog where username="+ Uname + " and thoughtdate=" + _date;

            Cursor cursor = database.rawQuery(query, null);

            JournalThoughts journalThoughts;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                journalThoughts = new JournalThoughts();                                          //1
                journalThoughts.setEmotion(cursor.getString  (cursor.getColumnIndex("emotion")));
                journalThoughts.setThoughtId(cursor.getInt(cursor.getColumnIndex("thoughtid")));
                journalThoughts.setUserName(cursor.getString(cursor.getColumnIndex("username")));
                journalThoughts.setFeelings(cursor.getString(cursor.getColumnIndex("feeling")));
                journalThoughts.setThoughtDate(cursor.getString(cursor.getColumnIndex("thoughtdate")));
                journalThoughts.setSituatationWhom(cursor.getString(cursor.getColumnIndex("situation_whom")));
                journalThoughts.setSituationWhen(cursor.getString(cursor.getColumnIndex("situation_when")));
                journalThoughts.setSituationWhere(cursor.getString(cursor.getColumnIndex("situation_where")));
                journalThoughts.setBehaviourAfterThought(cursor.getString(cursor.getColumnIndex("behaviour_afterthought")));
                journalThoughts.setGetBehaviourReaction(cursor.getString(cursor.getColumnIndex("behaviour_reaction")));
                _journalThought.add(journalThoughts);
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            _journalThought = new ArrayList<JournalThoughts>();
        }
        return _journalThought;
    }

    public boolean deleteUserHabit(int selectedHabitId) {
        boolean didSucceed=false;
        try {


            ContentValues updateValues = new ContentValues();
            updateValues.put("isdeleted", 1);

            didSucceed = database.update("userhabit", updateValues, "habitid=" + selectedHabitId, null) > 0;


        }
        catch (Exception e) {
            //Do nothing -will return false if there is an exception
            e.printStackTrace();
        }
        return didSucceed;
    }

    public void updateHabitProgress(int userId, int habitid, int value) {
        try {


          ContentValues insertValues = new ContentValues();

        insertValues.put("userid", userId);
        insertValues.put("habitid", habitid);
        insertValues.put("value", value);
        insertValues.put("entrydate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));


            int id = (int) database.insertWithOnConflict("userhabitentry", null, insertValues, SQLiteDatabase.CONFLICT_REPLACE);
        }
        catch (Exception e) {
            //Do nothing -will return false if there is an exception
            e.printStackTrace();
        }


    }

    public boolean InsertNote(String userId, String note, String act) {
        boolean didSucceed = false;
        try {
            //Date currentTime = java.util.Calendar.getInstance().getTime();
            ContentValues initialValues = new ContentValues();
            initialValues.put("username", userId);
            initialValues.put("note", note);
            initialValues.put("act", act);
            initialValues.put("notedate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

            didSucceed = database.insert("thankfuldiary", null, initialValues) > 0;
        }
        catch (Exception e) {
            //Do nothing -will eturn false if there is an exception
            e.printStackTrace();
        }
        return didSucceed;
    }
}
