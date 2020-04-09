package ru.rache.mobileappmanager.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_task_manager.*
import ru.rache.mobileappmanager.R
import ru.rache.mobileappmanager.ui.home.db.Const
import ru.rache.mobileappmanager.ui.home.db.Const.ADD
import ru.rache.mobileappmanager.ui.home.db.Const.TASK_KEY
import ru.rache.mobileappmanager.ui.home.db.Const.WHAT
import ru.rache.mobileappmanager.ui.home.db.DBHelper
import ru.rache.mobileappmanager.ui.home.db.Task

class TaskManager : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_manager)

        if (intent.getStringExtra(Const.WHAT).equals(Const.ADD)){
            taskManagerButton.text = "save"
        } else {
            taskManagerButton.text = "update"
            var tsk: Task = intent.getParcelableExtra(TASK_KEY)
            taskManagerText.setText(tsk.text)
        }

        taskManagerButton.setOnClickListener{
            if (intent.getStringExtra(WHAT).equals(ADD)){
                addTask()
                finish()
            } else {
                updateTask(intent.getParcelableExtra(TASK_KEY))
                finish()
            }
        }
    }

    fun logMsg (msg: String){
        Log.d("TAG", msg)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null){
            when (item.itemId){
                android.R.id.home -> finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun validateInput(): Boolean{
        if (taskManagerText?.text.toString().trim().equals("")){
            Toast.makeText(this,"Пожалуйста, заполните поле", Toast.LENGTH_SHORT).show()
            return false
        } else {
            return true
        }
    }

    fun addTask(){
        if (validateInput()){
            DBHelper.getInstance(this).addTask(taskManagerText?.text.toString())
        }
    }

    fun updateTask(task: Task?){
        if (validateInput()){
            DBHelper.getInstance(this).updateTask(task?.id, taskManagerText.text.toString())
        }
    }

}
