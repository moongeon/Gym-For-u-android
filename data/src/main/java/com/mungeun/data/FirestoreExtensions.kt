package com.mungeun.data

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

fun FirebaseFirestore.document() : DocumentReference {
    return collection("gymforyou").document("2022")
}