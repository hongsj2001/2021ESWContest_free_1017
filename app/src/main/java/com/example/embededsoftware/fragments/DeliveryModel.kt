package com.example.embededsoftware.fragments

import com.google.firebase.database.Exclude

data class DeliveryModel(
    var objectId:String ="",
    var arriveday:String="",
    var arrivetime:String="",
    var receiveday:String="",
    var receivetime:String="",
    var receive:String="",
    var checkday:String="",
    var checktime:String=""
)