package com.imeanttobe.app901.data.type

data class MemoItemLeaf(
    private var content: String,
    private var checked: Boolean
) : MemoItem(content, checked)