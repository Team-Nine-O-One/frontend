package com.imeanttobe.app901.data.type

import android.content.Context
import androidx.datastore.core.DataStore
import com.imeanttobe.app901.IdPrefs
import com.imeanttobe.app901.api.serializer.idPrefsDataStore
import kotlinx.coroutines.flow.first

class IdGenerator(
    private val context: Context,
) {
    private val idPrefsDataStore: DataStore<IdPrefs> = context.idPrefsDataStore

    suspend fun assignId(): Long {
        var assignedId: Long = -1

        idPrefsDataStore.updateData { current ->
            val currentId = current.currentId
            assignedId = currentId
            current
                .toBuilder()
                .setCurrentId(assignedId + 1)
                .build()
        }

        return assignedId
    }

    suspend fun peekId(): Long {
        val prefs = idPrefsDataStore.data.first()
        return prefs.currentId
    }
}
