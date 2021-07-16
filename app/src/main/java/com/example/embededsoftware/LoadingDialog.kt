package com.example.embededsoftware

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.example.embededsoftware.fragments.HomeFragment

class LoadingDialog(context: Context) : Dialog(context) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.loading_dialog)

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}