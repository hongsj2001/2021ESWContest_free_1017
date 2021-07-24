package com.example.embededsoftware.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.embededsoftware.R
import com.example.embededsoftware.databinding.FragmentDeliveryBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
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

    var data = ArrayList<String>()

    val adapter = deliveryAdapter()

    val list = ArrayList<String>()

    var positon = false

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

        getData()
        adapter.notifyItemInserted(adapter.items.size - 1)

        val layoutManager = context?.let { it1 -> LinearLayoutManager(
            it1,
            LinearLayoutManager.VERTICAL,
            false
        )}
        //LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.recyclerView?.layoutManager = layoutManager






//
//        layoutManager?.reverseLayout = true
//        layoutManager?.stackFromEnd = true //리스트 역순 배열


        binding.recyclerView?.adapter = adapter



        // Inflate the layout for this fragment
        return binding.root
    }
    fun getData() {

        val database = Firebase.database
        val myRef = database.getReference("pakage")
        val postListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                Log.d("MainActivity", dataSnapshot.toString())
                //list.add(dataSnapshot.getValue(Model::class.java)!!)
                for(dataModel in dataSnapshot.children) {
                    list.add(dataModel.getValue().toString()!!)
                    if (list[0] in ("택배없음")) {                 //도무지 아이디어가 안떠오르네.. data 값이 바뀌닌까 리사이클러뷰 리스트도 바뀐다.. 한번 넣으면 안사라지게 하고싶다.. if문이 문제
                        adapter.items.add( delivery("7월 12일", "15:15:55", "텅텅"))
                        //adapter.notifyItemInserted(adapter.items.size + 1)
                        positon = true

                    }
                    if (list[0] in ("택배가 도착했습니다.")) {

                        adapter.items.add(delivery("7월 12일", "15:15:55", "본인수령"))
                        //adapter.notifyItemInserted(adapter.items.size + 1)

                    }
                }



                adapter.notifyDataSetChanged()
//                for(dataModel in dataSnapshot.children) {
//                    data.add(dataModel.getValue().toString()!!)
//                }


//                when (data[0]){
//
//                    "택배가 도착했습니다" -> adapter.items.add(delivery("7월 12일", "15:15:55", "본인수령"))
//                    else -> adapter.items.add( delivery("7월 12일", "15:15:55", "텅텅"))
//                }
                //adapter.notifyItemInserted(adapter.items.size + 1)





            }


            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("MainActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)
    }

}