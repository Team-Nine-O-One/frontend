package com.imeanttobe.app901.util

import com.imeanttobe.app901.data.model.Product
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Converter {
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    fun getStringFromLocalDateTime(localDateTime: LocalDateTime): String =
        localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

    fun getLocalDateTimeFromString(string: String): LocalDateTime = LocalDateTime.parse(string, dateTimeFormatter)

    fun getShareTextFromProducts(products: List<Product>): String =
        products.joinToString(
            separator = "\n",
            prefix =
                """
                # 장 볼 목록
                아래 적혀 있는 물건들을 사 오면 돼요!
                
                """.trimIndent(),
            transform = { "- ${it.name}" },
        )
}
