package es.iessaladillo.esthercastaneda.realtimecorona.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        showCountries()
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

                info.text = String.format("Cases: %s%nDeaths: %s%nRecovered: %s%n",
                            infoCountries.cases.toString(), infoCountries.deaths.toString(), infoCountries.recovered.toString())
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}