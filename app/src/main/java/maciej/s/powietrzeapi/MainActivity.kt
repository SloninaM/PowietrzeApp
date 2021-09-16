package maciej.s.powietrzeapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import maciej.s.powietrzeapi.retrofit.RetrofitClient

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        //viewModel.getAllStation()
        viewModel.getSensorsOnStation(14)
    }

    private fun initViewModel() {
        val retrofitClient = RetrofitClient.getInstance()
        val api = retrofitClient.api
        val repo = MainRepository(api)

        val viewModelFactory = MainViewModelFactory(repo)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }
}