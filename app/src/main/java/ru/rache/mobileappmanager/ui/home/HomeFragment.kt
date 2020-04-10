package ru.rache.mobileappmanager.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.shrikanthravi.collapsiblecalendarview.data.Day
import ru.rache.mobileappmanager.R
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar
import kotlinx.android.synthetic.main.fragment_home.*


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
            }

            override fun onDataUpdate() {}
            override fun onDayChanged() {
            }

            override fun onMonthChange() {}
            override fun onWeekChange(i: Int) {}
        })

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        class MyPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm){
            override fun getItem(position: Int): Fragment {
                return when (position){
                    0 -> FragmentTaskRemaining()
                    else -> FragmentTaskDone()
                }
            }

            override fun getCount(): Int {
                return 2
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return when (position){
                    0 -> "Невыполненные"
                    else -> "Выполненные"
                }
            }
        }

        val fragmentAdapter = MyPagerAdapter(childFragmentManager)

        viewpager.adapter = fragmentAdapter
        tabs.setupWithViewPager(viewpager)
        super.onActivityCreated(savedInstanceState)
    }

}
