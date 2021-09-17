package maciej.s.powietrzeapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import maciej.s.powietrzeapi.retrofit.RetrofitClient

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val RETROFIT_TAG = "retrofit"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        setObservers()
        viewModel.getSensorsOnStation(14) //dziala
        //viewModel.getAllStation() //dziala
        //viewModel.getAirQualityIndex(52) //dziala
        //viewModel.getSensorMeasurementData(92) //dziala
    }

    private fun setObservers() {
        viewModel.sensorOnStationList.observe(this,{
            displayRetrofitLog(it.toString())
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