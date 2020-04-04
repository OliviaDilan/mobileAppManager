package ru.rache.mobileappmanager

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ru.rache.mobileappmanager.ui.home.FragmentTaskRemaining

var DATABASE_VERSION : Int = 1
val DATABASE_NAME : String = "tasksDb"

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    val TABLE_TASKS : String = "tasks"

    val KEY_ID : String = "_id"
    val KEY_TASKNAME : String = "taskName"
    val KEY_TASKTEXT : String = "taskText"
    val KEY_ISDONE : Int = 0

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table " + TABLE_TASKS + "(" + KEY_ID
                + " integer primary key," + KEY_TASKNAME + " text," + KEY_TASKTEXT + " text" + KEY_ISDONE + "integer" + ")")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("drop table if exists $TABLE_TASKS")
    }

}
