package maciej.s.powietrzeapi.retrofit

import maciej.s.powietrzeapi.model.Station
import maciej.s.powietrzeapi.model.sensordata.SensorData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitApi {

    companion object {
        const val BASE_URL = "http://api.gios.gov.pl/pjp-api/rest/"
    }

    @GET("station/findAll")
    fun getAllStations(): Call<List<Station>>

    @GET("station/sensors/{stationId}")
    fun getSensorData(@Path("stationId") stationId: Int): Call<List<SensorData>>

}