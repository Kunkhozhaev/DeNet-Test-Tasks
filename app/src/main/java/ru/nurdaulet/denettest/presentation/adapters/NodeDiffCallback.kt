package ru.nurdaulet.denettest.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import ru.nurdaulet.denettest.data.database.models.Node

class NodeDiffCallback : DiffUtil.ItemCallback<Node>() {
    override fun areItemsTheSame(oldItem: Node, newItem: Node): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Node, newItem: Node): Boolean {
        return oldItem.getHashName() == newItem.getHashName()
    }

}