package com.netguru.randomcity.core.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.netguru.randomcity.R

class NavigationBarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var onNavigationClick: () -> Unit = {}

    private val navigationIcon: ImageView
    private val title: TextView

    init {
        View.inflate(context, R.layout.layout_navigation_bar, this)
        navigationIcon = findViewById(R.id.navigation_icon)
        title = findViewById(R.id.title)
        navigationIcon.setOnClickListener {
            onNavigationClick()
        }

        attrs?.let(::applyAttributes)
    }

    fun setTitle(text: String) {
        title.text = text
    }

    fun setBarBackgroundColor(color: Int) {
        setBackgroundColor(color)
    }

    fun setNavigationIconVisibility(visible: Boolean) {
        visibility = if(visible) View.VISIBLE else View.GONE
    }

    private fun applyAttributes(attrs: AttributeSet) {
        with(context.obtainStyledAttributes(attrs, R.styleable.NavigationBarView)) {
            val title = getString(R.styleable.NavigationBarView_title)
            if (!title.isNullOrEmpty()) {
                setTitle(title)
            }
        }
    }
}