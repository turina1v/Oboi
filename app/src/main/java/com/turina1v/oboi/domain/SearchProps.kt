package com.turina1v.oboi.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchProps(
    var query: String? = null,
    var orientation: String? = null,
    var order: String? = null,
    var category: String? = null,
    val colors: MutableSet<String> = mutableSetOf(),
    var editorsChoice: String? = null,
    var page: Int? = null
) : Parcelable {

    fun getColorsQuery(): String? {
        return if (colors.isEmpty()) {
            null
        } else {
            buildString {
                colors.forEach { s ->
                    append(s)
                    append(",")
                }
                deleteCharAt(lastIndex)
            }
        }
    }

    fun copy(): SearchProps {
        return SearchProps(
            query,
            orientation,
            order,
            category,
            mutableSetOf<String>().apply { addAll(colors) },
            editorsChoice,
            page
        )
    }
}
