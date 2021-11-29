package com.vitekkor.task2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.net.URL
import java.util.concurrent.Executors


class ImageLoader : ViewModel() {
    private val executorService = Executors.newSingleThreadExecutor()
    private val bitmap: MutableLiveData<Bitmap> = MutableLiveData()

    fun getBitmap(): LiveData<Bitmap> = bitmap

    fun loadImage() {
        executorService.execute {
            val url = URL("https://cataas.com/cat/says/android-lectures?width=50?height=50")
            bitmap.postValue(BitmapFactory.decodeStream(url.openConnection().getInputStream()))
        }
    }
}