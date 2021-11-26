package com.example.justorder.ui.homefragments.homepage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.justorder.data.APIManager
import com.example.justorder.data.APIResponse
import com.example.justorder.data.restraurant.RestraurantResponse
import com.example.justorder.data.store.StoreResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class HomePageRepository {

    var apiManager: APIManager
    constructor(apiManager: APIManager){
        this.apiManager = apiManager
    }

    fun getRestraurants(): MutableLiveData<APIResponse<RestraurantResponse>>? {
        val apiResponse: APIResponse<RestraurantResponse> = APIResponse()
        val mutableLiveData: MutableLiveData<APIResponse<RestraurantResponse>> = MutableLiveData<APIResponse<RestraurantResponse>>()
        val get_itemlist: Call<RestraurantResponse> = apiManager.getRestraurants()
        get_itemlist.enqueue(object : Callback<RestraurantResponse> {
            override fun onResponse(call: Call<RestraurantResponse>, response: Response<RestraurantResponse>) {
                Log.d("MediapostRepository", "onResponse: ")
                if (!response.isSuccessful()) {
                    Log.d("MediapostRepository", "onResponseFail: ")
                    try {
                        apiResponse.setError(true)
                        apiResponse.setErrorMessage(response.errorBody()!!.string())
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                } else {
                    Log.d("MediapostRepository", "onResponseSuccess: Ashish")
                //    response.body()?.let { apiResponse.setResponse(it) }
                       apiResponse.setResponse(response.body()!!)
                }
                mutableLiveData.setValue(apiResponse)
            }

            override fun onFailure(call: Call<RestraurantResponse>, t: Throwable) {
                Log.d("MediapostRepository", "onFailures: ")
                apiResponse.setError(true)
                apiResponse.setErrorMessage(t.message)
                mutableLiveData.setValue(apiResponse)
            }
        })
        return mutableLiveData
    }

    fun getItems(): MutableLiveData<APIResponse<StoreResponse>>? {
        val apiResponse: APIResponse<StoreResponse> = APIResponse()
        val mutableLiveData: MutableLiveData<APIResponse<StoreResponse>> = MutableLiveData<APIResponse<StoreResponse>>()
        val get_itemlist: Call<StoreResponse> = apiManager.getItemList()
        get_itemlist.enqueue(object : Callback<StoreResponse> {
            override fun onResponse(call: Call<StoreResponse>, response: Response<StoreResponse>) {
                Log.d("MediapostRepository", "onResponse: ")
                if (!response.isSuccessful()) {
                    Log.d("MediapostRepository", "onResponseFail: ")
                    try {
                        apiResponse.setError(true)
                        apiResponse.setErrorMessage(response.errorBody()!!.string())
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                } else {
                    Log.d("MediapostRepository", "onResponseSuccess: Ashish")
                    //    response.body()?.let { apiResponse.setResponse(it) }
                    apiResponse.setResponse(response.body()!!)
                }
                mutableLiveData.setValue(apiResponse)
            }

            override fun onFailure(call: Call<StoreResponse>, t: Throwable) {
                Log.d("MediapostRepository", "onFailures: ")
                apiResponse.setError(true)
                apiResponse.setErrorMessage(t.message)
                mutableLiveData.setValue(apiResponse)
            }
        })
        return mutableLiveData
    }
}