package com.unss.defencecatalog.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.unss.defencecatalog.data.model.KullanicilarResponse
import com.unss.defencecatalog.data.repository.KullaniciRepository
import com.unss.defencecatalog.util.ResourceStatus
import kotlinx.coroutines.launch

class KullaniciViewModel: ViewModel() {
    private val kullaniciRepository = KullaniciRepository()

    init {
        getUsers()
    }

    var loading: MutableLiveData<Boolean>? = MutableLiveData()
    var kullaniciLiveData = MutableLiveData<KullanicilarResponse>()
    var error = MutableLiveData<Throwable>()

    fun getUsers() = viewModelScope.launch {
        kullaniciRepository.getUsers()
            .asLiveData(viewModelScope.coroutineContext).observeForever {

                when (it.status) {
                    ResourceStatus.LOADING -> {
                        loading?.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        Log.e("Artyom", it.data.toString())
                        kullaniciLiveData.postValue(it.data!!)
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