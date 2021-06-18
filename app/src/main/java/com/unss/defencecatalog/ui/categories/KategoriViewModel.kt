package com.unss.defencecatalog.ui.categories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.unss.defencecatalog.data.model.KatgoriResponseItem
import com.unss.defencecatalog.data.model.KullanicilarResponse
import com.unss.defencecatalog.data.repository.KategoriRepository
import com.unss.defencecatalog.data.repository.KullaniciRepository
import com.unss.defencecatalog.util.ResourceStatus
import kotlinx.coroutines.launch

class KategoriViewModel: ViewModel() {
    private val kategoriRepository = KategoriRepository()

    init {
        getCategories()
    }

    var loading: MutableLiveData<Boolean>? = MutableLiveData()
    var kategorilerLiveData = MutableLiveData<List<KatgoriResponseItem>>()
    var error = MutableLiveData<Throwable>()

    fun getCategories() = viewModelScope.launch {
        kategoriRepository.getCategories()
            .asLiveData(viewModelScope.coroutineContext).observeForever {

                when (it.status) {
                    ResourceStatus.LOADING -> {
                        loading?.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        Log.e("Artyom", it.data.toString())
                        kategorilerLiveData.postValue(it.data!!)
                        loading?.postValue(false)
                    }

                    ResourceStatus.ERROR -> {
                        error.postValue(it.throwable!!)
                        loading?.postValue(false)
                    }
                }
            }
    }
}