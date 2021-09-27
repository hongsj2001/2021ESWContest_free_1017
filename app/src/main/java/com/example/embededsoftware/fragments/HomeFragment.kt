package com.example.embededsoftware.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.embededsoftware.R
import com.example.embededsoftware.databinding.FragmentHomeBinding
import com.google.firebase.database.*

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        PackageCountView()
        theftChcekView()

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        //알람 탭
        binding.AlramTap.setOnClickListener{

            it.findNavController().navigate(R.id.action_homeFragment_to_alramFragment)
        }


        return binding.root
    }

    //파이어베이스의 count를 수신 대기하여 현재 보관중인 택배의 갯수를 보여준다.(count가 0보다 작으면 "현재 보관중인 택배가 없습니다" 출력,
    //count가 0보다 크면 count의 값을 출력)
    fun PackageCountView(){
        val database : FirebaseDatabase = FirebaseDatabase.getInstance()
        val countRef : DatabaseReference = database.getReference("count")
        countRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val count = dataSnapshot?.value.toString()
                if (count == "0") {
                    binding.tvCount.setText("현재 보관중인 택배가 없습니다.")
                } else {
                    binding.tvCount.setText("${count}개")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value.")
            }
        })
    }
    // 파이어베이스의 data들을 수신하여 만일 도난 의심 상태인 택배의 수를 파악하여 도난이 의심되는 택배의 수를 보여준다.
    fun theftChcekView(){
        val database : FirebaseDatabase = FirebaseDatabase.getInstance()
        val dataRef : DatabaseReference = database.getReference("data")
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var count = 0
                for(dataModel in dataSnapshot.children) {
                    val receive = dataModel.child("receive")?.value.toString()
                    if(receive == "도난 의심"){
                        count = count+1
                    }
                }
                if(count > 0) {
                    binding.theftTextView.setText("도난이 의심되는 택배가 ${count}개 있습니다.")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("MainActivity", "loadPost:onCancelled", databaseError.toException())
            }
        })
    }
}