package com.projk.madesub1.core.ui

import androidx.recyclerview.widget.DiffUtil
import com.projk.madesub1.core.domain.model.Movies

class MoviesDiffCallback(
    private val oldList: List<Movies>,
    private val newList: List<Movies>
): DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}