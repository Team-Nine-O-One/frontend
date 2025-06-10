package com.imeanttobe.app901.ui.memo.component

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingActionButtonMenu
import androidx.compose.material3.FloatingActionButtonMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleFloatingActionButton
import androidx.compose.material3.ToggleFloatingActionButtonDefaults.animateIcon
import androidx.compose.material3.animateFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.traversalIndex

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun MemoFloatingActionButtonMenu(
    fabMenuExpanded: Boolean,
    setFabMenuExpanded: (Boolean) -> Unit,
    items: List<Triple<ImageVector, String, () -> Unit>>,
    modifier: Modifier = Modifier,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomEnd,
    ) {
        BackHandler(enabled = fabMenuExpanded) { setFabMenuExpanded(false) }
        FloatingActionButtonMenu(
            modifier = Modifier.align(Alignment.BottomEnd),
            expanded = fabMenuExpanded,
            button = {
                ToggleFloatingActionButton(
                    modifier =
                        Modifier
                            .semantics {
                                traversalIndex = -1f
                                stateDescription = if (fabMenuExpanded) "Expanded" else "Collapsed"
                                contentDescription = "Toggle menu"
                            }.animateFloatingActionButton(
                                visible = true,
                                alignment = Alignment.BottomEnd,
                            ),
                    checked = fabMenuExpanded,
                    onCheckedChange = { setFabMenuExpanded(!fabMenuExpanded) },
                ) {
                    val imageVector by remember {
                        derivedStateOf {
                            if (checkedProgress > 0.5f) Icons.Filled.Close else Icons.Filled.Add
                        }
                    }
                    Icon(
                        painter = rememberVectorPainter(imageVector),
                        contentDescription = null,
                        modifier = Modifier.animateIcon({ checkedProgress }),
                    )
                }
            },
        ) {
            items.forEachIndexed { index, item ->
                FloatingActionButtonMenuItem(
                    modifier =
                        Modifier.semantics {
                            isTraversalGroup = true
                            // Add a custom a11y action to allow closing the menu when focusing
                            // the last menu item, since the close button comes before the first
                            // menu item in the traversal order.
                            if (index == items.size - 1) {
                                customActions =
                                    listOf(
                                        CustomAccessibilityAction(
                                            label = "Close menu",
                                            action = {
                                                setFabMenuExpanded(false)
                                                true
                                            },
                                        ),
                                    )
                            }
                        },
                    onClick = {
                        item.third()
                        setFabMenuExpanded(false)
                    },
                    icon = { Icon(item.first, contentDescription = null) },
                    text = { Text(text = item.second) },
                )
            }
        }
    }
}
