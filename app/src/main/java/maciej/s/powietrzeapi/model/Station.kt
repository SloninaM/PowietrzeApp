package maciej.s.powietrzeapi.model

data class Station(
    val id: Int,
    val stationName: String,
    val gegrLat: String,
    val gegrLon: String,
    val city: City,
    val addressStreet: String
)

