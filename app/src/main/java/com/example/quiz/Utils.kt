package com.example.quiz

import timber.log.Timber

fun alert(message: String) = Timber.i("zz (Thread ${Thread.currentThread().name}) $message")

fun getAppInjector() = MainApplication.get().component

const val countDownInitial: Long = 20000
const val countDownInterval: Long = 1000
const val countDownFinnishSignal: Long = -1