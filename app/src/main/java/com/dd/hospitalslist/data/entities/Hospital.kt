package com.dd.hospitalslist.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Hospital.TABLE_NAME)
data class Hospital(

        @PrimaryKey (autoGenerate = true)
        @ColumnInfo(name = ID) val id: Int,
        @ColumnInfo(name = REGION_ID) val region_id: Int,
        @ColumnInfo(name = CATEGORY_ID) val category_id: Int,
        @ColumnInfo(name = REGION) val region: String,
        @ColumnInfo(name = CATEGORY) val category: String,
        @ColumnInfo(name = BRANCH) val branch: String,
        @ColumnInfo(name = PHONE) val phone: String?,
        @ColumnInfo(name = ADDRESS) val address: String?
) {

    //////////////////////////TABLE///////////////////////////

    companion object {
        const val TABLE_NAME = "hospitals"
        const val ID = "id"
        const val REGION_ID = "region_id"
        const val CATEGORY_ID = "category_id"
        const val REGION = "region"
        const val CATEGORY = "category"
        const val BRANCH = "branch"
        const val PHONE = "phone"
        const val ADDRESS = "address"
    }
    //////////////////////////////////////////////////////////

}