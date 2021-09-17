package maciej.s.powietrzeapi

import maciej.s.powietrzeapi.retrofit.RetrofitApi

class MainRepository(private val api: RetrofitApi) {

    suspend fun getAllStation() = api.getAllStations()

    suspend fun getSensorsOnStation(stationId: Int) = api.getSensorsOnStation(stationId)

    suspend fun getAirQualityIndex(stationId: Int) = api.getAirQualityIndex(stationId)

    suspend fun getSensorMeasurementData(sensorId:Int) = api.getSensorMeasurementData(sensorId)
}