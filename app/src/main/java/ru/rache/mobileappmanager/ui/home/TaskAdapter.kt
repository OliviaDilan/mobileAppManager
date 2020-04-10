package ru.rache.mobileappmanager.ui.home

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import ru.rache.mobileappmanager.R
import ru.rache.mobileappmanager.ui.home.db.Const
import ru.rache.mobileappmanager.ui.home.db.DBHelper
import ru.rache.mobileappmanager.ui.home.db.Task

class TaskAdapter() : BaseAdapter(){

    var list : ArrayList<Task>? = null
    var context: Context? = null

    constructor(context: Context?) : this(){
        this.list = DBHelper.getInstance(context!!).getAllTasks()
        this.context = context
    }

    fun updateList(){
        this.list = DBHelper.getInstance(context!!).getAllTasks()
        notifyDataSetChanged()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        var convertView: View? = view
        if (convertView == null){
            convertView = View.inflate(context, R.layout.item_list, null)
        }
        var txtText: TextView? = convertView?.findViewById(R.id.itemTaskTxt) as TextView
        var imgDelete: ImageView = convertView.findViewById(R.id.itemTaskImgDelete) as ImageView
        var taskItem : RelativeLayout = convertView.findViewById(R.id.taskItem) as RelativeLayout
        txtText?.text = list?.get(position)?.text

        taskItem.setOnClickListener {
            var intent = Intent(context, TaskManager::class.java)
            intent.putExtra(Const.WHAT, Const.UPDATE)
            intent.putExtra(Const.TASK_KEY,(list?.get(position) as Parcelable))
            context?.startActivity(intent)
            notifyDataSetChanged()
        }

        imgDelete.setOnClickListener{
            DBHelper.getInstance(context!!).deleteTask(list?.get(position)?.id)
            list?.removeAt(position)
            notifyDataSetChanged()
        }
        // обновляем лист при добавлении (если задачи уже есть) или изменении задачи
        updateList()
        return convertView
    }

    override fun getItem(position: Int): Task? {
        return list?.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list!!.size
    }
//TODO Сделать свайпы, добавить оствлеживание важности, дату
}