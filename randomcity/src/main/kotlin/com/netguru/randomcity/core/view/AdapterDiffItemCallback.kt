package com.netguru.randomcity.core.view

import androidx.recyclerview.widget.DiffUtil

object AdapterDiffItemCallback : DiffUtil.ItemCallback<AdapterItem>() {
    override fun areItemsTheSame(oldItem: AdapterItem, newItem: AdapterItem): Boolean =
        oldItem.isTheSameAs(newItem)

    override fun areContentsTheSame(oldItem: AdapterItem, newItem: AdapterItem): Boolean =
        oldItem.hasTheSameContentAs(newItem)
}