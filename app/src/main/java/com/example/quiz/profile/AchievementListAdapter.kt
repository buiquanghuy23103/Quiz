package com.example.quiz.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.databinding.AchievementItemBinding
import com.example.quiz.model.Achievement

class AchievementListAdapter(private val userId: String?)
    : ListAdapter<Achievement, AchievementListAdapter.AchievementItem>(diffCallback)
{


    companion object {
        val diffCallback = object: DiffUtil.ItemCallback<Achievement>() {
            override fun areItemsTheSame(oldItem: Achievement, newItem: Achievement): Boolean {
                return oldItem.category.id == newItem.category.id
            }

            override fun areContentsTheSame(oldItem: Achievement, newItem: Achievement): Boolean {
                return oldItem.category.text == newItem.category.text &&
                        oldItem.quizzes.size == newItem.quizzes.size &&
                        oldItem.scores.size == newItem.scores.size

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchievementItem {
        return AchievementItem.from(parent, userId)
    }

    override fun onBindViewHolder(holder: AchievementItem, position: Int) {
        val achievement = getItem(position)
        holder.bind(achievement)
    }

    class AchievementItem private constructor(
        private val binding: AchievementItemBinding,
        private val userId: String?
    ): RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup, userId: String?): AchievementItem {
                val inflater = LayoutInflater.from(parent.context)
                val binding: AchievementItemBinding
                    = DataBindingUtil.inflate(inflater, R.layout.achievement_item, parent, false)
                return AchievementItem(binding, userId)
            }
        }

        fun bind(achievement: Achievement) {
            binding.categoryName.text = achievement.category.text
            val totalQuizzes = achievement.quizzes.size

            if (totalQuizzes > 0 && userId != null) {
                val currentUserScore = achievement.scores.filter { it.userId == userId }
                val totalScores = currentUserScore.size
                binding.numberOfAnsweredQuiz.text = "$totalScores / $totalQuizzes"
                val progress = (totalScores * 100 / totalQuizzes)
                binding.achievementProgressBar.progress = progress
            }

        }

    }

}