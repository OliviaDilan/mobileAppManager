package ru.rache.mobileappmanager.ui.home


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import ru.rache.mobileappmanager.DBHelper
import ru.rache.mobileappmanager.R


class FragmentTaskRemaining : Fragment(), View.OnClickListener {

    lateinit var etTaskName : EditText
    lateinit var etTaskText : EditText
    lateinit var etTaskID : EditText
    lateinit var dbHelper : DBHelper
    lateinit var btnAdd : Button
    lateinit var btnRead : Button
    lateinit var btnClear : Button
    lateinit var btnUpdate : Button
    lateinit var btnDel : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_remaining, container, false)
    }

// тут происходит магия, мать твою
    override fun onAttach(context: Context) {
        super.onAttach(context)

        dbHelper = DBHelper(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAdd = view.findViewById(R.id.btn_add)
        btnAdd.setOnClickListener(this)

        btnRead = view.findViewById(R.id.btn_read)
        btnRead.setOnClickListener(this)

        btnClear= view.findViewById(R.id.btn_clear)
        btnClear.setOnClickListener(this)

        btnDel= view.findViewById(R.id.btn_delete)
        btnDel.setOnClickListener(this)

        btnUpdate= view.findViewById(R.id.btn_update)
        btnUpdate.setOnClickListener(this)

        etTaskID = view.findViewById(R.id.et_taskID)
        etTaskName = view.findViewById(R.id.et_taskName)
        etTaskText = view.findViewById(R.id.et_taskText)

    }

    override fun onClick(v: View) {
        val taskName: String = etTaskName.text.toString() //getText() or text?
        val taskText: String = etTaskText.text.toString() //getText() or text?
        val taskID: String = etTaskID.text.toString()

        val database: SQLiteDatabase = dbHelper.writableDatabase
        val contentValues: ContentValues = ContentValues()

        when (v.id){
            R.id.btn_add -> {
                contentValues.put(dbHelper.KEY_TASKNAME, taskName)
                contentValues.put(dbHelper.KEY_TASKTEXT, taskText)
                database.insert(dbHelper.TABLE_TASKS, null, contentValues)
                }
            R.id.btn_read -> {
                val cursor: Cursor = database.query(dbHelper.TABLE_TASKS, null, null, null, null, null, null)
                if (cursor.moveToFirst()){
                    val idIndex: Int = cursor.getColumnIndex(dbHelper.KEY_ID)
                    val taskNameIndex: Int = cursor.getColumnIndex(dbHelper.KEY_TASKNAME)
                    val taskTextIndex: Int = cursor.getColumnIndex(dbHelper.KEY_TASKTEXT)
                    do {
                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) + ", task = " + cursor.getString(taskNameIndex) +
                                ", text = " + cursor.getString(taskTextIndex))
                    } while (cursor.moveToNext())
                } else Log.d("mLog", "0 rows")
                cursor.close()
            }
            R.id.btn_clear -> {
                database.delete(dbHelper.TABLE_TASKS, null, null)
            }
            R.id.btn_update -> {
                if (!(taskID.equals("", ignoreCase = true))) {
                    contentValues.put(dbHelper.KEY_TASKNAME, taskName);
                    contentValues.put(dbHelper.KEY_TASKTEXT, taskText);
                    val updCount: Int = database.update(
                        dbHelper.TABLE_TASKS,
                        contentValues,
                        dbHelper.KEY_ID + "= ?",
                        arrayOf<String>(taskID)
                    )
                    Log.d("mLog", "updates rows count = $updCount")
                }
            }
            R.id.btn_delete -> {
                if (!(taskID.equals("", ignoreCase = true))) {
                    val delCount: Int = database.delete(
                        dbHelper.TABLE_TASKS,
                        dbHelper.KEY_ID + "=" + taskID,
                        null
                    )

                    Log.d("mLog", "deleted rows count = " + delCount)
                }
            }
        }
        dbHelper.close()
    }
}



