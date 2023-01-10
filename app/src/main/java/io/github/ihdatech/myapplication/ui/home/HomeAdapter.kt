package io.github.ihdatech.myapplication.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.github.ihdatech.myapplication.data.model.LoggedInZodiac
import io.github.ihdatech.myapplication.databinding.FragmentHomeItemBinding

class HomeAdapter(
    private var contentList: List<LoggedInZodiac>,
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentHomeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = contentList[position]
        holder.contentView.text = item.name
        // Glide.with(holder.itemView).load(item.images[0])
        Glide.with(holder.itemView).load("https://media.istockphoto.com/id/184276818/photo/red-apple.jpg?s=612x612&w=0&k=20&c=NvO-bLsG0DJ_7Ii8SSVoKLurzjmV0Qi4eGfn6nW3l5w=")
            .apply(RequestOptions.centerCropTransform())
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = contentList.size

    inner class ViewHolder(binding: FragmentHomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val contentView: TextView = binding.content
        val imageView: ImageView = binding.imageView

        override fun toString(): String { return super.toString() + " '" + contentView.text + "'" }
    }
}