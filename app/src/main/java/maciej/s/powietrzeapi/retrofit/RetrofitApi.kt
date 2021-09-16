package maciej.s.powietrzeapi.retrofit

import maciej.s.powietrzeapi.model.station.Station
import maciej.s.powietrzeapi.model.sensor.Sensor
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
    fun getSensorsOnStation(@Path("stationId") stationId: Int): Call<List<Sensor>>

}