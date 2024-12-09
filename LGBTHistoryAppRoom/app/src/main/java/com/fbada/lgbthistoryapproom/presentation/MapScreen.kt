package com.fbada.lgbthistoryapproom.presentation

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.fbada.lgbthistoryapproom.MapsActivity


@Composable
fun MapScreen() {
    Box(modifier = Modifier.fillMaxSize())
    {
        val context = LocalContext.current
        context.startActivity(Intent(context, MapsActivity::class.java))
    }
}




