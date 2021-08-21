package com.example.justorder.ui.homefragments.myorder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.justorder.R

class MyOrderFragment : Fragment() {

    private lateinit var myOrderViewModel: MyOrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myOrderViewModel =
            ViewModelProvider(this).get(MyOrderViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_myorder, container, false)
        val textView: TextView = root.findViewById(R.id.text_myorder)
        myOrderViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}