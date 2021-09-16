package maciej.s.powietrzeapi

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import maciej.s.powietrzeapi.model.station.Station
import maciej.s.powietrzeapi.model.sensor.Sensor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repo: MainRepository): ViewModel() {

    private lateinit var stationList: List<Station>

    private val _sensorOnStationList = MutableLiveData<List<Sensor>>()
        val sensorOnStationList: LiveData<List<Sensor>>
            get() = _sensorOnStationList

    private val _sensorOnStationError = MutableLiveData<String>()
        val sensorOnStationError: LiveData<String>
            get() = _sensorOnStationError

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
    }
    fun getSensorsOnStation(stationId: Int){
        viewModelScope.launch {
            val response = repo.getSensorsOnStation(stationId)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    _sensorOnStationList.postValue(response.body())
                }else{
                    _sensorOnStationError.postValue(response.message())
                }
            }
        }
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