package com.example.quiz.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.quiz.model.Choice

class ChoiceAssessmentUtil(choiceList: LiveData<List<Choice>>) {
    private val choiceAssessmentList = Transformations.map(choiceList) {
        it.map { choice -> ChoiceAssessment(choice.id, choice.correct, false) }
    } as MutableLiveData

    val assessment = Transformations.map(choiceAssessmentList) {
        it.map { choiceAssessment -> choiceAssessment.isTrue == choiceAssessment.isChosen }
            .all { comparison -> comparison == true }
    }

    fun toggleChoiceChosenById(choiceId: Int) {
        val newMapChoiceId2ChosenState = choiceAssessmentList.value!!
            .let {
                val selectedChoice = getChoiceFromListById(it, choiceId)
                val newList = it.toMutableList()
                newList[it.indexOf(selectedChoice)] = selectedChoice.apply { isChosen = !isChosen }
                newList
            }

        choiceAssessmentList.value = newMapChoiceId2ChosenState
    }

    private fun getChoiceFromListById(list: List<ChoiceAssessment>, choiceId: Int) =
        list.find { choiceAssessment -> choiceAssessment.choiceId == choiceId }
            ?: throw Exception("No choice matches the given choiceId")

    fun getChosenStateById(choiceId: Int) = Transformations.map(choiceAssessmentList) {
        getChoiceFromListById(it, choiceId).isChosen
    }

    data class ChoiceAssessment(
        val choiceId: Int,
        val isTrue: Boolean,
        var isChosen: Boolean
    )
}