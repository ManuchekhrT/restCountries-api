package tj.test.restcountriesapi.ui.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import tj.test.restcountriesapi.data.api.State
import tj.test.restcountriesapi.data.usecases.CountriesUseCase
import tj.test.restcountriesapi.ui.model.Country
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val countriesUseCases: CountriesUseCase
) : ViewModel() {

    private val _countries = MutableLiveData<State<List<Country>>>()
    val countries: LiveData<State<List<Country>>> get() = _countries

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        fetchCountries()
    }

    fun fetchCountries() {
        viewModelScope.launch {
            countriesUseCases(Unit).onStart {
                _isLoading.value = true
            }.onCompletion {
                _isLoading.value = false
            }.collect { result ->
                result.onFailure { error ->
                    _countries.value = State.error(error.message)
                }.onSuccess {
                    _countries.value = State.success(it)
                }
            }
        }
    }

}