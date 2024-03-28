package com.firentistfw.kindlehighlights.extensions

fun <T> MutableList<T>.getUniqueRandomElements(count: Int): List<T> {
    val selectedValues = mutableListOf<T>()
    while (selectedValues.size < count && isNotEmpty()) {
        val selectedValue = random()
        selectedValues.add(selectedValue)
        remove(selectedValue)
    }

    return selectedValues
}
