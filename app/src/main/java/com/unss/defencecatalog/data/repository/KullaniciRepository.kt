package com.unss.defencecatalog.data.repository

import com.unss.defencecatalog.data.datasource.remote.RemoteKullanicilarDataSource
import com.unss.defencecatalog.data.model.KullanicilarResponse
import com.unss.defencecatalog.util.Resource
import kotlinx.coroutines.flow.Flow

class KullaniciRepository {
    private var kullanicilarDataSource: RemoteKullanicilarDataSource? = null
    init {
        kullanicilarDataSource = RemoteKullanicilarDataSource()
    }

    fun getUsers(): Flow<Resource<KullanicilarResponse>>{
        return kullanicilarDataSource!!.getUsers()
    }
}