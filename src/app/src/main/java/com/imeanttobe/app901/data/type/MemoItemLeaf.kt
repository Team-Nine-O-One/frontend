package com.imeanttobe.app901.data.type

data class MemoItemLeaf(
    private val id: Long,
    private var content: String,
) : MemoItem(id, content) {
    constructor(content: String) : this(-1, content)
}
