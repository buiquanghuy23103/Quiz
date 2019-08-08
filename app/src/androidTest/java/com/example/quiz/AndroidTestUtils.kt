package com.example.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.quiz.model.Answer
import com.example.quiz.model.Quiz
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

val sampleQuizList = listOf(
    Quiz("Canberra is the capital of Australia.", 0),
    Quiz("The Pacific Ocean is larger than the Atlantic Ocean.", 1),
    Quiz("The Suez Canal connects the Red Sea and the Indian Ocean.", 2),
    Quiz("The source of the Nile River is in Egypt.", 3),
    Quiz("The Amazon River is the longest river in the Americas.", 4),
    Quiz("Lake Baikal is the world\'s oldest and deepest freshwater lake.", 5)
)

val sampleQuiz = sampleQuizList[0]
val sampleAnswersOfSampleQuiz = generateAnswerListForSampleQuiz()
val sampleAnswer = Answer(sampleQuiz.id, "New answer", true)

private fun generateAnswerListForSampleQuiz(): List<Answer> {
    return mutableListOf<Answer>().also {
        for (i in 0..3) {
            val answer = Answer(sampleQuiz.id, "Answer " + i, i % 2 == 0)
            it.add(answer)
        }
    }
}

@Throws(InterruptedException::class)
fun <T> LiveData<T>.getValueBlocking(): T? {
    var value: T? = null
    val latch = CountDownLatch(1)
    val innerObserver = object : Observer<T> {
        override fun onChanged(it: T) {
            value = it
            latch.countDown()
            this@getValueBlocking.removeObserver(this)
        }
    }
    CoroutineScope(Dispatchers.Main).launch {
        observeForever(innerObserver)
    }
    latch.await(2, TimeUnit.SECONDS)
    return value
}