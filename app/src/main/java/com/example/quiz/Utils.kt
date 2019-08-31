package com.example.quiz

import timber.log.Timber

fun alert(message: String) = Timber.i("zz (Thread ${Thread.currentThread().name}) $message")