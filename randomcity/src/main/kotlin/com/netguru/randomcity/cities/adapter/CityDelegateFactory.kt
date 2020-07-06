package com.netguru.randomcity.cities.adapter

import android.widget.TextView
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.netguru.randomcity.R
import com.netguru.randomcity.cities.data.CityAdapterItem
import com.netguru.randomcity.core.view.AdapterItem
import javax.inject.Inject

class CityDelegateFactory @Inject constructor() {

    fun create(onCityClick: (CityAdapterItem) -> Unit) = adapterDelegate<CityAdapterItem, AdapterItem>(R.layout.delegate_city) {
        val name: TextView = itemView.findViewById(R.id.name)
        val timestamp: TextView = itemView.findViewById(R.id.timestamp)

        bind {
            name.text = item.name
            name.setTextColor(item.textColor)
            timestamp.text = context.getString(R.string.created_at, item.creationTimestamp)
            itemView.setOnClickListener {
                onCityClick(item)
            }
        }
    }
}