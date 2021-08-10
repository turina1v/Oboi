package com.turina1v.oboi.domain

data class SearchProps(
    val query: String? = null,
    val orientation: String? = null,
    val order: String? = null,
    val category: String? = null,
    val colors: String? = null,
    val editorsChoice: String? = null,
    val page: Int? = null
)
