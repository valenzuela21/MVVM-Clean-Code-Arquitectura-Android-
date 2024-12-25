package com.cursosant.recomendedarch.common.entities

import android.net.Uri

data class FirebaseUser(val uid: String = "",
                        val photoUrl: Uri? = null,
                        val displayName: String = "",
                        val email: String = "",
                        val phone: String = "",
                        val username: String = "",
                        val pin: String = "")
