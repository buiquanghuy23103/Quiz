package com.example.quiz.framework

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

abstract class BaseListAdapter<T : BaseListItem> :
    ListAdapter<BaseData, BaseListItem>(ListDiffCallback()) {

    abstract fun getViewHolder(inflater: LayoutInflater): T

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseListItem {
        val inflater = LayoutInflater.from(parent.context)
        return getViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: BaseListItem, position: Int) {
        holder.bind(getItem(position))
    }
}