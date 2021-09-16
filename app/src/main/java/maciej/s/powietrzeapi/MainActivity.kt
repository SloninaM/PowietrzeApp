package maciej.s.powietrzeapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import maciej.s.powietrzeapi.retrofit.RetrofitClient

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        setObservers()
        viewModel.getSensorsOnStation(14)
    }

    private fun setObservers() {
        viewModel.sensorOnStationList.observe(this,{
            Log.i("retrofit","response")
            Log.i("retrofit","$it")
        })

        viewModel.sensorOnStationError.observe(this,{
            Log.i("retrofit",it)
        })
    }

    private fun initViewModel() {
        val retrofitClient = RetrofitClient.getInstance()
        val api = retrofitClient.api
        val repo = MainRepository(api)

        val viewModelFactory = MainViewModelFactory(repo)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }
}