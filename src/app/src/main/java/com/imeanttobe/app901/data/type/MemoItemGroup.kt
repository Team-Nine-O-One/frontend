package com.imeanttobe.app901.data.type

data class MemoItemGroup(
    private val id: Long,
    private var content: String,
    private val items: MutableList<MemoItemLeaf> = mutableListOf(),
) : MemoItem(id, content) {
    constructor(content: String) : this(-1, content)
    constructor(
        content: String,
        items: MutableList<MemoItemLeaf>,
    ) : this(-1, content, items)

    fun getItems(): List<MemoItemLeaf> = items.toList()

    fun addItem(item: MemoItemLeaf) {
        items.add(item)
    }

    fun removeItem(item: MemoItemLeaf) {
        items.remove(item)
    }
}
