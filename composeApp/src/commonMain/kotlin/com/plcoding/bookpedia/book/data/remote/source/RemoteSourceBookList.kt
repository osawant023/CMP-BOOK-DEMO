package com.plcoding.bookpedia.book.data.remote.source

import com.plcoding.bookpedia.book.data.remote.model.DtoResponseBookList
import com.plcoding.bookpedia.book.data.remote.model.DtoResponseSearchBook
import com.plcoding.bookpedia.core.domain.DataError
import com.plcoding.bookpedia.core.domain.Result

interface RemoteSourceBookList {
    suspend fun fetchBookList(): Result<DtoResponseBookList, DataError.Remote>
    suspend fun fetchSearchBooks(query:String , resultLimit:Int?): Result<DtoResponseSearchBook, DataError.Remote>
}