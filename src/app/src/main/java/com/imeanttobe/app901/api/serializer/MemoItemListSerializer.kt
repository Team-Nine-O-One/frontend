package com.imeanttobe.app901.api.serializer

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.protobuf.InvalidProtocolBufferException
import com.imeanttobe.app901.ProtoMemoItemList
import java.io.InputStream
import java.io.OutputStream

object MemoItemListSerializer : Serializer<ProtoMemoItemList> {
    override val defaultValue: ProtoMemoItemList = ProtoMemoItemList.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ProtoMemoItemList {
        try {
            return ProtoMemoItemList.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(
        t: ProtoMemoItemList,
        output: OutputStream,
    ) = t.writeTo(output)
}

val Context.memoItemListDataStore: DataStore<ProtoMemoItemList> by dataStore(
    fileName = "memo.pb",
    serializer = MemoItemListSerializer,
)
