package maciej.s.appclient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(),View.OnClickListener {

    private val GET_SENSOR_ON_STATION = "GET_SENSOR_ON_STATION"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.btnGetSensor)
        button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        val intent = Intent().apply{
            action = "maciej.s.powietrzeapi.LAUNCH_IT"
            putExtra(GET_SENSOR_ON_STATION,14)
        }
        startActivity(intent)
    }
}