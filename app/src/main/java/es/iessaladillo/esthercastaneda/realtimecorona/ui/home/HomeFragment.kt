package es.iessaladillo.esthercastaneda.realtimecorona.ui.home

import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.google.gson.Gson
import es.iessaladillo.esthercastaneda.realtimecorona.R
import es.iessaladillo.esthercastaneda.realtimecorona.api.All
import es.iessaladillo.esthercastaneda.realtimecorona.api.CoronaAPI
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.Response
import java.io.IOException
import okhttp3.OkHttpClient
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit

class HomeFragment : Fragment() {

    private val cliente =   OkHttpClient.Builder()
        .connectTimeout(5000, TimeUnit.MILLISECONDS)
        .readTimeout(5000, TimeUnit.MILLISECONDS)
        .build()

    private val apiService = CoronaAPI(cliente)
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val connectivityManager = context?.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo

        if (networkInfo != null && networkInfo.isConnected) {
            noConnection.visibility = View.INVISIBLE
            withConnection.visibility = View.VISIBLE
            setupViews()
        } else {
            noConnection.visibility = View.VISIBLE
            withConnection.visibility = View.INVISIBLE
            Toast.makeText(context, getString(R.string.connectInternet), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupViews() {
        showCountries()

        homeViewModel.totalCases.observe(this) {
            txtTotalCases.text = it.toString()
        }
        homeViewModel.actualCases.observe(this) {
            txtActualCases.text = it.toString()
        }
        homeViewModel.recovered.observe(this) {
            txtRecovered.text = it.toString()
        }
        homeViewModel.deaths.observe(this) {
            txtDeaths.text = it.toString()
        }
    }

    private fun showCountries() {
        CompletableFuture.allOf(apiService.getTotal().thenAcceptAsync(this::getAllCountriesNumbers)).join()
    }
    private fun getAllCountriesNumbers(response: Response){
        try {
            val rb = response.body()
            if(rb != null){

                val countries = rb.string()

                val gson = Gson()
                val infoCountries = gson.fromJson(countries, All::class.java)

                homeViewModel.setTotalCases(infoCountries.cases)
                homeViewModel.setActualCases(infoCountries.active)
                homeViewModel.setDeaths(infoCountries.deaths)
                homeViewModel.setRecovered(infoCountries.recovered)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}