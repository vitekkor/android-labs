package com.vitekkor.task2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.util.concurrent.ExecutorService

class MyViewModelFactory(private val app: MyApp) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ExecutorService::class.java)
            .newInstance(app.executorService)
    }
}