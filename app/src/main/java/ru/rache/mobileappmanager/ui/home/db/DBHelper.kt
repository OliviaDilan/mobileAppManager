package ru.rache.mobileappmanager.ui.home.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ru.rache.mobileappmanager.ui.home.db.Const.DATABASE_NAME
import ru.rache.mobileappmanager.ui.home.db.Const.DATABASE_VERSION


class DBHelper(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int): SQLiteOpenHelper(context, name, factory, version){

    val TASK_TABLE: String = "task_table"
    val ID: String = "id"
    val TASK_TEXT: String = "text"
    val TASK_DATE: String = "yyyy:mm:dd"
    val TASK_IMPORTANT: Int = 0 // there is no boolean data type in SQLite, so we use 0/1 as integer

    companion object{
        private var instance: DBHelper? = null
        fun getInstance(ctx: Context): DBHelper {
            if(instance == null){
                instance =
                    DBHelper(
                        ctx,
                        DATABASE_NAME,
                        null,
                        DATABASE_VERSION
                    )
            }
            return instance!!
        }
    }

    val createSQL: String = "create table $TASK_TABLE($ID INTEGER PRIMARY KEY AUTOINCREMENT, $TASK_TEXT TEXT)"

    override fun onCreate(db: SQLiteDatabase) {
            db.execSQL(createSQL)
        }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    fun addTask(task: String?): Long{
        var values: ContentValues = ContentValues()
        values.put(TASK_TEXT, task)
        return this.writableDatabase.insert(TASK_TABLE, null, values)
    }

    fun updateTask(id: Int?, task: String?): Int{
        var values: ContentValues = ContentValues()
        values.put(TASK_TEXT, task)
        return this.writableDatabase.update(TASK_TABLE, values, "$ID=?", arrayOf("$id"))
    }

    fun deleteTask(id: Int?){
        this.writableDatabase.delete(TASK_TABLE, "$ID=?", arrayOf("$id"))
    }

    fun getAllTasks(): ArrayList<Task>{
        var list: ArrayList<Task> = ArrayList()
        var cursor: Cursor = this.writableDatabase.query(TASK_TABLE, arrayOf(ID, TASK_TEXT), null, null, null, null, null)

        if (cursor.moveToFirst()){
            do {
                var tsk: Task = Task()
                tsk.id = cursor.getInt(0)
                tsk.text = cursor.getString(1)
                list.add(tsk)
            } while (cursor.moveToNext())
        }
        return list
    }

}
