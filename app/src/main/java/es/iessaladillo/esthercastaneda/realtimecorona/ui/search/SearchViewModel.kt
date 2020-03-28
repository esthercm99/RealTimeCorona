package es.iessaladillo.esthercastaneda.realtimecorona.ui.search

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import es.iessaladillo.esthercastaneda.realtimecorona.api.CoronaAPI
import es.iessaladillo.esthercastaneda.realtimecorona.api.Country
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit

class SearchViewModel : ViewModel() {

    private var info: String = ""

    private val cliente =   OkHttpClient.Builder()
        .connectTimeout(5000, TimeUnit.MILLISECONDS)
        .readTimeout(5000, TimeUnit.MILLISECONDS)
        .build()

    private val apiService = CoronaAPI(cliente)

    fun showCountry(country: String) : String {
        CompletableFuture.allOf(apiService.getCountry(country).thenAcceptAsync(this::showResponse)).join()
        return info
    }

    private fun showResponse(response: Response){
        try {
            val rb = response.body()
            if(rb != null){

                val country = rb.string()

                if (country.equals("Country not found")) {
                    info = country
                } else {
                    val gson = Gson()
                    val infoCountry = gson.fromJson(country, Country::class.java)

                    info = String.format(  "Country: %s%nCases: %d%nCritical: %d%nDeaths: %d%nRecovered: %d%nToday cases: %d%nToday Deaths: %d%n",
                        infoCountry.country, infoCountry.cases, infoCountry.critical, infoCountry.deaths,
                        infoCountry.recovered, infoCountry.todayCases, infoCountry.todayDeaths)
                }

            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}