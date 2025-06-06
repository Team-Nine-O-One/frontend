package com.imeanttobe.app901.ui.analysis.component

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.Link
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.model.Store
import com.imeanttobe.app901.util.NativeUtil

@SuppressLint("DefaultLocale")
@Composable
fun StoreCardDescription(
    store: Store,
    isOffline: Boolean,
) {
    val context = LocalContext.current
    val iconSize = 16.dp
    val alpha = 0.7f

    when (isOffline) {
        true -> {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Icon(
                        imageVector = Icons.Rounded.AccessTime,
                        contentDescription = "Estimated time",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = alpha),
                        modifier = Modifier.size(iconSize),
                    )
                    Text(
                        text =
                            stringResource(
                                R.string.format_time_minute,
                                store.estimatedTime,
                            ),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = alpha),
                    )
                }

                // Distance
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Icon(
                        imageVector = Icons.Rounded.LocationOn,
                        contentDescription = "Distance",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = alpha),
                        modifier = Modifier.size(iconSize),
                    )
                    Text(
                        text =
                            stringResource(
                                R.string.format_distance,
                                String.format("%.2f", store.distance),
                            ),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = alpha),
                    )
                }
            }
        }
        false -> {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Icon(
                    imageVector = Icons.Rounded.Link,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = alpha),
                    contentDescription = "Open web link",
                    modifier = Modifier.size(16.dp),
                )
                // Weblink
                Text(
                    text = store.link,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = alpha),
                    textDecoration = TextDecoration.Underline,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier =
                        Modifier.clickable { NativeUtil.openUrl(context, store.link) },
                )
            }
        }
    }
}

@Preview
@Composable
fun OfflineStoreCardDescriptionPreview() {
    StoreCardDescription(
        store = Store.getDefaultInstance(),
        isOffline = true,
    )
}

@Preview
@Composable
fun OnlineStoreCardDescriptionPreview() {
    StoreCardDescription(
        store =
            Store.getDefaultInstance().copy(
                link = "https://google.com",
            ),
        isOffline = false,
    )
}
