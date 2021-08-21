package com.example.justorder.ui.restraurant

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.justorder.R
import com.smarteist.autoimageslider.SliderViewAdapter

public class ImageSliderAdapter : SliderViewAdapter<ImageSliderAdapter.ImageViewHolder>() {

    lateinit var list:ArrayList<String>
   fun SetImageList(listdata: ArrayList<String>){
       list=listdata
   }

    override fun onCreateViewHolder(parent: ViewGroup): ImageViewHolder {
      val inflater= LayoutInflater.from(parent.context).inflate(R.layout.item_sliderlayout,parent,false)
        return ImageViewHolder(inflater,parent.context)
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(viewHolder: ImageViewHolder, position: Int) {
        viewHolder.bind(list[position])
    }

    class ImageViewHolder(itemView: View, var context: Context) : ViewHolder(itemView) {

         val image: ImageView = itemView.findViewById(R.id.restraurent_images);
         fun bind(url: String){
             if(url!=null) {
                 Glide.with(context)
                     .load(url)
                     .centerCrop()
                     .into(image)
             }
         }
    }
}