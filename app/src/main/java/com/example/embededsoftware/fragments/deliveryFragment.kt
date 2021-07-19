package com.example.embededsoftware.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.embededsoftware.R
import com.example.embededsoftware.databinding.FragmentDeliveryBinding
import kotlinx.android.synthetic.main.fragment_delivery.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [deliveryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class deliveryFragment : Fragment() {


    private lateinit var binding : FragmentDeliveryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_delivery, container, false)

        binding.HomeTap.setOnClickListener{

            it.findNavController().navigate(R.id.action_deliveryFragment_to_homeFragment)
        }

        binding.AlramTap.setOnClickListener{

            it.findNavController().navigate(R.id.action_deliveryFragment_to_alramFragment)
        }

        val layoutManager = context?.let {it1 -> LinearLayoutManager(it1, LinearLayoutManager.VERTICAL,false)}
        //LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.recyclerView?.layoutManager = layoutManager

        val adapter = deliveryAdapter()


        adapter.items.add(delivery("7월 12일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 13일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 14일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 15일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 16일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 17일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 18일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 19일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 20일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 21일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 22일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 23일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 24일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 25일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 26일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 27일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 28일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 29일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 30일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 31일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 32일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 33일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 34일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 35일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 36일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 37일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 38일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 39일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 40일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 41일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 42일","15:15:55","수령 완료"))
        adapter.items.add(delivery("7월 43일","15:15:55","수령 완료"))

        layoutManager?.reverseLayout = true
        layoutManager?.stackFromEnd = true //리스트 역순 배열




        binding.recyclerView?.adapter = adapter

        // Inflate the layout for this fragment
        return binding.root
    }



}