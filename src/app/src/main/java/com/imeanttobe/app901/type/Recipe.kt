package com.imeanttobe.app901.type

data class Recipe(
    val id: Int,
    val name: String,
    val summary: String,
    val imageUrl: String
) {
    companion object {
        fun getDefaultInstance(): Recipe {
            return Recipe(
                id = -1,
                name = "알리오 올리오",
                summary = "마늘과 올리브 유를 활용한 이탈리아의 가장 기본적인 파스타입니다.",
                imageUrl = ""
            )
        }
    }
}
