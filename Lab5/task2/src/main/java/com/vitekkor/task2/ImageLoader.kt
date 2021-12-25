package com.vitekkor.task2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future


class ImageLoader(private val executorService: ExecutorService) : ViewModel() {
    private lateinit var task: Future<*>
    private val bitmap: MutableLiveData<Bitmap> = MutableLiveData()

    fun getBitmap(): LiveData<Bitmap> = bitmap

    fun loadImage() {
        task = executorService.submit {
            val url = URL("https://cataas.com/cat/says/android-lectures?width=50?height=50")
            bitmap.postValue(BitmapFactory.decodeStream(url.openConnection().getInputStream()))
        }
    }

    override fun onCleared() {
        task.cancel(true)
        super.onCleared()
    }
}