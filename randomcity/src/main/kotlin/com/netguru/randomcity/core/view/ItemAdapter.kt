package com.netguru.randomcity.core.view

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class ItemAdapter(
    delegates: List<AdapterDelegate<List<AdapterItem>>>
) : AsyncListDifferDelegationAdapter<AdapterItem>(AdapterDiffItemCallback) {

    init {
        delegates.forEach {
            delegatesManager.addDelegate(it)
        }
    }
}