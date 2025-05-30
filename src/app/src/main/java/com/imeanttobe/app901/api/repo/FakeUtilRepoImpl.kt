package com.imeanttobe.app901.api.repo

import javax.inject.Inject

class FakeUtilRepoImpl
    @Inject
    constructor() : UtilRepo {
        override suspend fun importStringsFromUrl(): Result<Pair<String, List<String>>> {
            val title = "함박스테이크를 완벽하게 요리 하는 법 - 마법소년 김셰프"
            val strings =
                listOf(
                    "돼지고기 300g",
                    "소고기 200g",
                    "빵가루",
                    "계란",
                    "소금",
                )

            return Result.success(title to strings)
        }
    }
