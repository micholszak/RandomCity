# RandomCity
## Overview
Every 5 sec. some producer will produce the next value with random City and Color combination from predefined
lists. The producer should be working only if the application is in foreground.
The application starts with Splash and navigates to Main Screen on the first emission of producer.
## Project structure
* **randomcity** - contains all of the code relateed to given task
* **app** - dependency configuration of application
## Dependency overview
### Main dependencies
* [Kotlin](https://kotlinlang.org/)
* [Dagger](https://github.com/google/dagger) - dependency injection
* [Room](https://developer.android.com/training/data-storage/room) - database
* [RxJava](https://github.com/ReactiveX/RxJava) - asynchronicity
* [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - process lifecycle subscriptions
* [GoogleMaps](https://developers.google.com/maps/documentation/android-sdk/intro) - map display
* [Appcompat](https://developer.android.com/jetpack/androidx/releases/appcompat)
* [ConstraintLayout](https://developer.android.com/jetpack/androidx/releases/constraintlayout)
* [AdapterDelegates](https://github.com/sockeqwe/AdapterDelegates) - flexible adapter delegates
### Tesing
* [JUnit5](https://github.com/mannodermaus/android-junit5)
* [Mockito](https://github.com/nhaarman/mockito-kotlin)
* [AssertJ](https://github.com/joel-costigliola/assertj-core)
* [Androidx tools](https://developer.android.com/training/testing/set-up-project)
## Build types
### Debug
For application development, disabled R8 and shrinking
### Release
For application release, non-debuggable with full R8 configuration
