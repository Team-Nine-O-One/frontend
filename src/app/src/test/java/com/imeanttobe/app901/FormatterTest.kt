package com.imeanttobe.app901

import com.imeanttobe.app901.util.Formatter
import org.junit.Test

class FormatterTest {
    @Test
    fun testFormatPrice() {
        val target = Formatter.formatPrice(123456789)
        val result = "123,456,789"

        assert(target == result)
    }
}
