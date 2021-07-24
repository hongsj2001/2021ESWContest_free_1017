package com.example.embededsoftware.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.embededsoftware.Model
import com.example.embededsoftware.R
import com.example.embededsoftware.databinding.FragmentAlramBinding
import com.example.embededsoftware.databinding.FragmentDeliveryBinding
import com.example.embededsoftware.databinding.FragmentHomeBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

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


    val data = ArrayList<String>()

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
        getData()
        return binding.root
    }
    fun getData() {

        val database = Firebase.database
        val myRef = database.getReference("pakage")
        val postListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                Log.d("MainActivity", dataSnapshot.toString())

                for(dataModel in dataSnapshot.children) {
                    data.add(dataModel.getValue().toString()!!)
                    binding.textPakage.setText("${data[0]}")
                }


            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("MainActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)
    }



}