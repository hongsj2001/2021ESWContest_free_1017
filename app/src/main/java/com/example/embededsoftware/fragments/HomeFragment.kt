package com.example.embededsoftware.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.embededsoftware.CustomDialogFragment
import com.example.embededsoftware.LoadingDialog
import com.example.embededsoftware.Model
import com.example.embededsoftware.R
import com.example.embededsoftware.databinding.FragmentHomeBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.*
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
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {



    val data = ArrayList<String>()

    private lateinit var binding : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        getData()

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.pakageTap.setOnClickListener{

            it.findNavController().navigate(R.id.action_homeFragment_to_deliveryFragment)
        }

        binding.AlramTap.setOnClickListener{

            it.findNavController().navigate(R.id.action_homeFragment_to_alramFragment)
        }
        binding.btnPakageGet.setOnClickListener {


            val loadingDialog = context?.let { it1 -> LoadingDialog(it1) }
            loadingDialog?.show()


            if (data[0].contains("2")) { //버튼을 누른상태에서 집에 있는 내가 택배를 들었을 때!!!!! 아두이노가 파이어베이스로 값을 보낸다. 이때 아래와 같이 작동
                loadingDialog?.dismiss()
                getData()
                val database = Firebase.database
                val myRef2 = database.getReference("pakage")
                myRef2.push().setValue("택배 도난이 의심됩니다.")

                val myRef3 = database.getReference("Time")
                myRef3.setValue(getTime())

            }
            //val adapter = deliveryAdapter()



        }
        binding.btnArduino.setOnClickListener{
            val database = Firebase.database
            val myRef = database.getReference("arduino")
            myRef.setValue("2")


            getData()
        }

        return binding.root
    }

    fun getTime() : String{
        val currentDateTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA).format(currentDateTime)

        return dateFormat
    }

    fun getData() {

        val database = Firebase.database
        val myRef = database.getReference("arduino")
        val postListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                Log.d("MainActivity", dataSnapshot.toString())
                data.add(dataSnapshot.getValue().toString()!!)
                binding.tvCount.setText("${data[0]}개")
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("MainActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)
    }
}