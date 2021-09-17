package maciej.s.powietrzeapi.model.sensor

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Param(
    val paramName: String,
    val paramFormula: String,
    val paramCode: String,
    val idParam: String
): Parcelable