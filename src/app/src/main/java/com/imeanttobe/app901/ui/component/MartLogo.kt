package com.imeanttobe.app901.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.R

@Composable
fun MartLogo(
    name: String,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val martKeywordsToLogos =
        mapOf(
            "이마트" to R.drawable.mart_emart,
            "홈플러스" to R.drawable.mart_homeplus,
            "롯데" to R.drawable.mart_lotte,
            "GS" to R.drawable.mart_gs,
            "하나로" to R.drawable.mart_hanaro,
            "농협" to R.drawable.mart_hanaro,
            "네이버" to R.drawable.mart_naver,
            "NAVER" to R.drawable.mart_naver,
            "쿠팡" to R.drawable.mart_coupang,
            "COUPANG" to R.drawable.mart_coupang,
        )
    val logo =
        martKeywordsToLogos.entries
            .firstOrNull { (keyword, _) -> name.contains(keyword) } // Find the first entry where name contains the keyword
            ?.value // Get the drawable resource (the value from the map entry)
            ?: -1 // Default if no keyword is found

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier,
    ) {
        if (logo != -1) {
            Image(
                painter = painterResource(id = logo),
                contentDescription = "Mart logo",
                modifier = Modifier.matchParentSize(),
            )
        } else {
            Icon(
                imageVector = Icons.Rounded.ShoppingCart,
                contentDescription = "Mart logo",
                tint = MaterialTheme.colorScheme.primary,
                modifier =
                    Modifier
                        .background(color = MaterialTheme.colorScheme.surfaceContainerLowest)
                        .padding(16.dp)
                        .matchParentSize(),
            )
        }
    }
}

@Preview
@Composable
fun MartLogoPreview() {
    MartLogo(name = "이마트", modifier = Modifier.sizeIn(minHeight = 50.dp, minWidth = 50.dp))
}

@Preview
@Composable
fun DefaultMartLogoPreview() {
    MartLogo(name = "가나다라", modifier = Modifier.sizeIn(minHeight = 50.dp, minWidth = 50.dp))
}
