package ru.nurdaulet.denettest.presentation.fragments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.nurdaulet.denettest.data.database.models.Node
import ru.nurdaulet.denettest.data.repository.NodeRepository
import javax.inject.Inject

@HiltViewModel
class NodeViewModel @Inject constructor(
    private val repository: NodeRepository,
) : ViewModel() {
    private var _node = MutableLiveData<Node>()
    val node: LiveData<Node>
        get() = _node

    private var _children = MutableLiveData<List<Node>>()
    val children: LiveData<List<Node>>
        get() = _children

    fun setNode(id: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (id == "null" || id == null) {
                repository.getRoot().collect { node ->
                    if (node == null) {
                        repository.createRoot()
                    }
                    _node.postValue(node)
                }
            } else {
                repository.getNodeById(id).collect { node -> _node.postValue(node) }
            }
        }
    }

    fun addChild() {
        _node.value?.let { node ->
            viewModelScope.launch(Dispatchers.IO) {
                repository.addChild(node)
            }
        }
    }

    fun getNodeChildren(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getNodeChildren(id).collect {
                _children.postValue(it)
            }
        }
    }

    fun deleteChild(id: String) {
        _node.value?.let { node ->
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    repository.deleteChild(node, id)
                } catch (e: Exception) {
                    Log.d(TAG, e.toString())
                }
            }
        }
    }

    companion object {
        private const val TAG = "NodeViewModel"
    }
}