package com.vitekkor.lab5

import android.app.Application
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MyApplication : Application() {
    val executorService: ExecutorService = Executors.newFixedThreadPool(1)
}