package es.iessaladillo.esthercastaneda.realtimecorona.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Country {
    @SerializedName("country")
    @Expose
    var country: String? = null
    @SerializedName("cases")
    @Expose
    var cases: Int? = null
    @SerializedName("todayCases")
    @Expose
    var todayCases: Int? = null
    @SerializedName("deaths")
    @Expose
    var deaths: Int? = null
    @SerializedName("todayDeaths")
    @Expose
    var todayDeaths: Int? = null
    @SerializedName("recovered")
    @Expose
    var recovered: Int? = null
    @SerializedName("critical")
    @Expose
    var critical: Int? = null

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param country
     * @param recovered
     * @param cases
     * @param critical
     * @param deaths
     * @param todayCases
     * @param todayDeaths
     */
    constructor(
        country: String,
        cases: Int?,
        todayCases: Int?,
        deaths: Int?,
        todayDeaths: Int?,
        recovered: Int?,
        critical: Int?
    ) : super() {
        this.country = country
        this.cases = cases
        this.todayCases = todayCases
        this.deaths = deaths
        this.todayDeaths = todayDeaths
        this.recovered = recovered
        this.critical = critical
    }
}