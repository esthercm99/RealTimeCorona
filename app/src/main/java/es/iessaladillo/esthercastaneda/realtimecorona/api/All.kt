package es.iessaladillo.esthercastaneda.realtimecorona.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class All {

    @SerializedName("cases")
    @Expose
    var cases: Int? = null
    @SerializedName("deaths")
    @Expose
    var deaths: Int? = null
    @SerializedName("recovered")
    @Expose
    var recovered: Int? = null
    @SerializedName("updated")
    @Expose
    var updated: Long? = null

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param recovered
     * @param cases
     * @param updated
     * @param deaths
     */
    constructor(cases: Int?, deaths: Int?, recovered: Int?, updated: Long?) : super() {
        this.cases = cases
        this.deaths = deaths
        this.recovered = recovered
        this.updated = updated
    }

}