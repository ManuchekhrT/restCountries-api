package tj.test.restcountriesapi.data.api

import retrofit2.http.GET
import tj.test.restcountriesapi.data.model.CountriesResponse

interface CountryApi {
    companion object {
        const val BASE_API = "https://restcountries.com/v2/"
    }

    @GET("all")
    suspend fun fetchCountries(): List<CountriesResponse>
}