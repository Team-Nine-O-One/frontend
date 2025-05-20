package com.imeanttobe.app901.api.serializer

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.protobuf.InvalidProtocolBufferException
import com.imeanttobe.app901.MemoItem
import com.imeanttobe.app901.MemoItemList
import java.io.InputStream
import java.io.OutputStream

object MemoItemListSerializer : Serializer<MemoItemList> {
    override val defaultValue: MemoItemList = MemoItemList.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): MemoItemList {
        try {
            return MemoItemList.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(
        t: MemoItemList,
        output: OutputStream,
    ) = t.writeTo(output)
}

val Context.memoItemDataStore: DataStore<MemoItem> by dataStore(
    fileName = "memo.pb",
    serializer = MemoItemListSerializer,
)
