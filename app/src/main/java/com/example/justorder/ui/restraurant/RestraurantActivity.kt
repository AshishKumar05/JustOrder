package com.example.justorder.ui.restraurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.justorder.R
import com.example.justorder.data.APIManager
import com.example.justorder.data.RetroInstance
import com.example.justorder.data.restraurant.Restraurant
import com.example.justorder.data.restraurant.RestraurantResponse
import com.example.justorder.data.store.StoreItem
import com.example.justorder.data.store.StoreResponse
import com.example.justorder.roomdb.CartItem
import com.example.justorder.roomdb.RoomViewModel
import com.example.justorder.ui.Home
import com.example.justorder.utils.Constants
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import retrofit2.Call
import retrofit2.Response

class RestraurantActivity : ItemAdapter.ItemClickListener,AppCompatActivity()  {
    val TAG="RestraurantActivity"
    private var layoutManager:RecyclerView.LayoutManager?=null
    lateinit var itemAdapter: ItemAdapter
    lateinit var name : TextView
    lateinit var rating : TextView
    lateinit var desc: TextView
    lateinit var location: TextView
    lateinit var proceed: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restraurant)
        val actionBar = supportActionBar
        actionBar!!.hide()
        name=findViewById(R.id.restraurent_name)
        rating=findViewById(R.id.rating)
        desc=findViewById(R.id.restraurent_desc)
        location=findViewById(R.id.restraurent_location)
        proceed= findViewById(R.id.btn_proceedtocart)

        var restraId=intent.getStringExtra("resId")
        var restraurantId = restraId?.toLong()
        Toast.makeText(this@RestraurantActivity,"Res ID"+restraurantId,Toast.LENGTH_LONG).show()
        var sliderview: SliderView = findViewById(R.id.image_slider)
        var list=arrayListOf<String>( Constants.BASE_URL+"/image/Makhan%20bhog.jpg",
            Constants.BASE_URL+"/image/GidhariLal.jpg",
            Constants.BASE_URL+"/image/shakti-dhaba.jpg",
            Constants.BASE_URL+"/image/rajput%20dhaba.jpg",
            Constants.BASE_URL+"/image/madan%20bhog.jpg")
        var imageAdapter :ImageSliderAdapter = ImageSliderAdapter()
        imageAdapter.SetImageList(list)
        sliderview.setSliderAdapter(imageAdapter)
        sliderview.setIndicatorAnimation(IndicatorAnimationType.WORM)
        sliderview.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
        sliderview.startAutoCycle()

        proceed.setOnClickListener {
            var roomViewModel = RoomViewModel(application)
            Log.e("ASHISH", "onCreate: "+roomViewModel.getAll() )
            Toast.makeText(this, ""+roomViewModel.getAll(),Toast.LENGTH_LONG).show()
            var intent=Intent(this@RestraurantActivity, Home::class.java)
            intent.putExtra("CartFragment",true)
            overridePendingTransition(0, 0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent)
        }
        var recyclerView:RecyclerView=findViewById(R.id.itemlist)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        itemAdapter = ItemAdapter(applicationContext, ArrayList<StoreItem>(),this)
        recyclerView.adapter=itemAdapter
        if(restraId!=null) {
            Log.e("CALL", "onCreate: "+restraId)
            getRestraurantData(restraId)
            getRestraurantListData(restraId)
        }
        //setItemData()
    }

//    fun setItemData(){
//        val retroInstance = RetroInstance().getMyRetrofit().create(APIManager::class.java)
//        val call = retroInstance.getItemList()
//        call.enqueue(object : retrofit2.Callback<StoreResponse>{
//            override fun onResponse(call: Call<StoreResponse>, response: Response<StoreResponse>) {
//                if(response.isSuccessful){
//
//
//                }
//            }
//
//            override fun onFailure(call: Call<StoreResponse>, t: Throwable) {
//                Toast.makeText(this@RestraurantActivity,"Something went wrong", Toast.LENGTH_LONG).show()
//                Log.e("ASHISH", "onFailure: " )
//                Log.e("ASHISH", "onFailure: "+t.stackTrace )
//            }
//        })
//    }

    fun getRestraurantData(restraurantId: String){
        val retroInstance = RetroInstance().getMyRetrofit().create(APIManager::class.java)
        val call = retroInstance.getOneRestraurant(restraurantId)
        call.enqueue(object : retrofit2.Callback<RestraurantResponse>{
            override fun onResponse(call: Call<RestraurantResponse>, response: Response<RestraurantResponse>) {
                if(response.isSuccessful){
                    Log.e("ASHISH", "onResponse: successfull" )
                 var restraurant =response.body()?.list?.get(0)
                    if(restraurant!=null) {
                        setRestraurantData(restraurant)
                        Log.e("RESTRAURANT", "onResponse: "+restraurant )
                    }
                }
            }

            override fun onFailure(call: Call<RestraurantResponse>, t: Throwable) {
                Toast.makeText(this@RestraurantActivity,"Something went wrong",Toast.LENGTH_LONG).show()
                Log.e("ASHISH", "onFailure: " )
                Log.e("ASHISH", "onFailure: "+t.stackTrace )
            }
        })
    }

    fun getRestraurantListData(restraurantId: String){
        val retroInstance = RetroInstance().getMyRetrofit().create(APIManager::class.java)
        val call = retroInstance.getRestraurantItemList(restraurantId)
        call.enqueue(object : retrofit2.Callback<StoreResponse>{
            override fun onResponse(call: Call<StoreResponse>, response: Response<StoreResponse>) {
                if(response.isSuccessful){
                    Log.e("ASHISH", "onResponse: successfull" )
                    var restraurantItemList =response.body()?.list
                    if(restraurantItemList!=null) {
                        setRestraurantItemListData(restraurantItemList)
                        Log.e("RESTRAURANT", "onResponse: "+restraurantItemList )
                    }
                }
            }

            override fun onFailure(call: Call<StoreResponse>, t: Throwable) {
                Toast.makeText(this@RestraurantActivity,"Something went wrong",Toast.LENGTH_LONG).show()
                Log.e("ASHISH", "onFailure: " )
                Log.e("ASHISH", "onFailure: "+t.stackTrace )
            }
        })
    }

    fun setRestraurantData(restraurant: Restraurant){
        name.text = restraurant.name
        rating.text = restraurant.rating.toString()
        location.text = restraurant.location
        desc.text = restraurant.description
    }

    fun setRestraurantItemListData(restraurantItemList : ArrayList<StoreItem>) {
        itemAdapter.setData(restraurantItemList)
        itemAdapter.notifyDataSetChanged()
    }

    override fun add(cartItem: CartItem) {
        var roomViewModel = RoomViewModel(application)
        Log.e(TAG, "add: "+cartItem.copy() )
        roomViewModel.insertItem(cartItem)
    }

    override fun update(cartItem: CartItem) {
        var roomViewModel = RoomViewModel(application)
        Log.e(TAG, "update: "+cartItem.copy() )
        roomViewModel.insertItem(cartItem)
    }

    override fun delete(cartItem: CartItem) {
        var roomViewModel = RoomViewModel(application)
        Log.e(TAG, "delete: "+cartItem.copy() )
        roomViewModel.insertItem(cartItem)
    }
}