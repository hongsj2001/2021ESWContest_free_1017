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



    val list = mutableListOf<Model>()
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


           if (list[0].title.contains("2")) { //임의로 넣은거 이것도!!
               loadingDialog?.dismiss()
            }


        }
        binding.btnArduino.setOnClickListener{
            val database = Firebase.database
            val myRef = database.getReference("message")
            myRef.setValue(Model("2"))
            getData()
        }

        // Inflate the layout for this fragment
        return binding.root
    }
    fun getData() {

        val database = Firebase.database
        val myRef = database.getReference("message")
        val postListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                Log.d("MainActivity", dataSnapshot.toString())
                list.add(dataSnapshot.getValue(Model::class.java)!!)
                binding.tvCount.setText("${list[0].title}개")

                if(list.isEmpty()){
                    binding.tvCount.setText("0개")
                }
                // ...
//                for (dataModel in dataSnapshot.children){
//                    val item = dataModel.getValue(Model::class.java)!!
//                    findViewById<TextView>(R.id.tv_count)!!.text = dataModel.children.toString()
//                }


//val text = findViewById<TextView>(R.id.tv_count)
                //text!!.text = dataModel.getValue(Model::class.java).toString()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("MainActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)
    }

}