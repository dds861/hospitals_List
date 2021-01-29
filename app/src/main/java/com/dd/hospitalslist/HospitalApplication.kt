package com.dd.hospitalslist

import android.app.Application
import com.dd.hospitalslist.data.HospitalDatabase
import com.dd.hospitalslist.data.HospitalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class HospitalApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { HospitalDatabase.getInstance(this, applicationScope) }
    val repository by lazy { HospitalRepository(database.hospitalsDao()) }
}