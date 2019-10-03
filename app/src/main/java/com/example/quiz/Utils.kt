package com.example.quiz

import timber.log.Timber

fun alert(message: String) = Timber.i("zz (Thread ${Thread.currentThread().name}) $message")

fun alert2(message: String) = Timber.i("Pull request")