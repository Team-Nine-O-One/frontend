package com.imeanttobe.app901.data.type

abstract class MemoItem(
    private val id: Long = -1,
    private var content: String,
) {
    constructor(content: String) : this(-1, content)

    companion object {
        fun convertToString(memoItems: List<MemoItem>): String {
            var result = ""

            for (memoItem in memoItems) {
                if (memoItem != memoItems.last()) {
                    if (memoItem is MemoItemLeaf) {
                        result += "${memoItem.getContent()} | "
                    } else {
                        for (item in (memoItem as MemoItemGroup).getItems()) {
                            result += "${item.getContent()} | "
                        }
                    }
                } else {
                    if (memoItem is MemoItemLeaf) {
                        result += memoItem.getContent()
                    } else {
                        for (item in (memoItem as MemoItemGroup).getItems()) {
                            if (item != memoItem.getItems().last()) {
                                result += "${item.getContent()} | "
                            } else {
                                result += item.getContent()
                            }
                        }
                    }
                }
            }

            return result
        }
    }

    fun getContent(): String = content

    fun setContent(newValue: String) {
        content = newValue
    }
}
