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
) {

    @Exclude
    fun toMap(): HashMap<String, Any> {
        val result: HashMap<String, Any> = HashMap()
        result["objectId"] = objectId
        result["arriveday"] = arriveday
        result["arrivetime"] = arrivetime
        result["receivetime"] = receivetime
        result["receiveday"] = receiveday
        result["receive"] = receive
        result["checkday"] = checkday
        result["checktime"] = checktime
        return result
    }
}