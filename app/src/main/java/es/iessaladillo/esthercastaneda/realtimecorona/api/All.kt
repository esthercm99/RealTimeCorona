package es.iessaladillo.esthercastaneda.realtimecorona.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class All {
    @SerializedName("cases")
    @Expose
    var cases: Long? = null
    @SerializedName("deaths")
    @Expose
    var deaths: Long? = null
    @SerializedName("recovered")
    @Expose
    var recovered: Long? = null
    @SerializedName("updated")
    @Expose
    var updated: Long? = null
    @SerializedName("active")
    @Expose
    var active: Long? = null

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param recovered
     * @param cases
     * @param active
     * @param updated
     * @param deaths
     */
    constructor(cases: Long?, deaths: Long?, recovered: Long?, updated: Long?, active: Long?) : super() {
        this.cases = cases
        this.deaths = deaths
        this.recovered = recovered
        this.updated = updated
        this.active = active
    }

}