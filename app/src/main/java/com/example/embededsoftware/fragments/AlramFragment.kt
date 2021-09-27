package com.example.embededsoftware.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.embededsoftware.CustomDialog
import com.example.embededsoftware.R
import com.example.embededsoftware.databinding.FragmentAlramBinding
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_alram.*


class AlramFragment : Fragment() {
    private lateinit var databaseRef: DatabaseReference
    private lateinit var adapter: deliveryAdapter

    private lateinit var binding : FragmentAlramBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_alram, container, false)


        PackageCountView()
        initView()

        //탭
        binding.HomeTap.setOnClickListener{
            it.findNavController().navigate(R.id.action_alramFragment_to_homeFragment)
        }

        loadData()
        binding.recyclerView?.adapter = adapter


        return binding.root
    }

    //현재 보관중인 택배의 갯수를 보여준다.
    fun PackageCountView(){
        val database : FirebaseDatabase = FirebaseDatabase.getInstance()
        val countRef : DatabaseReference = database.getReference("count")
        countRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val count = dataSnapshot?.value.toString()
                if (count == "0") {
                    binding.countText.setText("없음.")
                } else {
                    binding.countText.setText("${count}개")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value.")
            }
        })
    }

    //택배 정보를 리사이클러뷰로 보여줌
    fun initView() {
        adapter = deliveryAdapter()

        recyclerView?.adapter = adapter
        adapter.listener = object : deliveryAdapter.OnDeliveryModelClickListener {
            override fun onItemClick(holder: deliveryAdapter.ViewHolder?, view: View?, position: Int) {
                val item = adapter.items[position]
                Log.d("item",item.objectId)
                val dialog = context?.let { CustomDialog(it) }
                // 택배 상태가 도난 의심일 경우에만 Dialog를 띄우도록 함
                if(item.receive == "도난 의심"){
                    dialog?.MyDig(item.objectId)
                }
            }
        }
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        layoutManager.setReverseLayout(true)
        layoutManager.setStackFromEnd(true)
        recyclerView?.layoutManager = layoutManager
    }

    // 파이어베이스에 저장된 택배 정보들을 가져와서 리사이클러뷰에 추가
    fun loadData() {
        val database : FirebaseDatabase = FirebaseDatabase.getInstance()
        val dataRef : DatabaseReference = database.getReference("data")
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d("MainActivity", dataSnapshot.toString())
                adapter.items.clear()
                for(dataModel in dataSnapshot.children) {
                    val objectId = dataModel.child("objectId")?.value.toString()
                    val arriveday = dataModel.child("arriveday")?.value.toString()
                    val arrivetime = dataModel.child("arrivetime")?.value.toString()
                    val receiveday = dataModel.child("receiveday")?.value.toString()
                    val receivetime = dataModel.child("receivetime")?.value.toString()
                    val receive = dataModel.child("receive")?.value.toString()
                    val checkday = dataModel.child("checkday")?.value.toString()
                    val checktime = dataModel.child("checktime")?.value.toString()

                    adapter.items.add(DeliveryModel(objectId, arriveday, arrivetime, receiveday, receivetime, receive,checkday,checktime))

                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("MainActivity", "loadPost:onCancelled", databaseError.toException())
            }
        })
    }
}