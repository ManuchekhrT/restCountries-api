package tj.test.restcountriesapi.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CountriesResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("flags")
    val flags: Map<String, String>? = null,
    @SerializedName("region")
    val region: String,
    @SerializedName("capital")
    val capital: String,
    @SerializedName("currencies")
    val currencies: List<CountryCurrency>? = emptyList(),
    @SerializedName("timezones")
    val timezones: List<String>? = emptyList(),
): Serializable

data class CountryCurrency(
    @SerializedName("code")
    val code: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("symbol")
    val symbol: String?
): Serializable

//data class CountryFlag(
//    @SerializedName("svg")
//    val code: String?,
//    @SerializedName("png")
//    val code: String?
//)