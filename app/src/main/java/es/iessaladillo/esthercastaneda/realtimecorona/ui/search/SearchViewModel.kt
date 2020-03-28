package es.iessaladillo.esthercastaneda.realtimecorona.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    var infoCountry: Country = Country()

    fun showCountry(country: String) : String {
        CompletableFuture.allOf(apiService.getCountry(country).thenAcceptAsync(this::showResponse)).join()
        return info
    }

    private fun showResponse(response: Response){
        try {
            val rb = response.body()
            if(rb != null){

                val country = rb.string()
                val aux = country.replace("{", "")
                    .replace("}", "")
                    .replace("\"", "")

                if ( aux.startsWith("message")) {
                    info = "Country not found or doesn't have any cases"

                } else {
                    val gson = Gson()
                    infoCountry = gson.fromJson(country, Country::class.java)
                    info = ""
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}