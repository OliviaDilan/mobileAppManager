package ru.rache.mobileappmanager.ui.tools

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_tools.*
import ru.rache.mobileappmanager.R

class ToolsFragment : Fragment() {
    var mycontext: Context? = null
    lateinit var btn_a: Button
    private lateinit var toolsViewModel: ToolsViewModel
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mycontext=context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        toolsViewModel =
            ViewModelProviders.of(this).get(ToolsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_tools, container, false)
        //val btn = root.findViewById<View>(R.id.tools_acc) as Button

        //Button-ы
        val btn_a: Button = root.findViewById(R.id.tools_acc)
        btn_a.setOnClickListener {
            var intent: Intent = Intent(mycontext, AccActivity::class.java)
            startActivity(intent)
        }
        val btn_b: Button = root.findViewById(R.id.tools_access)
        btn_b.setOnClickListener {
            var intent: Intent = Intent(mycontext, AccessActivity::class.java)
            startActivity(intent)
        }
        val btn_c: Button = root.findViewById(R.id.tools_notification)
        btn_c.setOnClickListener {
            var intent: Intent = Intent(mycontext, VolumeActivity::class.java)
            startActivity(intent)
        }
        val btn_d: Button = root.findViewById(R.id.tools_sort)
        btn_d.setOnClickListener {
            var intent: Intent = Intent(mycontext, SortActivity::class.java)
            startActivity(intent)
        }
        val btn_e: Button = root.findViewById(R.id.tools_categories)
        btn_e.setOnClickListener {
            var intent: Intent = Intent(mycontext, CategoriesActivity::class.java)
            startActivity(intent)
        }
        val btn_f: Button = root.findViewById(R.id.tools_places)
        btn_f.setOnClickListener {
            var intent: Intent = Intent(mycontext, PlacesActivity::class.java)
            startActivity(intent)
        }

        return root
    }
}







