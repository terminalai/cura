package com.mindfulhacks.hermoor.model.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseUtil {
    private var FIRESTORE: FirebaseFirestore? = null

    // Connect to the Cloud Firestore
    private val firestore: FirebaseFirestore
        get() {
            if (FIRESTORE == null) FIRESTORE = Firebase.firestore
            return FIRESTORE!!
        }

    fun msgsCollection() = firestore.collection("messages")
}