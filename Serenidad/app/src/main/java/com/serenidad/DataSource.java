package com.serenidad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.util.Log;

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


    public boolean updateHabit(int min,int max,int habitid,String username) {
        boolean didSucceed = false;
        try {


                 ContentValues insertValues = new ContentValues();

                insertValues.put("username", username);
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

    public ArrayList<Habit> getUserHabits(String username) {
        ArrayList<Habit> habits = new ArrayList<Habit>();
        try {
            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String query = "SELECT  habit.habitid,habitname,iconname,min,max,scale,value FROM userhabit" +
                    " join habit on habit.habitid=userhabit.habitid and userhabit.username='"+username+"'" +
                    " left  join userhabitentry on userhabit.habitid=userhabitentry.habitid"  +
                    "          and userhabit.username=userhabitentry.username" +
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

    public void updateHabitProgress(String username, int habitid, int value) {
        try {


          ContentValues insertValues = new ContentValues();

        insertValues.put("username", username);
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

    public boolean insertEmotiont(JournalThoughts j)
    {
        //  String emotiondata[]= s.split(":");

        boolean didSucceed = false;
        try {
            String s=j.getGetBehaviourReaction();
            ContentValues values = new ContentValues();
            //values.put("thoughtid","");
            values.put("username",j.getUserName());
            values.put("emotion",j.getEmotion());
            values.put("thoughtdate",j.getThoughtDate());
            values.put("situation_whom",j.getSituatationWhom());
            values.put("situation_when",j.getSituationWhen());
            values.put("situation_where",j.getSituationWhere());
            values.put("behaviour_afterthought",j.getBehaviourAfterThought());
            values.put("behaviour_reaction",j.getGetBehaviourReaction());
            didSucceed = database.insert("thoughtlog", null, values) > 0;

        }
        catch (Exception e) {
            //Do nothing -will eturn false if there is an exception
            e.printStackTrace();
        }
        return didSucceed;

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

    public ArrayList<Habit> getUserHabitsEntry(String id) {
        ArrayList<Habit> habits = new ArrayList<Habit>();
        try {
            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String query = "SELECT  habit.habitid,habitname,iconname,min,max,scale,value,entrydate FROM userhabit" +
                    " join habit on habit.habitid=userhabit.habitid" +
                    " left  join userhabitentry on userhabit.habitid=userhabitentry.habitid"  +
                    "          and userhabit.username=userhabitentry.username"+
                    " where userhabit.username='"+ id+"'";

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
                newHabit .setDate(cursor.getString(7));
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

    public ArrayList<JournalThoughts> getUserThought(String id) {
        ArrayList<JournalThoughts> Thoughts = new ArrayList<JournalThoughts>();
        try {
            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String query = "SELECT  * FROM thoughtlog" +" where username='"+ id+"'";

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
                Thoughts.add(journalThoughts);
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            Thoughts = new ArrayList<JournalThoughts>();
        }
        return Thoughts;
    }

    public ArrayList<Diary> getUserNote(String id) {
        ArrayList<Diary> Notes = new ArrayList<Diary>();
        try {
            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String query = "SELECT  * FROM thankfuldiary" +" where username='"+ id+"'";

            Cursor cursor = database.rawQuery(query, null);

            Diary note;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                note = new Diary();                                          //1
                note.setAct(cursor.getString  (cursor.getColumnIndex("act")));
                note.setNote(cursor.getString(cursor.getColumnIndex("note")));
                note.setUserName(cursor.getString(cursor.getColumnIndex("username")));
                note.setNoteDate(cursor.getString(cursor.getColumnIndex("notedate")));
                note.setNoteId(cursor.getInt(cursor.getColumnIndex("noteid")));
                Notes.add(note);
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            Notes = new ArrayList<Diary>();
        }
        return Notes;
    }

    public ArrayList<Diary> getUserNoteById(String id) {
        ArrayList<Diary> Notes = new ArrayList<Diary>();
        try {
            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String query = "SELECT  * FROM thankfuldiary" +" where noteid="+ id;

            Cursor cursor = database.rawQuery(query, null);

            Diary note;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                note = new Diary();                                          //1
                note.setAct(cursor.getString  (cursor.getColumnIndex("act")));
                note.setNote(cursor.getString(cursor.getColumnIndex("note")));
                note.setUserName(cursor.getString(cursor.getColumnIndex("username")));
                note.setNoteDate(cursor.getString(cursor.getColumnIndex("notedate")));
                note.setNoteId(cursor.getInt(cursor.getColumnIndex("noteid")));
                Notes.add(note);
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            Notes = new ArrayList<Diary>();
        }
        return Notes;
    }

    public boolean insertCustomHabit(Habit habit,String username) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            ContentValues insertHabitValues = new ContentValues();

            insertHabitValues.put("habitname", habit.getHabitname());
            insertHabitValues.put("iconname", habit.getHabiticon());
            insertHabitValues.put("min", habit.getMin());
            insertHabitValues.put("max", habit.getMax());
            insertHabitValues.put("scale", habit.getScale());
            insertHabitValues.put("iscustom", 1);

            int id = (int) database.insertWithOnConflict("habit", null, insertHabitValues, SQLiteDatabase.CONFLICT_REPLACE);


            ContentValues insertValues = new ContentValues();

            insertValues.put("username", username);
            insertValues.put("habitid", id);
            insertValues.put("isdeleted", 0);


            id = (int) database.insertWithOnConflict("userhabit", null, insertValues, SQLiteDatabase.CONFLICT_REPLACE);




        }
        catch (Exception e) {
            //Do nothing -will return false if there is an exception
            e.printStackTrace();
        }
        return didSucceed;
    }

    public ArrayList<JournalThoughts> getUserThoughtById(String id) {
        ArrayList<JournalThoughts> Thoughts = new ArrayList<JournalThoughts>();
        try {
            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String query = "SELECT  * FROM thoughtlog" +" where thoughtid="+ id;

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
                Thoughts.add(journalThoughts);
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            Thoughts = new ArrayList<JournalThoughts>();
        }
        return Thoughts;
    }


    public boolean authenticateUser(String username, String password) {
        boolean isValidUser = false;
        try {
            String Q = "SELECT * FROM user WHERE username='"
                    + username +"'"
                    + " AND password='" + password+"'";

            Cursor LoginQuery = database.rawQuery(Q, null);

            if (LoginQuery !=null && LoginQuery.getCount()>0) {

                isValidUser = true;
            }
        }
        catch (Exception e) {
            //Do nothing -will eturn false if there is an exception
            e.printStackTrace();
        }
        return isValidUser;
    }

    public void insertUser(String username,String password, String email) {
        try {
            ContentValues insertValues = new ContentValues();
            insertValues.put("username", username);
            insertValues.put("password", password);
            insertValues.put("email", email);
            database.insert("user", null, insertValues);
        }
        catch (Exception e) {
            //Do nothing -will eturn false if there is an exception
            e.printStackTrace();
        }
    }

    public String getEmail(String username){
        String email = "";
        try{

            String Q = "SELECT * FROM user WHERE username='"+username+"'";
            Cursor userData = database.rawQuery(Q,null);
            if(userData!=null ){
                Log.d("Datasource", "getEmail: "+userData.getCount());
                userData.moveToFirst();
                email = userData.getString(userData.getColumnIndex("email"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  email;
    }

    public boolean isUserRegistered(String email) {
        boolean isValidUser = false;
        try {
            String Q = "SELECT * FROM user WHERE email='"
                    + email+"'";

            Cursor LoginQuery = database.rawQuery(Q, null);

            if (LoginQuery!=null && LoginQuery.getCount()==0) {
                isValidUser = true;
            }
        }
        catch (Exception e) {
            //Do nothing -will eturn false if there is an exception
            e.printStackTrace();
        }
        return isValidUser;
    }

    public void updateUserPass(String password,String email) {
        try {
            //String Q = "update user set password='" + password+"'  WHERE email='"
            //        + email+"'";

            ContentValues cv = new ContentValues();
            cv.put("password",password);

            database.update("user", cv, "email='" + email+"'", null);


        }
        catch (Exception e) {
            //Do nothing -will eturn false if there is an exception
            e.printStackTrace();
        }
    }

}
