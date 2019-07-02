package com.serenidad;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferenceHelper {
    private static final String LOGGED_IN = "isLoggedIn";
    private static final String FNAME = "fname";
    private static final String LNAME = "lname";
    private static final String PHONE = "birthDate";
    private static final String EMAIL = "emailid";
    private static final String AGE = "age";
    private static final String PASS = "password";
    private static final String IMAGE_PATH = "imgPath";

    public static void setImagePath(Context context, String imagePath) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(IMAGE_PATH, imagePath);
        editor.apply();
    }


    public static String getImagePath(Context context) {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(IMAGE_PATH, "");
    }
    public static void setLogedIn(Context context, boolean loggedIn) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putBoolean(LOGGED_IN, loggedIn);
        editor.apply();
    }


    public static boolean isLoggedIn(Context context) {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(LOGGED_IN, false);
    }

    public static void setFName(Context context,String name){
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(FNAME, name);
        editor.apply();
    }
    public static String getFname(Context context){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(FNAME, "");
    }

    public static void setLname(Context context,String lname){
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(LNAME, lname);
        editor.apply();
    }

    public static String getLname(Context context){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(LNAME, "");
    }

    public static void setEmail(Context context,String email){
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(EMAIL, email);
        editor.apply();
    }

    public static String getEmail(Context context){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(EMAIL, "");
    }

    public static void setPass(Context context,String pass){
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(PASS, pass);
        editor.apply();
    }

    public static String getPass(Context context){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(PASS, "");
    }

    public static void setNumber(Context context,String number){
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(PHONE, number);
        editor.apply();
    }

    public static String getNumber(Context context){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(PHONE, "");
    }

    public static void setAge(Context context,String age){
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(AGE, age);
        editor.apply();
    }

    public static String getAge(Context context){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(AGE, "");
    }

}

