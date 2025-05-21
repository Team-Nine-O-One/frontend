package com.imeanttobe.app901.api.serializer

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.protobuf.InvalidProtocolBufferException
import com.imeanttobe.app901.IdPrefs
import java.io.InputStream
import java.io.OutputStream

object IdPrefsSerializer : Serializer<IdPrefs> {
    override val defaultValue: IdPrefs = IdPrefs.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): IdPrefs {
        try {
            return IdPrefs.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(
        t: IdPrefs,
        output: OutputStream,
    ) = t.writeTo(output)
}

val Context.idPrefsDataStore: DataStore<IdPrefs> by dataStore(
    fileName = "id_prefs.pb",
    serializer = IdPrefsSerializer,
)
