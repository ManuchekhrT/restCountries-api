package tj.test.restcountriesapi.data.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tj.test.restcountriesapi.data.repository.CountriesRepository
import tj.test.restcountriesapi.extensions.FlowUseCase
import tj.test.restcountriesapi.ui.model.Country
import javax.inject.Inject
import javax.inject.Singleton

interface CountriesUseCase : FlowUseCase<Unit, List<Country>>

class CountriesUseCaseImpl @Inject constructor(
    private val countriesRepository: CountriesRepository
) : CountriesUseCase {

    override fun execute(param: Unit): Flow<Result<List<Country>>> = flow {
        emit(Result.success(countriesRepository.fetchCountries()))
    }
}