package ru.rache.mobileappmanager.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.shrikanthravi.collapsiblecalendarview.data.Day
import ru.rache.mobileappmanager.R
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })


        val collapsibleCalendar: CollapsibleCalendar = root.findViewById(R.id.calendarView)
        collapsibleCalendar.setCalendarListener(object : CollapsibleCalendar.CalendarListener {
            override fun onDaySelect() {
//                val day: Day = viewCalendar.getSelectedDay()
//                Log.i(
//                    javaClass.name, ("Selected Day: "
//                            + day.getYear()) + "/" + (day.getMonth() + 1).toString() + "/" + day.getDay()
//                )
            }

            override fun onItemClick(view: View) {}
            override fun onClickListener() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataUpdate() {}
            override fun onDayChanged() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onMonthChange() {}
            override fun onWeekChange(i: Int) {}
        })


        return root
    }


}
