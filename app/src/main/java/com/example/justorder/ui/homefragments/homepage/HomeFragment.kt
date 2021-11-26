package com.example.justorder.ui.homefragments.homepage


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.justorder.R
import com.example.justorder.data.APIManager
import com.example.justorder.data.APIResponse
import com.example.justorder.data.RetroInstance
import com.example.justorder.data.restraurant.Restraurant
import com.example.justorder.data.restraurant.RestraurantResponse
import com.example.justorder.data.store.StoreItem
import com.example.justorder.data.store.StoreResponse
import com.example.justorder.ui.restraurant.ImageSliderAdapter
import com.example.justorder.ui.restraurant.RestraurantActivity
import com.example.justorder.utils.Constants
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment() {
//item
    private var layoutManager: RecyclerView.LayoutManager? = null
    lateinit var itemAdapter: StoreItemAdapter
//restraurent
    private var layoutManagerRestraurant : RecyclerView.LayoutManager?=null
    lateinit var adapterRestraurant: RestraurentAdapter

    private lateinit var homeViewModel: HomePageViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomePageViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val restraLabel: TextView = root.findViewById(R.id.restraurentlabel)
        restraLabel.setOnClickListener{
           // startActivity(Intent(activity, RestraurantActivity::class.java))
        }
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        var listData : List<StoreItem> = ArrayList()


        var sliderview: SliderView = root.findViewById(R.id.home_image_slider)
        var list=arrayListOf<String>( Constants.BASE_URL+"/image/Makhan%20bhog.jpg",
            Constants.BASE_URL+"/image/GidhariLal.jpg",
            Constants.BASE_URL+"/image/shakti-dhaba.jpg",
            Constants.BASE_URL+"/image/rajput%20dhaba.jpg",
            Constants.BASE_URL+"/image/madan%20bhog.jpg")
        var imageAdapter : ImageSliderAdapter = ImageSliderAdapter()
        imageAdapter.SetImageList(list)
        sliderview.setSliderAdapter(imageAdapter)
        sliderview.setIndicatorAnimation(IndicatorAnimationType.WORM)
        sliderview.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
        sliderview.startAutoCycle()
//        val profile : ImageView = root.findViewById(R.id.image)
        layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        val recylerview : RecyclerView = root.findViewById(R.id.itemlist)
        recylerview.layoutManager = layoutManager
        itemAdapter = StoreItemAdapter()
        recylerview.adapter= itemAdapter

      //  setItemData()

        homeViewModel.getItems()?.observe(requireActivity(), { response ->
            if (response.isError()) {

            } else {
                var itemResponse: StoreResponse? = response.getResponse()
                itemResponse?.list?.let { itemAdapter.setListData(it) }
                itemAdapter.notifyDataSetChanged()
                Log.e("ASHISH", "onCreateView: Items" )
            }
        })



        layoutManagerRestraurant = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        val recyclerViewRestraurant: RecyclerView = root.findViewById(R.id.restraurentlist)
        recyclerViewRestraurant.layoutManager = layoutManagerRestraurant
        var listRestraurantdata = ArrayList<Restraurant>()
        adapterRestraurant =
            RestraurentAdapter(listRestraurantdata, object : Itemclicklinstener {
                override fun onRestraurentclick(id: Long) {
                    Log.e("HOME", "bind: "+id )
                    var intent = Intent(activity, RestraurantActivity::class.java)
                    intent.putExtra("resId",id.toString())
                    startActivity(intent)
                }
            })
        recyclerViewRestraurant.adapter = adapterRestraurant
        homeViewModel.getRestraurants()?.observe(requireActivity(), Observer { response ->
            if(response.isError()){

            } else{
                var storeResponse : RestraurantResponse? = response.getResponse()
                adapterRestraurant.setRestraurantData(storeResponse?.list as ArrayList<Restraurant>)
                adapterRestraurant.notifyDataSetChanged()
                Log.e("ASHISH", "onCreateView: Restraurent" )
            }
        })

     //   setRestraurantData()
   //     getRestraurantList()
        return root
    }

    fun setItemData(){
        val retroInstance = RetroInstance().getMyRetrofit().create(APIManager::class.java)
        val call = retroInstance.getItemList()
        call.enqueue(object : retrofit2.Callback<StoreResponse>{
            override fun onResponse(call: Call<StoreResponse>, response: Response<StoreResponse>) {
               if(response.isSuccessful){

                   itemAdapter.setListData(response.body()?.list!!)
                   itemAdapter.notifyDataSetChanged()
               }
            }

            override fun onFailure(call: Call<StoreResponse>, t: Throwable) {
                Toast.makeText(activity,"Something went wrong",Toast.LENGTH_LONG).show()
                Log.e("ASHISH", "onFailure: " )
                Log.e("ASHISH", "onFailure: "+t.stackTrace )
            }
        })
    }

//    fun setRestraurantData(){
//        val retroInstance = RetroInstance().getMyRetrofit().create(APIManager::class.java)
//        val call = retroInstance.getRestraurants()
//        call.enqueue(object : retrofit2.Callback<RestraurantResponse>{
//            override fun onResponse(call: Call<RestraurantResponse>, response: Response<RestraurantResponse>) {
//                if(response.isSuccessful){
//                    adapterRestraurant.setRestraurantData(response.body()?.list!!)
//                    adapterRestraurant.notifyDataSetChanged()
//                }
//            }
//
//            override fun onFailure(call: Call<RestraurantResponse>, t: Throwable) {
//                Toast.makeText(activity,"Something went wrong",Toast.LENGTH_LONG).show()
//                Log.e("ASHISH", "onFailure: " )
//                Log.e("ASHISH", "onFailure: "+t.stackTrace )
//            }
//        })
//    }

//    fun getRestraurantList(){
//         homeViewModel.getRestraurants()
//             ?.observe(viewLifecycleOwner, Observer<APIResponse<RestraurantResponse>>() {
//                 Log.e("ASHISH", "getRestraurantList: "+it.getResponse()?.message )
//             })
//    }
}