package com.imeanttobe.app901.ui.dev

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.ui.component.NaverMap

@Composable
fun DevSection(viewModel: DevSectionViewModel = hiltViewModel()) {
    Scaffold(
        modifier = Modifier,
    ) { innerPadding ->
        val context = LocalContext.current

        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // Memo testing
            Text(
                text = "id = ${viewModel.id.value}",
            )

            Button(
                onClick = {
                    viewModel.assignId()
                },
            ) {
                Text(text = "assign id")
            }

            // Route test
            Button(
                onClick = {
                    viewModel.getRoute(printToast = { message ->
                        Toast.makeText(context, "# Result = $message", Toast.LENGTH_SHORT).show()
                    })
                },
            ) {
                Text(text = "get route")
            }
            Text(
                text = "distance = ${viewModel.route.value.distanceAsKilometer} km",
            )
            Text(
                text = "duration = ${viewModel.route.value.durationAsMinute} min",
            )
            Text(
                text = "route = ${if (viewModel.route.value.paths
                        .isEmpty()
                ) {
                    "empty"
                } else {
                    viewModel.route.value.paths
                        .subList(0, 5)
                }}",
            )

            // Naver map
            NaverMap(
                pathPoints =
                    viewModel.route.value.paths
                        .map { path -> path.toLatLng() },
            )
        }
    }
}
