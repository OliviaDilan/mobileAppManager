package ru.rache.mobileappmanager.ui.finances

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ru.rache.mobileappmanager.R

class FinancesFragment : Fragment() {

    private lateinit var financesViewModel: FinancesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        financesViewModel =
            ViewModelProviders.of(this).get(FinancesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_finances, container, false)
        val textView: TextView = root.findViewById(R.id.text_finances)
        financesViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}