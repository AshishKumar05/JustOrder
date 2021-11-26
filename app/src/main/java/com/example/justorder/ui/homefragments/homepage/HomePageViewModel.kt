package com.example.justorder.ui.homefragments.homepage

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.justorder.JustOrderApplication
import com.example.justorder.data.APIManager
import com.example.justorder.data.APIResponse
import com.example.justorder.data.restraurant.RestraurantResponse
import com.example.justorder.data.store.StoreResponse

class HomePageViewModel(application: Application) : AndroidViewModel(application) {

    var app: JustOrderApplication = application as JustOrderApplication
    var apiManager: APIManager = app.getAPIManager()!!
    var homePageItemRepository: HomePageRepository = HomePageRepository(apiManager)

    //    storeItemRepository= app!!.getAPIManager()?.let { StoreRepository(it) }



    fun getRestraurants(): MutableLiveData<APIResponse<RestraurantResponse>>? {
        Log.e("MediapostViewModel", "get_mediapost: ")
        return homePageItemRepository?.getRestraurants()
    }

    fun getItems(): MutableLiveData<APIResponse<StoreResponse>>? {
        Log.e("MediapostViewModel", "get_mediapost: ")
        return homePageItemRepository?.getItems()
    }


}