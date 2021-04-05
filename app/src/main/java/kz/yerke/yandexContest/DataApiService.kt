package kz.yerke.yandexContest

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface DataApiService {

    companion object{
        fun create():DataApiService{
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://mboum.com/api/v1/")
                .build()
            return retrofit.create(DataApiService::class.java)
        }



    }
    @GET("qu/quote/")
    fun getDataList(
        @Query("symbol") symbols:String,
        @Query("apikey") key:String
    ):Observable<DataJson>

}