package com.serenidad;

import java.io.Serializable;

public class Habit implements Serializable {
    private int habitid;
    private String habitname;
    private String habiticon;
    private int min;
    private int max;
    private String scale;
    private int isCustom;
    private int value;

    public int getHabitid() {
        return habitid;
    }

    public void setHabitid(int habitid) {
        this.habitid = habitid;
    }

    public String getHabitname() {
        return habitname;
    }

    public void setHabitname(String habitname) {
        this.habitname = habitname;
    }

    public String getHabiticon() {
        return habiticon;
    }

    public void setHabiticon(String habiticon) {
        this.habiticon = habiticon;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public int getIsCustom() {
        return isCustom;
    }

    public void setIsCustom(int isCustom) {
        this.isCustom = isCustom;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
