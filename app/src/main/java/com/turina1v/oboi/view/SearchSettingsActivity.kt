package com.turina1v.oboi.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.turina1v.oboi.R
import com.turina1v.oboi.data.network.JsonUtils
import com.turina1v.oboi.domain.Category
import com.turina1v.oboi.domain.SearchProps
import kotlinx.android.synthetic.main.activity_search_settings.*
import kotlinx.android.synthetic.main.layout_color_switch.*
import java.util.*

class SearchSettingsActivity : AppCompatActivity() {

    private var initialSearchProps: SearchProps? = null
    private lateinit var editedSearchProps: SearchProps

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_settings)
        initSearchProps()
        setupUi()
    }

    private fun initSearchProps() {
        val searchProps = intent.getParcelableExtra<SearchProps>(EXTRA_SEARCH_PROPS)
        initialSearchProps = searchProps
        editedSearchProps = searchProps?.copy() ?: SearchProps()
    }

    private fun setupUi() {
        setUpOrientation()
        setUpColors()
        setUpCategories()
    }

    private fun setUpOrientation() {
        when (initialSearchProps?.orientation) {
            ORIENTATION_VERTICAL -> orientationVerticalRadiobutton.isChecked = true
            ORIENTATION_HORIZONTAL -> orientationHorizontalRadiobutton.isChecked = true
            else -> orientationAllRadiobutton.isChecked = true
        }
        orientationRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.orientationVerticalRadiobutton -> editedSearchProps.orientation = ORIENTATION_VERTICAL
                R.id.orientationHorizontalRadiobutton -> editedSearchProps.orientation = ORIENTATION_HORIZONTAL
                R.id.orientationAllRadiobutton -> editedSearchProps.orientation = ORIENTATION_ALL
            }
        }
    }

    private fun setUpColors() {
        initialSearchProps?.colors?.forEach { color ->
            when (color) {
                COLOR_RED -> redSwitchButton.isChecked = true
                COLOR_ORANGE -> orangeSwitchButton.isChecked = true
                COLOR_YELLOW -> yellowSwitchButton.isChecked = true
                COLOR_GREEN -> greenSwitchButton.isChecked = true
                COLOR_LIGHT_BLUE -> blueLightSwitchButton.isChecked = true
                COLOR_DARK_BLUE -> blueDarkSwitchButton.isChecked = true
                COLOR_PURPLE -> purpleSwitchButton.isChecked = true
                COLOR_PINK -> pinkSwitchButton.isChecked = true
                COLOR_WHITE -> whiteSwitchButton.isChecked = true
                COLOR_GRAY -> graySwitchButton.isChecked = true
                COLOR_BLACK -> blackSwitchButton.isChecked = true
                COLOR_BROWN -> brownSwitchButton.isChecked = true
                COLOR_TRANSPARENT -> transparentCheckbox.isChecked = true
                COLOR_GRAYSCALE -> {
                    grayscaleCheckbox.isChecked = true
                    colorBlurLayout.isVisible = true
                }
            }
        }
        redSwitchButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) editedSearchProps.colors.add(COLOR_RED)
            else editedSearchProps.colors.remove(COLOR_RED)
            println(editedSearchProps.colors)
        }
        orangeSwitchButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) editedSearchProps.colors.add(COLOR_ORANGE)
            else editedSearchProps.colors.remove(COLOR_ORANGE)
        }
        yellowSwitchButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) editedSearchProps.colors.add(COLOR_YELLOW)
            else editedSearchProps.colors.remove(COLOR_YELLOW)
        }
        greenSwitchButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) editedSearchProps.colors.add(COLOR_GREEN)
            else editedSearchProps.colors.remove(COLOR_GREEN)
        }
        blueLightSwitchButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) editedSearchProps.colors.add(COLOR_LIGHT_BLUE)
            else editedSearchProps.colors.remove(COLOR_LIGHT_BLUE)
        }
        blueDarkSwitchButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) editedSearchProps.colors.add(COLOR_DARK_BLUE)
            else editedSearchProps.colors.remove(COLOR_DARK_BLUE)
        }
        purpleSwitchButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) editedSearchProps.colors.add(COLOR_PURPLE)
            else editedSearchProps.colors.remove(COLOR_PURPLE)
        }
        pinkSwitchButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) editedSearchProps.colors.add(COLOR_PINK)
            else editedSearchProps.colors.remove(COLOR_PINK)
        }
        whiteSwitchButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) editedSearchProps.colors.add(COLOR_WHITE)
            else editedSearchProps.colors.remove(COLOR_WHITE)
        }
        graySwitchButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) editedSearchProps.colors.add(COLOR_GRAY)
            else editedSearchProps.colors.remove(COLOR_GRAY)
        }
        blackSwitchButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) editedSearchProps.colors.add(COLOR_BLACK)
            else editedSearchProps.colors.remove(COLOR_BLACK)
        }
        brownSwitchButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) editedSearchProps.colors.add(COLOR_BROWN)
            else editedSearchProps.colors.remove(COLOR_BROWN)
        }
        transparentCheckbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) editedSearchProps.colors.add(COLOR_TRANSPARENT)
            else editedSearchProps.colors.remove(COLOR_TRANSPARENT)
        }
        grayscaleCheckbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                editedSearchProps.colors.add(COLOR_GRAYSCALE)
                colorBlurLayout.isVisible = true
            } else {
                editedSearchProps.colors.remove(COLOR_GRAYSCALE)
                colorBlurLayout.isVisible = false
            }
        }
    }

    private fun setUpCategories() {
        val gson = Gson()
        val json =
            if (Locale.getDefault().language == "ru") JsonUtils.loadJsonFromAsset(this, "categories_ru.json")
            else JsonUtils.loadJsonFromAsset(this, "categories.json")
        val listType = object : TypeToken<List<Category?>?>() {}.type
        val categories = gson.fromJson<List<Category>>(json, listType)
        val adapter = CategoryAdapter(this, R.layout.item_spinner_category, categories)
        categorySpinner.adapter = adapter
        val currentCategory = initialSearchProps?.category
        currentCategory?.let { categoryQuery ->
            val index = categories.indexOf(categories.firstOrNull { it.query == categoryQuery })
            categorySpinner.setSelection(index)
        }
        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val category = adapter.getItem(position)
                category?.let {
                    if (category.query == "all") editedSearchProps.category = null
                    else editedSearchProps.category = category.query
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) { }
        }
    }

    private fun setSearchPropsResult() {
        if (initialSearchProps != editedSearchProps) {
            setResult(RESULT_OK, Intent().apply { putExtra(EXTRA_SEARCH_PROPS, editedSearchProps) })
        }
    }

    override fun onBackPressed() {
        setSearchPropsResult()
        finish()
    }

    companion object {
        const val EXTRA_SEARCH_PROPS = "search_props"
        fun getStartIntent(context: Context, searchProps: SearchProps): Intent {
            return Intent(context, SearchSettingsActivity::class.java).putExtra(
                EXTRA_SEARCH_PROPS,
                searchProps
            )
        }

        private const val ORIENTATION_VERTICAL = "vertical"
        private const val ORIENTATION_HORIZONTAL = "horizontal"
        private const val ORIENTATION_ALL = "all"

        private const val ORDER_POPULAR = "popular"

        private const val COLOR_RED = "red"
        private const val COLOR_ORANGE = "orange"
        private const val COLOR_YELLOW = "yellow"
        private const val COLOR_GREEN = "green"
        private const val COLOR_LIGHT_BLUE = "turquoise"
        private const val COLOR_DARK_BLUE = "blue"
        private const val COLOR_PURPLE = "lilac"
        private const val COLOR_PINK = "pink"
        private const val COLOR_WHITE = "white"
        private const val COLOR_GRAY = "gray"
        private const val COLOR_BLACK = "black"
        private const val COLOR_BROWN = "brown"
        private const val COLOR_TRANSPARENT = "transparent"
        private const val COLOR_GRAYSCALE = "grayscale"
    }
}