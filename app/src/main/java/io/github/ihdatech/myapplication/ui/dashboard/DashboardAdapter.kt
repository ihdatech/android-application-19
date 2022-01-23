package io.github.ihdatech.myapplication.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.github.ihdatech.myapplication.data.model.LoggedInNewsArticles
import io.github.ihdatech.myapplication.databinding.FragmentDashboardItemBinding

class DashboardAdapter(
    private val contextList: Context?,
    private var contentList: ArrayList<LoggedInNewsArticles>,
) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentDashboardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = contentList[position]
        holder.contentView.text = item.title
        contextList?.let {
            Glide.with(it)
                .load("${item.urlToImage}?w=640&h=640&fit=crop&auto=format")
                .apply(RequestOptions.centerCropTransform())
                .into(holder.imageView)
        }
    }

    override fun getItemCount(): Int = contentList.size

    inner class ViewHolder(binding: FragmentDashboardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val contentView: TextView = binding.content
        val imageView: ImageView = binding.imageView

        override fun toString(): String { return super.toString() + " '" + contentView.text + "'" }
    }
}