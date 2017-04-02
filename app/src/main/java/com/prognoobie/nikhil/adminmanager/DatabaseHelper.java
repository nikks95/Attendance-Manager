package com.prognoobie.nikhil.adminmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLDataException;

/**
 * Created by Nikhil on 4/2/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME="admin.db";
    public static final String TABLE_NAME="user_details";
    public static final String COL_ID="id";
    public static final String C0L_USERNAME="username";
    public static final String COL_PASSWORD="password";

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db=this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE user ( user_id INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT NOT NULL, password TEXT NOT NULL,name TEXT NOT NULL,user_type TEXT NOT NULL)");
        sqLiteDatabase.execSQL("CREATE TABLE department (department_id INTEGER PRIMARY KEY AUTOINCREMENT,department_name TEXT NOT NULL,department_hod TEXT);");
        sqLiteDatabase.execSQL("CREATE TABLE courses ( course_id INTEGER PRIMARY KEY AUTOINCREMENT, course_name TEXT NOT NULL, course_duration INTEGER NOT NULL, course_department INTEGER NOT NULL, FOREIGN KEY(course_department) REFERENCES department(department_id) )");
        sqLiteDatabase.execSQL("CREATE TABLE faculty ( faculty_id INTEGER NOT NULL, faculty_department INTEGER NOT NULL, faculty_status TEXT, PRIMARY KEY(faculty_id), FOREIGN KEY(faculty_id) REFERENCES user(user_id), FOREIGN KEY(faculty_department) REFERENCES department(department_id) )");
        sqLiteDatabase.execSQL("CREATE TABLE student ( student_id INTEGER, student_rollno TEXT NOT NULL, student_course INTEGER NOT NULL, student_semester INTEGER NOT NULL, PRIMARY KEY(student_id), FOREIGN KEY(student_id) REFERENCES user(user_id), FOREIGN KEY(student_course) REFERENCES courses(course_id) )");
        sqLiteDatabase.execSQL("CREATE TABLE subject ( subject_id INTEGER PRIMARY KEY AUTOINCREMENT, subject_name TEXT NOT NULL, subject_course INTEGER NOT NULL, FOREIGN KEY(subject_course) REFERENCES courses(course_id) )");
        sqLiteDatabase.execSQL("CREATE TABLE timetable ( tt_id INTEGER PRIMARY KEY AUTOINCREMENT, tt_sem INTEGER NOT NULL, tt_starttime INTEGER NOT NULL, tt_endtime INTEGER NOT NULL, tt_subject INTEGER NOT NULL, tt_faculty INTEGER NOT NULL, FOREIGN KEY(tt_subject) REFERENCES subject(subject_id), FOREIGN KEY(tt_faculty) REFERENCES faculty(faculty_id) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS user");
        onCreate(sqLiteDatabase);
    }
}
