package com.cibertec.myciberapps06

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.myciberapps06.databinding.ItemTourismBinding
import com.squareup.picasso.Picasso

class TourismAdapter(
    private val listTourism: List<Tourism>,
    private val onItemClick: (Tourism) -> Unit
) : RecyclerView.Adapter<TourismViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourismViewHolder {
        val binding = ItemTourismBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TourismViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TourismViewHolder, position: Int) {
        val currentItem = listTourism[position]
        holder.bind(currentItem, onItemClick)
    }

    override fun getItemCount() = listTourism.size


}

class TourismViewHolder(private val binding: ItemTourismBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Tourism, onItemClick: (Tourism) -> Unit) {

        binding.tourismName.text = item.name
        binding.tourismDescription.text = item.description

        Picasso.get()
            .load(item.imageUrl)
            .placeholder(R.drawable.ic_placeholder_tourism)
            .error(R.drawable.ic_placeholder_error)
            .into(binding.tourismImage)

        itemView.setOnClickListener {
            onItemClick(item)
        }

    }
}