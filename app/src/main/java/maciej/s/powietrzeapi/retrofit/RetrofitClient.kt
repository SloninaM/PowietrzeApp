package maciej.s.powietrzeapi.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient{


    companion object {
        lateinit var api: RetrofitApi
        @Volatile
        var INSTANCE: RetrofitClient? = null
        fun getInstance(): RetrofitClient {
            return INSTANCE?: synchronized(this){
                val retrofit = Retrofit.Builder().baseUrl(RetrofitApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                api = retrofit.create(RetrofitApi::class.java)
                val retrofitInstance = retrofit.create(RetrofitClient::class.java)
                INSTANCE = retrofitInstance
                retrofitInstance
            }
        }
    }
    
}
