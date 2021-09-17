package maciej.s.powietrzeapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import maciej.s.powietrzeapi.model.sensor.Sensor
import maciej.s.powietrzeapi.retrofit.RetrofitClient

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val RETROFIT_TAG = "retrofit"
    private val GET_SENSOR_ON_STATION = "GET_SENSOR_ON_STATION"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        setObservers()
        receiveIntent()
        //viewModel.getSensorsOnStation(14) //dziala
        //viewModel.getAllStation() //dziala
        //viewModel.getAirQualityIndex(52) //dziala
        //viewModel.getSensorMeasurementData(92) //dziala
    }

    private fun receiveIntent() {
        val intent = intent
        val sensorValue = intent.getIntExtra(GET_SENSOR_ON_STATION,-1)
        if(sensorValue != -1){
            viewModel.getSensorsOnStation(sensorValue)
        }
    }

    private fun setObservers() {
        viewModel.sensorOnStationList.observe(this,{
            onIntentCallback(it)
            //displayRetrofitLog(it.toString())
        })

        viewModel.sensorMeasurementDataList.observe(this,{
            displayRetrofitLog(it.toString())
        })

        viewModel.stationsAirQualityList.observe(this,{
            displayRetrofitLog(it.toString())
        })

        viewModel.stationsList.observe(this,{
            displayRetrofitLog(it.toString())
        })

        viewModel.error.observe(this,{
            displayRetrofitLog(it)
        })
    }

    private fun onIntentCallback(list: List<Sensor>) {
        val arrayList = list as ArrayList
        val bundle = Bundle()
        bundle.putParcelableArrayList(GET_SENSOR_ON_STATION, arrayList)
        val intent = Intent().apply{
            action = "maciej.s.appclient.LAUNCH_IT"
            putExtra(GET_SENSOR_ON_STATION,bundle)
        }
        startActivity(intent)
    }

    private fun displayRetrofitLog(string: String) {
        if(string.isEmpty()){
            Log.i(RETROFIT_TAG, "empty")
        }else{
            Log.i(RETROFIT_TAG, string)
        }
    }

    private fun initViewModel() {
        val retrofitClient = RetrofitClient.getInstance()
        val api = retrofitClient.api
        val repo = MainRepository(api)

        val viewModelFactory = MainViewModelFactory(repo)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }
}