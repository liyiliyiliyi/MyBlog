package model;

import util.StringUtils;

/**
 * 类主要是 为时间轴 的输出而设置的 一个Article类的简化版
 */
public class TimeLine {

    private String title;
    private int id;
    private int year;
    private int month;
    private int day;

    @Override
    public String toString() {
        return "TimeLine [title=" + title + ", id=" + id + ", year=" + year + ", month=" + month + ", day=" + day
                + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

}
