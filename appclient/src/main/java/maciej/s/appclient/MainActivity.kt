package maciej.s.appclient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import maciej.s.powietrzeapi.model.sensor.Sensor

class MainActivity : AppCompatActivity(),View.OnClickListener {

    private val GET_SENSOR_ON_STATION = "GET_SENSOR_ON_STATION"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.btnGetSensor)
        button.setOnClickListener(this)
        receiveIntent()
    }


    private fun receiveIntent() {
        val bundle = intent.getBundleExtra(GET_SENSOR_ON_STATION)
        val parelable = bundle?.getParcelableArrayList<Sensor>(GET_SENSOR_ON_STATION)
        Log.i("tesst",parelable.toString())
    }

    override fun onClick(v: View?) {

        val intent = Intent().apply{
            action = "maciej.s.powietrzeapi.LAUNCH_IT"
            putExtra(GET_SENSOR_ON_STATION,14)
        }
        startActivity(intent)
    }
}