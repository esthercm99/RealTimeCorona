package es.com.scoroleth.realtimecorona.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Country {
    @SerializedName("country")
    @Expose
    var country: String? = null
    @SerializedName("countryInfo")
    @Expose
    var countryInfo: CountryInfo? = null
    @SerializedName("cases")
    @Expose
    var cases: Long? = null
    @SerializedName("todayCases")
    @Expose
    var todayCases: Long? = null
    @SerializedName("deaths")
    @Expose
    var deaths: Long? = null
    @SerializedName("todayDeaths")
    @Expose
    var todayDeaths: Long? = null
    @SerializedName("recovered")
    @Expose
    var recovered: Long? = null
    @SerializedName("active")
    @Expose
    var active: Long? = null
    @SerializedName("critical")
    @Expose
    var critical: Long? = null
    @SerializedName("casesPerOneMillion")
    @Expose
    var casesPerOneMillion: Long? = null
    @SerializedName("deathsPerOneMillion")
    @Expose
    var deathsPerOneMillion: Long? = null

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
     * @param deathsPerOneMillion
     * @param active
     * @param casesPerOneMillion
     * @param countryInfo
     * @param deaths
     * @param todayCases
     * @param todayDeaths
     */
    constructor(
        country: String?,
        countryInfo: CountryInfo,
        cases: Long?,
        todayCases: Long?,
        deaths: Long?,
        todayDeaths: Long?,
        recovered: Long?,
        active: Long?,
        critical: Long?,
        casesPerOneMillion: Long?,
        deathsPerOneMillion: Long?
    ) : super() {
        this.country = country
        this.countryInfo = countryInfo
        this.cases = cases
        this.todayCases = todayCases
        this.deaths = deaths
        this.todayDeaths = todayDeaths
        this.recovered = recovered
        this.active = active
        this.critical = critical
        this.casesPerOneMillion = casesPerOneMillion
        this.deathsPerOneMillion = deathsPerOneMillion
    }

}