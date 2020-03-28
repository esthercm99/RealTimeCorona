package es.iessaladillo.esthercastaneda.realtimecorona.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CountryInfo {
    @SerializedName("_id")
    @Expose
    var id: Int? = null
    @SerializedName("lat")
    @Expose
    var lat: Int? = null
    @SerializedName("long")
    @Expose
    var long: Int? = null
    @SerializedName("flag")
    @Expose
    var flag: String? = null
    @SerializedName("iso3")
    @Expose
    var iso3: String? = null
    @SerializedName("iso2")
    @Expose
    var iso2: String? = null

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param flag
     * @param _long
     * @param id
     * @param iso2
     * @param lat
     * @param iso3
     */
    constructor(
        id: Int?,
        lat: Int?,
        _long: Int?,
        flag: String?,
        iso3: String?,
        iso2: String?
    ) : super() {
        this.id = id
        this.lat = lat
        long = _long
        this.flag = flag
        this.iso3 = iso3
        this.iso2 = iso2
    }

}