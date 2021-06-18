package com.unss.defencecatalog.data.datasource

import com.unss.defencecatalog.data.model.KullanicilarResponse
import com.unss.defencecatalog.util.Resource
import kotlinx.coroutines.flow.Flow

interface KullanicilarDataSource {
    fun getUsers(): Flow<Resource<KullanicilarResponse>>
}