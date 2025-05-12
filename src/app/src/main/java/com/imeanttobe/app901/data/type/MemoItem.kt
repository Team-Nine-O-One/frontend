package com.imeanttobe.app901.data.type

abstract class MemoItem(
    private var content: String,
    private var checked: Boolean
) {
    fun getContent(): String = content
    fun setContent(newValue: String) {
        content = newValue
    }
    fun isChecked(): Boolean = checked
    fun setChecked(newValue: Boolean) {
        checked = newValue
    }
}
