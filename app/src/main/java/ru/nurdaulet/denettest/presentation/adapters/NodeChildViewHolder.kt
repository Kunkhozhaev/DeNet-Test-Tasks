package ru.nurdaulet.denettest.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import ru.nurdaulet.denettest.data.database.models.Node
import ru.nurdaulet.denettest.databinding.NodeChildItemViewBinding

class NodeChildViewHolder(
    private val binding: NodeChildItemViewBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        child: Node,
        navigateListener: ((node: Node) -> Unit)?,
        deleteListener: ((node: Node) -> Unit)?
    ) {
        binding.cardView.setOnClickListener { navigateListener?.let { it(child) } }
        binding.tvChildName.text = child.getHashName()
        binding.btnDelete.setOnClickListener { deleteListener?.let { it(child) } }
    }
}