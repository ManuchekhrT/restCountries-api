package tj.test.restcountriesapi.ui.view

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dagger.hilt.android.AndroidEntryPoint
import tj.test.restcountriesapi.R
import tj.test.restcountriesapi.databinding.FragmentCountryDetailsBinding
import tj.test.restcountriesapi.extensions.ImageExtension
import tj.test.restcountriesapi.extensions.serializable
import tj.test.restcountriesapi.ui.model.Country

@AndroidEntryPoint
class CountryDetailsFragment : Fragment() {

    private var _binding: FragmentCountryDetailsBinding? = null
    private val binding get() = checkNotNull(_binding)

    private var country: Country? = null

    companion object {
        const val ARG_COUNTRY = "arg_country"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            country = it.serializable(ARG_COUNTRY) as? Country
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountryDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupMenu()
    }

    private fun setupView() {
        country?.let { aCountry ->
            if (aCountry.flags.containsKey(ImageExtension.png.name)) {
                Glide.with(requireContext())
                    .load(aCountry.flags[ImageExtension.png.name])
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(binding.ivFlag)
            }
            binding.tvName.text = aCountry.name
            binding.countryInfoView.setCountryInfo(aCountry)
        }
    }

    private fun setupMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {
                val menuItem = menu.findItem(R.id.action_update)
                menuItem.isVisible = false
            }
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}