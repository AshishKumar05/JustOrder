package com.example.justorder.ui.homefragments.cart

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
import com.example.justorder.roomdb.CartItem
import com.example.justorder.utils.Constants

class CartAdapter(var cartList: ArrayList<CartItem>, var cartlistner: CartItemClickLestener): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    fun setCartlist(cartList: ArrayList<CartItem>){
        this.cartList=cartList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_restraurant_layout,parent,false)
        return CartViewHolder(inflater,parent.context,cartlistner)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cartList[position])
    }

    override fun getItemCount(): Int {
       return cartList.size
    }

    class CartViewHolder(itemView: View, var context: Context,var linstener: CartItemClickLestener) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.item_name)
        var logo: ImageView = itemView.findViewById(R.id.image)
        var desc: TextView = itemView.findViewById(R.id.item_desc)
        var add : ConstraintLayout = itemView.findViewById(R.id.add)
        var addcount : ConstraintLayout = itemView.findViewById(R.id.addlayout)
        var itemcount : TextView = itemView.findViewById(R.id.count)
        var itemplus : ConstraintLayout = itemView.findViewById(R.id.plus)
        var itemminus : ConstraintLayout = itemView.findViewById(R.id.minus)

          fun bind(item: CartItem){
              add.visibility = GONE
              addcount.visibility = VISIBLE
              name.text=item.name
              desc.text=item.description
              itemcount.text = ""+item.orderquantity
              var url= Constants.BASE_URL+item.logo
              if(url!=null){
                  Glide.with(context)
                      .load(url)
                      .centerCrop()
                      .into(logo)
              }
              var count=item.orderquantity

              itemplus.setOnClickListener{
                  count++
                  itemcount.text = ""+count
                  var cartItem : CartItem = CartItem(item.id,item.name,item.description,item.price,count,item.restraurantId,item.logo)
                  if(count==0) {
                      add.visibility = VISIBLE
                      addcount.visibility = GONE
                      linstener.delete(cartItem)
                  } else{
                      linstener.update(cartItem)
                  }
              }
              itemminus.setOnClickListener{
                  count--
                  itemcount.text = ""+count
                  var cartItem : CartItem = CartItem(item.id,item.name,item.description,item.price,count,item.restraurantId,item.logo)
                  if(count==0) {
                      add.visibility = VISIBLE
                      addcount.visibility = GONE
                      linstener.delete(cartItem)
                  } else{
                      linstener.update(cartItem)
                  }
              }

          }
    }
}