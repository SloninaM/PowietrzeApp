package maciej.s.powietrzeapi.model.airquality

data class StationAirQuality(
    val id: Int,
    val stCalcDate: String,
    val stIndexLevel: IndexLevel,
    val stSourceDataDate: String,
    val so2CalcDate: String,
    val so2IndexLevel: IndexLevel,
    val so2SourceDataDate: String,
    val no2CalcDate: String,
    val no2IndexLevel: IndexLevel,
    val no2SourceDataDate: String,
    val pm10CalcDate: String,
    val pm10IndexLevel: IndexLevel,
    val pm10SourceDataDate: String,
    val pm25CalcDate: String,
    val pm25IndexLevel: IndexLevel,
    val pm25SourceDataDate: String,
    val o3CalcDate: String,
    val o3IndexLevel: IndexLevel,
    val o3SourceDataDate: String,
    val stIndexStatus: Boolean,
    val stIndexCrParam: String
)