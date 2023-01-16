package tj.test.restcountriesapi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tj.test.restcountriesapi.data.repository.CountriesRepository
import tj.test.restcountriesapi.data.usecases.CountriesUseCase
import tj.test.restcountriesapi.data.usecases.CountriesUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Singleton
    @Provides
    fun provideCountriesUseCase(
        repository: CountriesRepository
    ): CountriesUseCase = CountriesUseCaseImpl(repository)
}