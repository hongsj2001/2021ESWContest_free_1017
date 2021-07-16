package com.example.embededsoftware.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.embededsoftware.R
import com.example.embededsoftware.databinding.FragmentAlramBinding
import com.example.embededsoftware.databinding.FragmentDeliveryBinding
import com.example.embededsoftware.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [deliveryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AlramFragment : Fragment() {


    private lateinit var binding : FragmentAlramBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_alram, container, false)

        binding.HomeTap.setOnClickListener{

            it.findNavController().navigate(R.id.action_alramFragment_to_homeFragment)
        }

        binding.pakageTap.setOnClickListener{

            it.findNavController().navigate(R.id.action_alramFragment_to_deliveryFragment)
        }

        // Inflate the layout for this fragment
        return binding.root
    }



}