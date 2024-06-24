package com.magicianguo.fakeminotaur.util

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object TaskPool {
    val CACHE: ExecutorService = Executors.newCachedThreadPool()
}