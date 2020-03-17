package ru.rache.mobileappmanager.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_fragment_task_remaining.*

import ru.rache.mobileappmanager.R

class FragmentTaskRemaining : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_task_remaining, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        textview_fragment_name.text = "Fragment Task Remaining"
    }

}
