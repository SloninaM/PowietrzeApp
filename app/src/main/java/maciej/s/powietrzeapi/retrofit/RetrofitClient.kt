package maciej.s.powietrzeapi.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    val api: RetrofitApi

    init {
        val retrofit = Retrofit.Builder().baseUrl(RetrofitApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(RetrofitApi::class.java)
    }


    companion object {
        @Volatile
        var INSTANCE: RetrofitClient? = null
        fun getInstance(): RetrofitClient {
            return INSTANCE?: synchronized(this){
                val instance = RetrofitClient()
                INSTANCE = instance
                instance
            }
        }
    }
    
}
