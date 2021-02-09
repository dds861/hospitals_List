package com.dd.hospitalslist.data

import com.dd.hospitalslist.utils.Constants.STRING_EMPTY

data class HospitalModel(
    val regionName: String = STRING_EMPTY,
    val categoryName: String = STRING_EMPTY,
    val categoryState: CategoryState = CategoryState.ALL,
    val regionState: RegionState = RegionState.ALL

)

enum class RegionState {
    ALL,
    SPECIFIC_ITEM,
}

enum class CategoryState {
    ALL,
    SPECIFIC_ITEM

}

