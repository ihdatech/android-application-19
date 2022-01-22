package io.github.ihdatech.myapplication.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.ihdatech.myapplication.data.model.LoggedInHomeData
import io.github.ihdatech.myapplication.databinding.FragmentHomeItemBinding

class HomeAdapter(private var contentList: ArrayList<LoggedInHomeData>?) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentHomeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = contentList[position]
        holder.contentView.text = item.name
    }

    override fun getItemCount(): Int = contentList.size

    inner class ViewHolder(binding: FragmentHomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val contentView: TextView = binding.content

        override fun toString(): String { return super.toString() + " '" + contentView.text + "'" }
    }
}