package tj.test.restcountriesapi.ui.model

import tj.test.restcountriesapi.data.model.CountriesResponse
import tj.test.restcountriesapi.data.model.CountryCurrency
import java.io.Serializable

data class Country(
    val name: String?,
    val flags: Map<String, String> = emptyMap(),
    val region: String?,
    val capital: String?,
    val currencies: List<CountryCurrency> = emptyList(),
    val timezones: List<String> = emptyList()
): Serializable

fun CountriesResponse.toCountry() : Country {
    return Country(
        name = name,
        flags = flags ?: mapOf(),
        region = region,
        capital = capital,
        currencies = currencies ?: listOf(),
        timezones = timezones ?: listOf()
    )
}