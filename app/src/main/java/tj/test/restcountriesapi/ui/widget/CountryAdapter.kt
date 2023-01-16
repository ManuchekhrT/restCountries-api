package tj.test.restcountriesapi.ui.widget

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import tj.test.restcountriesapi.databinding.ItemCountryBinding
import tj.test.restcountriesapi.extensions.ImageExtension
import tj.test.restcountriesapi.ui.model.Country

class CountryAdapter(private val itemClickListener: (Country) -> Unit) :
    ListAdapter<Country, RecyclerView.ViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = ItemCountryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as PhotoViewHolder).apply {
            bind(item)
        }
    }

    inner class PhotoViewHolder(
        private val binding: ItemCountryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Country) {
            if (item.flags.containsKey(ImageExtension.png.name)) {
                Glide.with(itemView.context)
                    .load(item.flags[ImageExtension.png.name])
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(binding.ivFlag)
            }
            binding.tvName.text = item.name
            binding.root.setOnClickListener {
                itemClickListener.invoke(item)
            }
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }
}