package com.example.justorder.ui.homefragments.homepage

import android.content.Context
import android.graphics.Outline
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.justorder.R
import com.example.justorder.data.restraurant.Restraurant
import com.example.justorder.utils.Constants

class RestraurentAdapter(var restraurentList: ArrayList<Restraurant>, var listener:Itemclicklinstener) : RecyclerView.Adapter<RestraurentAdapter.RestraurentViewHolder>() {

     lateinit var context : Context
//    var item1: Restraurant = Restraurant(1234,"Girdhari Lal Gojhiya vale","", "Best sweet in Market",4.8,"Jangiganj bhadohi")
//    var item2: Restraurant = Restraurant(1234,"Ajay Vijay Sweet Hounse","", "Best sweet in Market",4.5,"Dhantulsi morh, Jangiganj bhadohi")
//    var item3: Restraurant = Restraurant(1234,"Madan Sweet hounse","", "Best sweet in Market",4.6,"Dhantulsi morh, Jangiganj bhadohi")
//    var item4: Restraurant = Restraurant(1234,"Shakti Dhaba","", "Best sweet in Market",4.3,"Kaulapur Bhadohi")
//    var item5: Restraurant = Restraurant(1234,"Bhole Dhaba","", "Best sweet in Market",4.7,"Vahida Bagar Bhadohi")
//    var item6: Restraurant = Restraurant(1234,"Rajput Dhabha","", "Best sweet in Market",3.8,"Gopi Ganj Bhadohi")
//    var restraurentList: List<Restraurant> = mutableListOf<Restraurant>(item1,item2,item3,item4,item5,item6)


    fun setRestraurantData(list: ArrayList<Restraurant>){
        this.restraurentList = list
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestraurentViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_layout,parent,false)
        return RestraurentViewHolder(
            inflater,
            parent.context,
            listener
        )
    }

    override fun getItemCount(): Int {
      return restraurentList.size
    }

    override fun onBindViewHolder(holder: RestraurentViewHolder, position: Int) {
      holder.bind(restraurentList[position])
    }

    class RestraurentViewHolder(itemView: View,var context: Context,var listener:Itemclicklinstener) : RecyclerView.ViewHolder(itemView) {

        var name: TextView = itemView.findViewById(R.id.restraurent_name)
        var location: TextView = itemView.findViewById(R.id.restraurent_location)
        var rating: TextView = itemView.findViewById(R.id.restraurent_rating)
        var image : ImageView = itemView.findViewById(R.id.restraurent_image)
        fun bind(item: Restraurant) {
            name.text = item.name
            location.text = item.location
            rating.text = item.rating.toString()
            var url = Constants.BASE_URL+item.logo
            val curveRadius = 40F
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                image.outlineProvider = object : ViewOutlineProvider() {

                    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                    override fun getOutline(view: View?, outline: Outline?) {
                        outline?.setRoundRect(0, 0, view!!.width, (view.height+curveRadius).toInt(), curveRadius)
                    }
                }
                image.clipToOutline = true
            }

            if(url!=null){
                Glide.with(context)
                    .load(url)
                    .centerCrop()
                    .into(image)
            }
            itemView.setOnClickListener{
                Log.e("ADAPTER", "bind: "+item.restraurantId )
                  listener.onRestraurentclick(item.restraurantId)
            }
        }
    }

}

interface Itemclicklinstener{
    fun onRestraurentclick(id:Long)
}