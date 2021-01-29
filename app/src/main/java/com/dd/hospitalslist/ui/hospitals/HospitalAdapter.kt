package com.dd.hospitalslist.ui.hospitals

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.dd.hospitalslist.data.entities.Hospital

class HospitalAdapter : PagedListAdapter<Hospital, HospitalViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
        return HospitalViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HospitalViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Hospital>() {
            override fun areItemsTheSame(oldItem: Hospital, newItem: Hospital): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Hospital, newItem: Hospital): Boolean {
                return oldItem == newItem
            }

        }
    }
}