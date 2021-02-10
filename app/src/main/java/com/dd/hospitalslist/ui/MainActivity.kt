package com.dd.hospitalslist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dd.hospitalslist.R
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this) {}

    }


}