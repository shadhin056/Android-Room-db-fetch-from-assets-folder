package com.shadhin.android_jetpack.view.view_model

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.shadhin.android_jetpack.view.model.WordDatabase
import com.shadhin.android_jetpack.view.model.WordModel
import kotlinx.coroutines.launch


class WordViewModel(application: Application) : BaseViewModel(application) {

    val word = MutableLiveData<String>()


    override fun onCleared() {
        super.onCleared()
        // disposable.clear()
    }

    fun storeWordLocally(user: WordModel) {

       launch {
            val cus = WordDatabase(getApplication()).wordDao()
            val result = cus.insertAll(user)
        }
        word.value="information added successfully"
            //Toast.makeText(getApplication(), "Added "+result, Toast.LENGTH_LONG).show()

    }

    fun deleteLocally() {
        launch {
            val cus = WordDatabase(getApplication()).wordDao()
            cus.deleteData()
        }
        word.value="information delete successfully "
        Toast.makeText(getApplication(), "information delete successfully", Toast.LENGTH_LONG).show()
    }
}
