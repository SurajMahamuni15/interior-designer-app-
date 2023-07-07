package com.example.dreamcraft.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.dreamcraft.R
import com.example.dreamcraft.WorkTypeModel
import com.example.dreamcraft.databinding.WorkItemBinding
import com.example.dreamcraft.viewholder.CategoriesViewHolder
import com.example.dreamcraft.viewholder.WorkTypeViewHolder

class WorkTypeAdapter : Adapter<WorkTypeViewHolder>() {
    private var data = ArrayList<WorkTypeModel>()
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkTypeViewHolder {
        context = parent.context
        val binding =
            WorkItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WorkTypeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: WorkTypeViewHolder, position: Int) {
        holder.binding.workTypeTxt.text = data[position].typeTitle
        Glide.with(context)
            .load(data[position].typeImg)
            .centerCrop()
            .placeholder(R.drawable.app_icon)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(holder.binding.workTypeImg)
    }

    fun setData(newData: ArrayList<WorkTypeModel>) {
        val workDiffUtil = WorkDiffUtil(data, newData)
        val workDiff = DiffUtil.calculateDiff(workDiffUtil)
        data = newData
        workDiff.dispatchUpdatesTo(this)

    }

    class WorkDiffUtil(
        private val oldData: ArrayList<WorkTypeModel>,
        private val newData: ArrayList<WorkTypeModel>
    ) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldData.size
        }

        override fun getNewListSize(): Int {
            return newData.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldData[oldItemPosition] == newData[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldData[oldItemPosition] == newData[newItemPosition]
        }

    }
}