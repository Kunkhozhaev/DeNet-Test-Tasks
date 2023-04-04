package ru.nurdaulet.denettest.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.nurdaulet.denettest.databinding.FragmentNodeBinding
import ru.nurdaulet.denettest.presentation.adapters.NodeChildAdapter

@AndroidEntryPoint
class NodeFragment : Fragment() {

    private val viewModel: NodeViewModel by viewModels()
    private lateinit var binding: FragmentNodeBinding
    private val args: NodeFragmentArgs by navArgs()
    private lateinit var nodeChildAdapter: NodeChildAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNodeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setNode(args.node)
        setupRecyclerView()
        setupAdapterClickListeners()
        setupObservers()

        binding.btnAdd.setOnClickListener { viewModel.addChild() }
    }

    private fun setupAdapterClickListeners() {
        nodeChildAdapter.setOnNodeClickListener { node ->
            navigateToChild(node.id)
        }
        nodeChildAdapter.setOnDeleteClickListener { node ->
            deleteChild(node.id)
        }
    }

    private fun setupObservers() {
        viewModel.node.observe(viewLifecycleOwner) { node ->
            binding.tvNodeName.text = node.getHashName()
            viewModel.getNodeChildren(node.id)
        }
        viewModel.children.observe(viewLifecycleOwner) { children ->
            nodeChildAdapter.submitList(children)
        }
    }

    private fun setupRecyclerView() {
        nodeChildAdapter = NodeChildAdapter()
        binding.rvNodeChildren.apply {
            adapter = nodeChildAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun navigateToChild(child: String) {
        val action = NodeFragmentDirections.actionNodeFragmentSelf()
        action.node = child
        findNavController().navigate(action)
    }

    private fun deleteChild(child: String) {
        viewModel.deleteChild(child)
    }
}