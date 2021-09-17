package maciej.s.powietrzeapi

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import maciej.s.powietrzeapi.model.airquality.StationAirQuality
import maciej.s.powietrzeapi.model.station.Station
import maciej.s.powietrzeapi.model.sensor.Sensor
import maciej.s.powietrzeapi.model.sensordata.SensorData
import retrofit2.Response

class MainViewModel(private val repo: MainRepository): ViewModel() {

    private val _stationsList = MutableLiveData<List<Station>>()
    val stationsList: LiveData<List<Station>>
        get() = _stationsList

    private val _stationsAirQualityList = MutableLiveData<StationAirQuality>()
    val stationsAirQualityList: LiveData<StationAirQuality>
        get() = _stationsAirQualityList

    private val _sensorMeasurementDataList = MutableLiveData<SensorData>()
    val sensorMeasurementDataList: LiveData<SensorData>
        get() = _sensorMeasurementDataList

    private val _sensorOnStationList = MutableLiveData<List<Sensor>>()
        val sensorOnStationList: LiveData<List<Sensor>>
            get() = _sensorOnStationList

    private val _error = MutableLiveData<String>()
        val error: LiveData<String>
            get() = _error

    fun getAllStation() {
        viewModelScope.launch {
            val response = repo.getAllStation()
            setMutableLiveDataValue(response,_stationsList)
        }
    }

    fun getAirQualityIndex(stationId: Int) {
        viewModelScope.launch {
            val response = repo.getAirQualityIndex(stationId)
            setMutableLiveDataValue(response,_stationsAirQualityList)
        }
    }


    fun getSensorMeasurementData(sensorId: Int) {
        viewModelScope.launch {
            val response = repo.getSensorMeasurementData(sensorId)
            setMutableLiveDataValue(response,_sensorMeasurementDataList)
        }
    }

    fun getSensorsOnStation(stationId: Int){
        viewModelScope.launch {
            val response = repo.getSensorsOnStation(stationId)
            setMutableLiveDataValue(response,_sensorOnStationList)
        }
    }


    private suspend fun <T> setMutableLiveDataValue(response: Response<T>, _mutableLiveData:MutableLiveData<T>) {
        withContext(Dispatchers.Main) {
            if (response.isSuccessful) {
                _mutableLiveData.postValue(response.body())
            } else {
                _error.postValue(response.message())
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