package com.raywenderlich.android.rwandroidtutorial

class MainPresenter(view: MainContract.View,
                    dependencyInjector: DependencyInjector)
    : MainContract.Presenter {

    private val repository: WeatherRepository = dependencyInjector.weatherRepository()
    private var view: MainContract.View? = view

    override fun onViewCreated() {
        loadWeather()
    }

    override fun onLoadWeatherTapped() {
        loadWeather()
    }

    override fun onDestroy() {
        view = null
    }

    private fun loadWeather() {
        val weather = repository.loadWeather()
        val weatherState = weatherStateForWeather(weather)

        view?.displayWeatherState(weatherState)
    }

    private fun weatherStateForWeather(weather: Weather) : WeatherState {
        if (weather.rain!!.amount!! > 0) {
            return WeatherState.RAIN
        }
        return WeatherState.SUN
    }
}