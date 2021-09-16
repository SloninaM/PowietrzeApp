package maciej.s.powietrzeapi

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import maciej.s.powietrzeapi.model.Station
import maciej.s.powietrzeapi.model.sensor.SensorData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repo: MainRepository): ViewModel() {

    private lateinit var stationList: List<Station>

    fun getAllStation() {
        val call = repo.getAllStation()
        call.enqueue(object:Callback<List<Station>>{
            override fun onResponse(call: Call<List<Station>>, response: Response<List<Station>>) {
                Log.i("retrofit","response")
                //Log.i("retrofit",response.body().toString())
            }

            override fun onFailure(call: Call<List<Station>>, t: Throwable) {
                Log.i("retrofit","failure")
                Log.i("retrofit","${t.message}")
            }
        })
//        if(response.isSuccessful){
//            val body = response.body()
//            if(body != null){
//                stationList = body
//            }
//        }
    }
    fun getSensorsOnStation(stationId: Int){
        val call = repo.getSensorsOnStation(stationId)
        call.enqueue(object:Callback<List<SensorData>>{
            override fun onResponse(call: Call<List<SensorData>>, response: Response<List<SensorData>>) {
                Log.i("retrofit","response")
                Log.i("retrofit",response.body().toString())
            }

            override fun onFailure(call: Call<List<SensorData>>, t: Throwable) {
                Log.i("retrofit","failure")
                Log.i("retrofit","${t.message}")
            }
        })
    }

}

class MainViewModelFactory(private val repo: MainRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}