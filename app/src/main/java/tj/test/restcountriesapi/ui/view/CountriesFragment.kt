package tj.test.restcountriesapi.ui.view

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import tj.test.restcountriesapi.R
import tj.test.restcountriesapi.data.api.Status
import tj.test.restcountriesapi.databinding.FragmentCountriesBinding
import tj.test.restcountriesapi.extensions.showToast
import tj.test.restcountriesapi.ui.model.Country
import tj.test.restcountriesapi.ui.widget.CountryAdapter

@AndroidEntryPoint
class CountriesFragment : Fragment() {

    private var _binding: FragmentCountriesBinding? = null
    private val binding get() = checkNotNull(_binding)

    private var countryAdapter: CountryAdapter? = null

    private val viewModel: CountriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
        initMenu()
    }

    private fun initView() = with(binding) {
        countryAdapter = CountryAdapter { onCountryItemClickListener(it) }
        countryAdapter?.let {
            rvCountries.adapter = it
        }
    }

    private fun initObservers() = with(viewModel) {
        countries.observe(viewLifecycleOwner) { state ->
            when(state.status) {
                Status.SUCCESS -> {
                    countryAdapter?.submitList(state.data)
                }
                Status.ERROR -> {
                    requireContext().showToast(state.message)
                }
            }
        }

        isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.rvCountries.isVisible = !isLoading
            binding.pbLoading.isVisible = isLoading
        }
    }

    private fun initMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.action_update -> {
                        viewModel.fetchCountries()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun onCountryItemClickListener(item: Country) {
        findNavController().navigate(
            R.id.action_CountriesFragment_to_CountryDetailsFragment,
            bundleOf(CountryDetailsFragment.ARG_COUNTRY to item)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}