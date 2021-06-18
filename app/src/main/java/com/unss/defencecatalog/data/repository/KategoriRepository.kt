package com.unss.defencecatalog.data.repository

import com.unss.defencecatalog.data.datasource.remote.RemoteKategorilerDataSource
import com.unss.defencecatalog.data.model.KatgoriResponseItem
import com.unss.defencecatalog.util.Resource
import kotlinx.coroutines.flow.Flow

class KategoriRepository {
    private var kategorilerDataSource: RemoteKategorilerDataSource? = null
    init {
        kategorilerDataSource = RemoteKategorilerDataSource()
    }

    fun getCategories(): Flow<Resource<List<KatgoriResponseItem>>> {
        return kategorilerDataSource!!.getCategories()
    }
}