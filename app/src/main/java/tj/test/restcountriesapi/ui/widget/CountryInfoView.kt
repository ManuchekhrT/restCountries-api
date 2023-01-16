package tj.test.restcountriesapi.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import tj.test.restcountriesapi.R
import tj.test.restcountriesapi.databinding.ViewCountryInfoBinding
import tj.test.restcountriesapi.extensions.fromHtml
import tj.test.restcountriesapi.ui.model.Country

class CountryInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding =
        ViewCountryInfoBinding.inflate(LayoutInflater.from(context), this, true)

    var region: CharSequence?
        get() = binding.tvRegion.text
        set(value) {
            binding.tvRegion.text = value
        }

    var capital: CharSequence?
        get() = binding.tvCapital.text
        set(value) {
            binding.tvCapital.text = value
        }

    var currency: CharSequence?
        get() = binding.tvCurrency.text
        set(value) {
            binding.tvCurrency.text = value
        }

    var timezones: CharSequence?
        get() = binding.tvTimezones.text
        set(value) {
            binding.tvTimezones.text = value
        }

    fun setCountryInfo(aCountry: Country) {
        region = String.format(resources.getString(R.string.region), aCountry.region).fromHtml()
        capital =
            if (aCountry.capital.isNullOrBlank()) {
                "-"
            } else {
                String.format(resources.getString(R.string.capital), aCountry.capital).fromHtml()
            }

        currency =
            if (aCountry.currencies.isNotEmpty()) {
                val aCurrency = aCountry.currencies.firstNotNullOf { it }
                String.format(
                    resources.getString(R.string.currency),
                    aCurrency.name, aCurrency.symbol, aCurrency.code
                ).fromHtml()
            } else {
                "-"
            }

        timezones =
            if (aCountry.timezones.isNotEmpty()) {
                val builder = StringBuilder()
                aCountry.timezones.forEach { aTimeZone ->
                    builder
                        .append(aTimeZone)
                        .append("\n")
                }
                String.format(
                    resources.getString(R.string.timezones),
                    builder.toString()
                ).fromHtml()
            } else {
                "-"
            }
    }
}