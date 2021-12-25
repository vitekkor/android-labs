package com.vitekkor.task2

import android.app.Application
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MyApp: Application() {
    val executorService: ExecutorService = Executors.newSingleThreadExecutor()
}