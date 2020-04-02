package es.com.scoroleth.realtimecorona.api

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.CompletableFuture

class CoronaAPI(private val cliente: OkHttpClient) {

    private val BASE_URL = "https://corona.lmao.ninja/"

    fun getCountry(country: String): CompletableFuture<Response> {
        val url = this.BASE_URL + "countries/" + country.toLowerCase()

        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        val call = cliente.newCall(request)
        return executeAsync(call)
    }

    fun getTotal(): CompletableFuture<Response> {
        val url = this.BASE_URL + "all"

        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        val call = cliente.newCall(request)
        return executeAsync(call)
    }

    // -------------- DEFAULT --------------
    private fun executeAsync(call: Call): CompletableFuture<Response> {
        val cf = CompletableFuture<Response>()

        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                cf.completeExceptionally(e)
            }

            override fun onResponse(call: Call, response: Response) {
                cf.complete(response)
            }
        })

        return cf
    }
}
