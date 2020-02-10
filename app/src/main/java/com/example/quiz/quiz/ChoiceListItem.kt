package com.example.quiz.quiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.databinding.ChoiceListItemBinding
import com.example.quiz.model.Choice

class ChoiceListItem private constructor(private val binding: ChoiceListItemBinding) :
    RecyclerView.ViewHolder(binding.root)
{
    lateinit var clickListener: Listener

    companion object{
        fun from(parent: ViewGroup): ChoiceListItem {
            val inflater = LayoutInflater.from(parent.context)
            val binding: ChoiceListItemBinding =
                DataBindingUtil.inflate(inflater, R.layout.choice_list_item, parent, false)
            return ChoiceListItem(binding)
        }
    }

    fun bind(choice: Choice) {
        with(binding) {
            this.choice = choice
            choiceButton.let { view ->
                clickListener.setOptionButtonColor(view, choice.id)
                view.setOnClickListener { clickListener.onOptionButtonClick(choice.id) }
            }
            executePendingBindings()
        }
    }

    interface Listener {
        fun onOptionButtonClick(choiceId: String)
        fun setOptionButtonColor(view: View, choiceId: String)
    }


}