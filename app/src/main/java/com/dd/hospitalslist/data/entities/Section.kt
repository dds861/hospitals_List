package com.dd.hospitalslist.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = Section.TABLE_NAME)
data class Section(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = ID) val id: Int,
        @ColumnInfo(name = NAME) val name: String
) {

    //////////////////////////TABLE///////////////////////////

    companion object {
        const val TABLE_NAME = "category"
        const val ID = "id"
        const val NAME = "name"
    }

    //////////////////////////////////////////////////////////

}