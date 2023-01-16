package tj.test.restcountriesapi.data.repository

import tj.test.restcountriesapi.data.api.CountryApi
import tj.test.restcountriesapi.ui.model.Country
import tj.test.restcountriesapi.ui.model.toCountry
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(
    private val api: CountryApi,
) : CountriesRepository {

    override suspend fun fetchCountries(): List<Country> {
        return api.fetchCountries().map {
            it.toCountry()
        }
    }
}