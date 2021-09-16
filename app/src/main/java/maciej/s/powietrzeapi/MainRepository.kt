package maciej.s.powietrzeapi

import maciej.s.powietrzeapi.retrofit.RetrofitApi

class MainRepository(private val api: RetrofitApi) {

    fun getAllStation() = api.getAllStations()

    fun getSensorsOnStation(stationId: Int) = api.getSensorsOnStation(stationId)
}