package com.unss.defencecatalog.data.datasource

import com.unss.defencecatalog.data.model.KatgoriResponseItem
import com.unss.defencecatalog.util.Resource
import kotlinx.coroutines.flow.Flow

interface KategorilerDataSource {
    fun getCategories(): Flow<Resource<List<KatgoriResponseItem>>>
}