package ru.rache.mobileappmanager.ui.home


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_task_remaining.*
import org.w3c.dom.Text
import ru.rache.mobileappmanager.R


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

}
