package ru.nurdaulet.denettest.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.nurdaulet.denettest.data.database.models.Node
import ru.nurdaulet.denettest.databinding.NodeChildItemViewBinding

class NodeChildAdapter : ListAdapter<Node, NodeChildViewHolder>(NodeDiffCallback()) {

    private var onNodeClickListener: ((node: Node) -> Unit)? = null
    private var onDeleteClickListener: ((node: Node) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NodeChildViewHolder {
        return NodeChildViewHolder(
            NodeChildItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NodeChildViewHolder, position: Int) {
        holder.bind(currentList[position], onNodeClickListener, onDeleteClickListener)
    }

    fun setOnNodeClickListener(listener: (node: Node) -> Unit) {
        onNodeClickListener = listener
    }

    fun setOnDeleteClickListener(listener: (node: Node) -> Unit) {
        onDeleteClickListener = listener
    }

}