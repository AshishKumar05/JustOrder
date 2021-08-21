package com.example.justorder.ui.restraurant

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.justorder.R
import com.example.justorder.data.RetroInstance
import com.example.justorder.data.store.StoreItem
import com.example.justorder.utils.Constants
import com.smarteist.autoimageslider.SliderViewAdapter

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {


    var list = ArrayList<StoreItem>()

    fun setData(listData: ArrayList<StoreItem>){
        list=listData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
       val layoutInflater=LayoutInflater.from(parent.context).inflate(R.layout.item_restraurant_layout,parent,false)
    return ItemViewHolder(layoutInflater,parent.context)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
       holder.bind(list[position])
    }

    class ItemViewHolder(itemView: View, var context: Context) : RecyclerView.ViewHolder(itemView) {

        var name:TextView = itemView.findViewById(R.id.item_name)
        var logo:ImageView = itemView.findViewById(R.id.image)
        var desc: TextView = itemView.findViewById(R.id.item_desc)
        var add : ConstraintLayout = itemView.findViewById(R.id.add)
        var addcount : ConstraintLayout = itemView.findViewById(R.id.addlayout)
        var itemcount : TextView = itemView.findViewById(R.id.count)
        var itemplus : ConstraintLayout = itemView.findViewById(R.id.plus)
        var itemminus : ConstraintLayout = itemView.findViewById(R.id.minus)

        var count = 1;
         fun bind(item:StoreItem){
             add.visibility = VISIBLE
             addcount.visibility = GONE
             name.text=item.name
             desc.text=item.description
             itemcount.text = ""+count;
             var url=Constants.BASE_URL+item.logo
             if(url!=null){
                 Glide.with(context)
                     .load(url)
                     .centerCrop()
                     .into(logo)
             }
             add.setOnClickListener{
                 add.visibility = GONE
                 addcount.visibility = VISIBLE
                 count=1
                 itemcount.text = ""+count;
             }
             itemplus.setOnClickListener{
                 count++
                 itemcount.text = ""+count;
                 if(count==0) {
                     add.visibility = VISIBLE
                     addcount.visibility = GONE
                 }
             }
             itemminus.setOnClickListener{
                 count--
                 itemcount.text = ""+count;
                 if(count==0) {
                     add.visibility = VISIBLE
                     addcount.visibility = GONE
                 }
             }
         }
    }
}