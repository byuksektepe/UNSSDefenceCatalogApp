package com.unss.defencecatalog.data.datasource.remote

import com.unss.defencecatalog.data.datasource.KullanicilarDataSource
import com.unss.defencecatalog.data.model.KullanicilarResponse
import com.unss.defencecatalog.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class RemoteKullanicilarDataSource: KullanicilarDataSource {
    override fun getUsers(): Flow<Resource<KullanicilarResponse>> = flow {
        try {
            emit(Resource.Loading())

            val response = MainService.build().getUsers()

            if (response.isSuccessful){
                response.body()?.let{
                    emit(Resource.Success(it))
                }
            }

        }catch (ex: Exception) {
            emit(Resource.Error(ex))
            ex.printStackTrace()
        }
    }
}