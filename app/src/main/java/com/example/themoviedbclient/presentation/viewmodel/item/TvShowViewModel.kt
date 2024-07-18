package com.example.themoviedbclient.presentation.viewmodel.item

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedbclient.data.model.ItemModel
import com.example.themoviedbclient.domain.repository.item.TvShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(
    private val tvShowRepository: TvShowRepository
): ViewModel(), ItemViewModelInterface {

    private val _loader: MutableLiveData<Boolean> = MutableLiveData(false)
    override val loader: LiveData<Boolean> get() = _loader

    private val _item: MutableLiveData<ItemModel?> = MutableLiveData(null)
    override val item: LiveData<ItemModel?> get() = _item

    override fun setItem(item: ItemModel) {
        _item.postValue(item)
    }
    override fun getImageFullPath(path: String): String {
        return tvShowRepository.getImageFullPath(path)
    }

    override fun showLoader(state: Boolean) {
        _loader.postValue(state)
    }

    override suspend fun saveItem() {
        item.value?.let {
            showLoader(true)
            viewModelScope.launch(Dispatchers.IO) {
                delay(1000) // Simulate workflow
                tvShowRepository.saveItem(it)
                setItem(it.copy(saved = true))
                showLoader(false)
            }
        }
    }
}