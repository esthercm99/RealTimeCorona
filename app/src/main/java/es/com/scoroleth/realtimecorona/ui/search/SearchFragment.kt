package es.com.scoroleth.realtimecorona.ui.search

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import es.com.scoroleth.realtimecorona.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    private lateinit var connectivityManager: ConnectivityManager
    private lateinit var searchViewModel: SearchViewModel

    private val navController: NavController by lazy {
        NavHostFragment.findNavController(nav_host_fragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            setupViews()
        } else {
            navController.navigate(R.id.navigation_home)
        }
    }

    private fun setupViews() {
        search.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                search()
            }
            true
        }
        iconSearch.setOnClickListener {
            search()
        }
    }

    private fun search() {
        val networkInfo = connectivityManager.activeNetworkInfo

        if (networkInfo != null && networkInfo.isConnected) {
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(search.windowToken, 0) ?: false

            txtNotFound.visibility = View.INVISIBLE
            infoCountry.visibility = View.INVISIBLE

            if (search.text.toString().trim().isEmpty()) {
                Toast.makeText(context, getString(R.string.warningEmptyCountry), Toast.LENGTH_SHORT).show()
            } else {
                val result = searchViewModel.showCountry(search.text.toString())

                if (result.isEmpty()) {
                    infoCountry.visibility = View.VISIBLE
                    setInfoCountry()
                } else {
                    txtNotFound.visibility = View.VISIBLE
                }
            }
        } else {
            navController.navigate(R.id.navigation_home)
        }
    }

    private fun setInfoCountry() {
        nameCountry.text = searchViewModel.infoCountry.country.toString()
        txtTotalCases.text = searchViewModel.infoCountry.cases.toString()
        txtActualCases.text = searchViewModel.infoCountry.active.toString()
        txtRecovered.text = searchViewModel.infoCountry.recovered.toString()
        txtDeaths.text = searchViewModel.infoCountry.deaths.toString()
        txtTodayCases.text = searchViewModel.infoCountry.todayCases.toString()
        txtTodayDeaths.text = searchViewModel.infoCountry.todayDeaths.toString()
        txtCasesPerMillion.text = searchViewModel.infoCountry.casesPerOneMillion.toString()
        txtDeathsPerMillion.text = searchViewModel.infoCountry.deathsPerOneMillion.toString()

        context?.let {
            val flag = searchViewModel.infoCountry.countryInfo?.flag

            if (flag != null) {
                Glide.with(it).load(flag).into(imgCountry)
            }
        }
    }

}