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
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

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
    val Time = ArrayList<String>()

    private lateinit var binding : FragmentAlramBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_alram, container, false)

        binding.checkBtn.setVisibility(View.INVISIBLE)
        binding.othercheckBtn.setVisibility(View.INVISIBLE)
        binding.thiefBtn.setVisibility(View.INVISIBLE)
        binding.theifCheckTv.setVisibility(View.INVISIBLE)
        binding.theifTv.setVisibility(View.INVISIBLE)
        binding.thiefCheckTimeTv.setVisibility(View.INVISIBLE)
        binding.thiefTimeTv.setVisibility(View.INVISIBLE)

        binding.HomeTap.setOnClickListener{

            it.findNavController().navigate(R.id.action_alramFragment_to_homeFragment)
        }

        binding.pakageTap.setOnClickListener{

            it.findNavController().navigate(R.id.action_alramFragment_to_deliveryFragment)
        }
        binding.checkBtn.setOnClickListener {
            val database = Firebase.database
            val myRef2 = database.getReference("pakage")
            myRef2.push().setValue("버튼X 택배가 수령이 확인되었습니다.")
        }
        binding.othercheckBtn.setOnClickListener {
            val database = Firebase.database
            val myRef2 = database.getReference("pakage")
            myRef2.push().setValue("버튼X 택배가 수령이 확인되었습니다.")
        }
        binding.thiefBtn.setOnClickListener {
            TimeData()
            val database = Firebase.database
            val myRef2 = database.getReference("pakage")
            myRef2.push().setValue("택배 도난이 의심됩니다.")
            binding.thiefCheckTimeTv.text = getTime()
            binding.theifCheckTv.setVisibility(View.VISIBLE)
            binding.theifTv.setVisibility(View.VISIBLE)
            binding.thiefCheckTimeTv.setVisibility(View.VISIBLE)
        }

        getData()
        return binding.root
    }

    fun getTime() : String{
        val currentDateTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA).format(currentDateTime)

        return dateFormat
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

                    if(data[0] in "버튼X 택배가 수령이 확인되었습니다."){
                        binding.checkBtn.setVisibility(View.VISIBLE)
                    }
                    if(data[0] in "택배 도난이 의심됩니다."){
                        binding.othercheckBtn.setVisibility(View.VISIBLE)
                        binding.thiefBtn.setVisibility(View.VISIBLE)
                    }
                }


            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("MainActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)
    }
    fun TimeData() {

        val database = Firebase.database
        val myRef = database.getReference("Time")
        val postListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                    Time.add(dataSnapshot.getValue().toString()!!)



                binding.thiefTimeTv.text = Time[0]
                binding.thiefTimeTv.setVisibility(View.VISIBLE)
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("MainActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)
    }



}