package com.dd.hospitalslist.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Region.TABLE_NAME)
data class Region(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = ID) val id: Int,
        @ColumnInfo(name = REGION_NAME) val name: String
) {

    //////////////////////////TABLE///////////////////////////

    companion object {
        const val TABLE_NAME = "regions"
        const val ID = "id"
        const val REGION_NAME = "name"
    }

    //////////////////////////////////////////////////////////

}