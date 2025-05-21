package com.imeanttobe.app901

import com.imeanttobe.app901.data.type.MemoItem
import com.imeanttobe.app901.data.type.MemoItemGroup
import com.imeanttobe.app901.data.type.MemoItemLeaf
import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MemoItemTest {
    @Test
    fun is_convert_correct() {
        val memoItems =
            listOf(
                MemoItemLeaf("Item 1", false),
                MemoItemGroup(
                    "",
                    false,
                    mutableListOf(
                        MemoItemLeaf("Item 2", true),
                        MemoItemLeaf("Item 3", false),
                    ),
                ),
                MemoItemLeaf("Item 4", true),
            )
        val expected = "Item 1 | Item 2 | Item 3 | Item 4"
        val actual = MemoItem.convertToString(memoItems)
        assertEquals(expected, actual)
    }
}
