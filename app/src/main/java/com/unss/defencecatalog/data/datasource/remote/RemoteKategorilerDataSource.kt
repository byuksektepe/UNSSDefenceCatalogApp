package com.unss.defencecatalog.data.datasource.remote

import com.unss.defencecatalog.data.datasource.KategorilerDataSource
import com.unss.defencecatalog.data.model.KatgoriResponseItem
import com.unss.defencecatalog.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteKategorilerDataSource : KategorilerDataSource {
    override fun getCategories(): Flow<Resource<List<KatgoriResponseItem>>> = flow {
        try {
            emit(Resource.Loading())

            val response = MainService.build().getCategories()

            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success(it))
                }
            }

        } catch (ex: Exception){
            emit(Resource.Error(ex))
            ex.printStackTrace()
        }
    }
}