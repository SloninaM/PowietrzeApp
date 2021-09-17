package maciej.s.powietrzeapi.model.sensor

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sensor(val id: Int, val stationId: Int, val param: Param):Parcelable