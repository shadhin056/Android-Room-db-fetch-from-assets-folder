package com.example.konamoniruzzaman.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.shadhin.android_jetpack.view.model.WordDatabase
import com.shadhin.android_jetpack.view.model.WordModel
import com.shadhin.android_jetpack.view.view_model.BaseViewModel
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : BaseViewModel(application) {
    val words = MutableLiveData<List<WordModel>>()

    fun fetchFromDB(search:String) {

        launch {
            val words = WordDatabase(getApplication()).wordDao().getAllWord(search)
            this@ListViewModel.words.value=words
           // Toast.makeText(getApplication(), "Fetch From Database", Toast.LENGTH_LONG).show()
        }

    }
    override fun onCleared() {
        super.onCleared()
        //disposable.clear()
    }
}
