package es.iessaladillo.esthercastaneda.realtimecorona.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import es.iessaladillo.esthercastaneda.realtimecorona.R
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        iconSearch.setOnClickListener {
            txtNotFound.visibility = View.INVISIBLE
            infoCountry.visibility = View.INVISIBLE

            if (search.text.toString().trim().isEmpty()) {
                Toast.makeText(context, "Put a country", Toast.LENGTH_SHORT).show()
            } else {
                val result = searchViewModel.showCountry(search.text.toString())

                if(result.isEmpty()) {
                    infoCountry.visibility = View.VISIBLE
                    setInfoCountry()
                } else {
                    txtNotFound.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setInfoCountry() {
        nameCountry.text = searchViewModel.infoCountry.country.toString()
        txtTotalCases.text = searchViewModel.infoCountry.cases.toString()
        txtActualCases.text = searchViewModel.infoCountry.active.toString()
        txtRecovered.text = searchViewModel.infoCountry.recovered.toString()
        txtDeaths.text = searchViewModel.infoCountry.deaths.toString()

        context?.let {
            val flag = searchViewModel.infoCountry.countryInfo?.flag

            if (flag != null) {
                Glide.with(it).load(flag).into(imgCountry)
            }
        }
    }
}