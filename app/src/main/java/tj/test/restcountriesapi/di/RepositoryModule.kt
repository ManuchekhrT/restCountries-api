package tj.test.restcountriesapi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tj.test.restcountriesapi.data.api.CountryApi
import tj.test.restcountriesapi.data.repository.CountriesRepository
import tj.test.restcountriesapi.data.repository.CountriesRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideCountriesRepository(
        api: CountryApi
    ): CountriesRepository = CountriesRepositoryImpl(api)

}