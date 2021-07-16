package com.example.embededsoftware

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.embededsoftware.auth.loginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    val list = mutableListOf<Model>()


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        findViewById<Button>(R.id.btn_logout).setOnClickListener{

            auth.signOut()

            val intent = Intent(this, loginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

        }


            val database = Firebase.database
            val myRef = database.getReference("message")
            myRef.setValue(Model("1"))  //임의로 넣은 것!!!!!!!!!


    }

//    fun getData() {
//
//        val database = Firebase.database
//        val myRef = database.getReference("message")
//        val postListener = object : ValueEventListener {
//
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                // Get Post object and use the values to update the UI
//                Log.d("MainActivity", dataSnapshot.toString())
//                list.add(dataSnapshot.getValue(Model::class.java)!!)
//                val text = findViewById<TextView>(R.id.tv_count)!!
//                text.setText("${list[0].title}개")
//
//                if(list.isEmpty()){
//                    text.setText("0개")
//                }
//                // ...
////                for (dataModel in dataSnapshot.children){
////                    val item = dataModel.getValue(Model::class.java)!!
////                    findViewById<TextView>(R.id.tv_count)!!.text = dataModel.children.toString()
////                }
//
//
////val text = findViewById<TextView>(R.id.tv_count)
//                //text!!.text = dataModel.getValue(Model::class.java).toString()
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                // Getting Post failed, log a message
//                Log.w("MainActivity", "loadPost:onCancelled", databaseError.toException())
//            }
//        }
//        myRef.addValueEventListener(postListener)
//    }
}