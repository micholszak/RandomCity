package com.netguru.randomcity.splash

interface SplashContract {

    interface View

    interface Presenter {
        fun onViewCreated(view: View)

        fun onViewDestroyed()
    }
}