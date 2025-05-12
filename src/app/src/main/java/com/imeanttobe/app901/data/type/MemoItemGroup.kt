package com.imeanttobe.app901.data.type

data class MemoItemGroup(
    private var content: String,
    private var checked: Boolean,
    private val items: MutableList<MemoItemLeaf> = mutableListOf()
) : MemoItem(content, checked) {
    fun getItems(): List<MemoItemLeaf> = items.toList()
    fun addItem(item: MemoItemLeaf) {
        items.add(item)
    }
    fun removeItem(item: MemoItemLeaf) {
        items.remove(item)
    }
}