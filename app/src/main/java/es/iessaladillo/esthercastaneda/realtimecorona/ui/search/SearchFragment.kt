package es.iessaladillo.esthercastaneda.realtimecorona.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import es.iessaladillo.esthercastaneda.realtimecorona.R
import kotlinx.android.synthetic.main.fragment_notifications.*

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        iconSearch.setOnClickListener {
            if (search.text.toString().isEmpty()) {
                Toast.makeText(context, "Put a country", Toast.LENGTH_SHORT).show()
            } else {
                info.text = searchViewModel.showCountry(search.text.toString())
            }
        }
    }
}