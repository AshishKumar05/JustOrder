package com.example.justorder.ui.homefragments.cart

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.justorder.R
import com.example.justorder.roomdb.CartItem
import com.example.justorder.roomdb.RoomViewModel
import com.example.justorder.ui.checkout.PaymentScreen

class CartFragment : Fragment() {

    private lateinit var cartViewModel: CartViewModel
    //cart adapter
    private  lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var cartAdapter: CartAdapter
    val TAG="CARTFRAGMENT"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cartViewModel =
            ViewModelProvider(this).get(CartViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_cart, container, false)
        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
       val recyclerView:RecyclerView = root.findViewById(R.id.cartItemView)
        val proceed :Button = root.findViewById(R.id.btn_proceedtocheckout)
        proceed.setOnClickListener {
            var intent = Intent(activity,PaymentScreen::class.java)
            startActivity(intent)
        }
        recyclerView.layoutManager=layoutManager
        cartAdapter = CartAdapter(ArrayList<CartItem>(),object :CartItemClickLestener{
            override fun update(cartItem: CartItem) {
                TODO("Not yet implemented")
            }

            override fun delete(cartItem: CartItem) {
                TODO("Not yet implemented")
            }
        })
        recyclerView.adapter= cartAdapter
        setCartData()
        return root
    }

    private fun setCartData() {
       var application: Application = activity?.application!!
        var roomViewModel = RoomViewModel(application)
       var list=roomViewModel.getAll()
        Log.e(TAG, "setCartData: "+list )
        cartAdapter.setCartlist(roomViewModel.getAll() as ArrayList<CartItem>)
        cartAdapter.notifyDataSetChanged()
    }
}