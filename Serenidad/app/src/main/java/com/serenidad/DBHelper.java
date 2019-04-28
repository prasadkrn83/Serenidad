package com.serenidad;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "serenidad.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String CREATE_TABLE_HABIT =
       "create table habit (habitid integer primary key autoincrement, "
            + "habitname text not null, iconname text, "
            + "min integer, max integer, scale text,iscustom int); ";
    private static String INSERT_STANDARD_HABIT_EATING="insert into habit values(1,'Eating','eating',600,1500,'Calories','N');" ;
    private static String INSERT_STANDARD_HABIT_EXERCISE="insert into habit values(2,'Exercise','exercise',20,45,'Minutes','N');" ;
    private static String INSERT_STANDARD_HABIT_ALCOHOL="insert into habit values(3,'Alcohol','alcohol',1,2,'Drinks','N');" ;
    private static String INSERT_STANDARD_HABIT_PETS="insert into habit values(4,'Pets','pets',15,30,'Minutes','N');" ;
    private static String INSERT_STANDARD_HABIT_HOBBIES="insert into habit values(5,'Hobbies','hobbies',20,45,'Minutes','N');" ;
    private static String INSERT_STANDARD_HABIT_MEDS="insert into habit values(6,'Medicine','medicine',1,1,'Tablets','N');" ;
    private static String INSERT_STANDARD_HABIT_SM="insert into habit values(7,'Social Media','socialmedia',15,30,'Minutes','N');" ;


    //" insert into habit values('Exercie','exercise.png',20,45,'Minutes','N');";

    private static final String CREATE_TABLE_USER_HABIT =
            "create table userhabit (habitid integer , "
                    + "userid integer,isdeleted int," +
                    " primary key(habitid,userid)," +
                    " foreign key(habitid) references habit(habitid)); ";


    private static final String CREATE_TABLE_USER_THOUGHT_LOG =
            "create table thoughtlog (thoughtid integer , username text," +
                    "emotion text,feeling text, thoughtdate datetime, situation_whom text, " +
                    "situation_when text, situation_where text, behaviour_afterthought text," +
                    "behaviour_reaction text, primary key(thoughtid))";

    private static final String CREATE_TABLE_USER_THANKFUL_DIARY =
            "create table thankfuldiary (noteid INTEGER PRIMARY KEY AUTOINCREMENT, username text," +
                    "note text,act text, notedate datetime)";

    private static final String CREATE_TABLE_USER_HABIT_ENTRY =
            "create table userhabitentry (userid integer ,habitid integer, "
                    + "entrydate date, value int," +
                    " primary key(userid,habitid,entrydate)," +
                    " foreign key(habitid) references habit(habitid)); ";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_HABIT);
        database.execSQL(CREATE_TABLE_USER_HABIT);
        database.execSQL(CREATE_TABLE_USER_HABIT_ENTRY);
        database.execSQL(CREATE_TABLE_USER_THOUGHT_LOG);
        database.execSQL(CREATE_TABLE_USER_THANKFUL_DIARY);
        database.execSQL(INSERT_STANDARD_HABIT_EATING);
        database.execSQL(INSERT_STANDARD_HABIT_EXERCISE);
        database.execSQL(INSERT_STANDARD_HABIT_ALCOHOL);
        database.execSQL(INSERT_STANDARD_HABIT_PETS);
        database.execSQL(INSERT_STANDARD_HABIT_HOBBIES);
        database.execSQL(INSERT_STANDARD_HABIT_MEDS);
        database.execSQL(INSERT_STANDARD_HABIT_SM);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onCreate(db);
    }



}
