package com.vitekkor.task3

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.request.*
import kotlinx.coroutines.launch
import java.io.InputStream


class ImageLoader : ViewModel() {
    private val bitmap: MutableLiveData<Bitmap> = MutableLiveData()
    private val client = HttpClient(Android)

    fun getBitmap(): LiveData<Bitmap> = bitmap

    fun loadImage() {
        viewModelScope.launch {
            bitmap.postValue(BitmapFactory.decodeStream(client.get<InputStream>("https://cataas.com/cat/says/android-lectures?width=50?height=50")))
        }
    }
}