package tj.test.restcountriesapi.data.repository

import tj.test.restcountriesapi.ui.model.Country

interface CountriesRepository {
    suspend fun fetchCountries(): List<Country>
}