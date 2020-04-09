package ru.rache.mobileappmanager.ui.home


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_task_remaining.*
import ru.rache.mobileappmanager.MainActivity
import ru.rache.mobileappmanager.ui.home.db.DBHelper
import ru.rache.mobileappmanager.R
import ru.rache.mobileappmanager.ui.home.db.Const.ADD
import ru.rache.mobileappmanager.ui.home.db.Const.TASK_KEY
import ru.rache.mobileappmanager.ui.home.db.Const.UPDATE
import ru.rache.mobileappmanager.ui.home.db.Const.WHAT
import ru.rache.mobileappmanager.ui.home.db.Task


class FragmentTaskRemaining() : Fragment() {
    var adapter: TaskAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_remaining, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapter = TaskAdapter(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        taskRemaining_list.adapter = adapter
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
/*
        fab.setOnClickListener{
            var intent: Intent = Intent(this@FragmentTaskRemaining, TaskManager::class.java)
            intent.putExtra(WHAT, ADD)
            startActivity(intent)
        }*/
    }
}


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
        var imgEdit: ImageView = convertView.findViewById(R.id.itemTaskImgEdit) as ImageView
        var imgDelete: ImageView = convertView.findViewById(R.id.itemTaskImgDelete) as ImageView
        txtText?.text = list?.get(position)?.text

        imgEdit.setOnClickListener {
            var intent = Intent(context, TaskManager::class.java)
            intent.putExtra(WHAT, UPDATE)
            intent.putExtra(TASK_KEY,(list?.get(position) as Parcelable))
            context?.startActivity(intent)
        }

        imgDelete.setOnClickListener{
            DBHelper.getInstance(context!!).deleteTask(list?.get(position)?.id)
            list?.removeAt(position)
            notifyDataSetChanged()
        }
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

}
