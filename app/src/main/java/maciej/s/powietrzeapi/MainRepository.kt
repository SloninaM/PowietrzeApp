package maciej.s.powietrzeapi

import maciej.s.powietrzeapi.retrofit.RetrofitApi
import maciej.s.powietrzeapi.retrofit.RetrofitClient

class MainRepository(private val api: RetrofitApi) {

    fun getAllStation() = api.getAllStations()

    fun getSensorData(stationId: Int) = api.getSensorData(stationId)
}