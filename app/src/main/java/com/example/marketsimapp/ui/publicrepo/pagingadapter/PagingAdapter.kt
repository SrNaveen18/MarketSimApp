package com.example.marketsimapp.ui.publicrepo.pagingadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.marketsimapp.R
import com.example.marketsimapp.databinding.AdapterPagingBinding
import com.example.marketsimapp.model.PublicRepoItem


class PagingAdapter(val listener: View.OnClickListener
) : PagingDataAdapter<PublicRepoItem, PagingViewHolder>(RepoComparator) {


    object RepoComparator : DiffUtil.ItemCallback<PublicRepoItem>() {
        override fun areItemsTheSame(oldItem: PublicRepoItem, newItem: PublicRepoItem): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PublicRepoItem, newItem: PublicRepoItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        holder.onBind(getItem(position), listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        return PagingViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.adapter_paging,
                parent,
                false
            )
        )
    }

}

class PagingViewHolder(private val adapterPagingBinding: AdapterPagingBinding) :
    RecyclerView.ViewHolder(adapterPagingBinding.root) {

    fun onBind(
        repoItem : PublicRepoItem?,
        listener: View.OnClickListener
    ) {
        adapterPagingBinding.repoItem = repoItem
        adapterPagingBinding.cardView.setTag(R.id.cardView,repoItem)
        adapterPagingBinding.cardView.setOnClickListener(listener)
    }
}
