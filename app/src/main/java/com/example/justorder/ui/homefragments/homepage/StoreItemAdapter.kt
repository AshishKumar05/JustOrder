package com.example.justorder.ui.homefragments.homepage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.justorder.R
import com.example.justorder.data.store.StoreItem
import com.example.justorder.utils.Constants

class StoreItemAdapter : RecyclerView.Adapter<StoreItemAdapter.MyViewHolder>(){



//    var item1: StoreItem= StoreItem(123,"Burger","sadsf",40,24,"1234444","weeww")
//    var item2: StoreItem=StoreItem(123,"Pizza","sadsf",100,24,"1234444","weeww")
//    var item3: StoreItem=StoreItem(123,"Samosa","sadsf",10,24,"1234444","weeww")
//    var item4: StoreItem=StoreItem(123,"Barfi","sadsf",300,24,"1234444","weeww")
//    var item5: StoreItem=StoreItem(123,"Gojhiya","sadsf",10,24,"1234444","weeww")
    var listdata = ArrayList<StoreItem>()

    fun setListData(list: ArrayList<StoreItem>){
        this.listdata = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return MyViewHolder(
            inflater,
            parent.context
        )
    }

    override fun getItemCount(): Int {
        return listdata.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listdata[position])
    }


    class MyViewHolder(view: View, var context:Context): RecyclerView.ViewHolder(view){

        val title: TextView = view.findViewById(R.id.name)
        val price: TextView = view.findViewById(R.id.price)
        val image: ImageView = view.findViewById(R.id.image);
        fun bind(item: StoreItem){
            title.text = item.name
            price.text = "Rs. "+item.price.toString()
            var url = Constants.BASE_URL+item.logo

            if(url!=null) {
                Glide.with(context)
                    .load(url)
                    .centerCrop()
                    .into(image)
            }
        }
    }
}