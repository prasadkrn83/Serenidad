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
    private static String INSERT_STANDARD_HABIT_EATING="insert into habit values(1,'Comiendo','eating',600,1500,'Calorías','N');" ;
    private static String INSERT_STANDARD_HABIT_EXERCISE="insert into habit values(2,'Ejercicio','exercise',20,45,'Minutos','N');" ;
    private static String INSERT_STANDARD_HABIT_ALCOHOL="insert into habit values(3,'Alcohol','alcohol',1,2,'Las bebidas','N');" ;
    private static String INSERT_STANDARD_HABIT_PETS="insert into habit values(4,'Mascotas','pets',15,30,'Minutos','N');" ;
    private static String INSERT_STANDARD_HABIT_HOBBIES="insert into habit values(5,'Aficiones','hobbies',20,45,'Minutos','N');" ;
    private static String INSERT_STANDARD_HABIT_MEDS="insert into habit values(6,'Medicina','medicine',1,1,'Pastillas','N');" ;
    private static String INSERT_STANDARD_HABIT_SM="insert into habit values(7,'Medios de social','socialmedia',15,30,'Minutos','N');" ;
    private static String INSERT_Thought="insert into thoughtlog values('1','Awesome','sad','depressed','04/29/2019'," +
                                        "'Friend','Morining','at Home','Happy','Motivated');" ;

    //" insert into habit values('Exercie','exercise.png',20,45,'Minutes','N');";

    private static final String CREATE_TABLE_USER_HABIT =
            "create table userhabit (habitid integer , "
                    + "username text,isdeleted int," +
                    " primary key(habitid,username)," +
                    " foreign key(habitid) references habit(habitid)); ";


    private static final String CREATE_TABLE_USER_THOUGHT_LOG =
            "create table thoughtlog (thoughtid INTEGER PRIMARY KEY AUTOINCREMENT , username text," +
                    "emotion text,feeling text, thoughtdate datetime, situation_whom text, " +
                    "situation_when text, situation_where text, behaviour_afterthought text," +
                    "behaviour_reaction text)";

    private static final String CREATE_TABLE_USER_THANKFUL_DIARY =
            "create table thankfuldiary (noteid INTEGER PRIMARY KEY AUTOINCREMENT, username text," +
                    "note text,act text, notedate datetime)";

    private static final String CREATE_TABLE_USER_HABIT_ENTRY =
            "create table userhabitentry (username text ,habitid integer, "
                    + "entrydate date, value int," +
                    " primary key(username,habitid,entrydate)," +
                    " foreign key(habitid) references habit(habitid)); ";


    private static final String CREATE_TABLE_USER =
            "create table user (userid integer primary key autoincrement not null, "
                    + "username text not null, password text not null,email text not null); ";


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
        database.execSQL(CREATE_TABLE_USER);
        database.execSQL(INSERT_STANDARD_HABIT_EATING);
        database.execSQL(INSERT_STANDARD_HABIT_EXERCISE);
        database.execSQL(INSERT_STANDARD_HABIT_ALCOHOL);
        database.execSQL(INSERT_STANDARD_HABIT_PETS);
        database.execSQL(INSERT_STANDARD_HABIT_HOBBIES);
        database.execSQL(INSERT_STANDARD_HABIT_MEDS);
        database.execSQL(INSERT_STANDARD_HABIT_SM);
        database.execSQL(INSERT_Thought);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onCreate(db);
    }



}
