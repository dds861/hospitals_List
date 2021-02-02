package com.dd.hospitalslist.data

import androidx.lifecycle.LiveData
import androidx.paging.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.dd.hospitalslist.data.entities.Hospital
import com.dd.hospitalslist.data.entities.Region
import kotlinx.coroutines.flow.Flow

class HospitalRepository(private val hospitalDao: HospitalDao) {


    val hospitals: Flow<PagingData<Hospital>> = Pager(
        config = PagingConfig(pageSize = 5, enablePlaceholders = true, prefetchDistance = 7),
        pagingSourceFactory = { hospitalDao.getHospitals(getFilteredQuery()) }
    ).flow


    fun getRegions(): LiveData<List<Region>> {
        return hospitalDao.getRegions()
    }


    private fun getFilteredQuery(): SimpleSQLiteQuery {
        val now = System.currentTimeMillis()
        val simpleQuery = StringBuilder()
            .append("SELECT * FROM hospitals")

//        if (filter == LetterState.FUTURE) {
//            simpleQuery.append("WHERE expires >= $now OR expires <= $now AND opened IS 0")
//        }
//        if (filter == LetterState.OPENED) {
//            simpleQuery.append("WHERE opened IS NOT 0")
//        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}