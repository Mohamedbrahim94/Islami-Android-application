package android.example.islamiproject.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiManager {
    //we will use singleton method

    companion object {
        private var retrofit: Retrofit? = null


        @Synchronized
        private fun getInstance(): Retrofit { //multithreading
            if (retrofit == null) {
                //build retrofit object using builder method
                retrofit = Retrofit.Builder()
                    .baseUrl("http://api.mp3quran.net")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                return retrofit!!
            }
            return retrofit!!
        }

        fun getApis(): WebServices {
            return getInstance().create(WebServices::class.java)

        }
    }

}

