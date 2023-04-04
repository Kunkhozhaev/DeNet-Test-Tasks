package ru.nurdaulet.denettest.data.repository

import kotlinx.coroutines.flow.Flow
import ru.nurdaulet.denettest.data.database.NodeDAO
import ru.nurdaulet.denettest.data.database.models.Node
import javax.inject.Inject

class NodeRepositoryImpl @Inject constructor(
    private val dao: NodeDAO
) : NodeRepository {

    override suspend fun createRoot() {
        dao.insert(Node())
    }

    override suspend fun addChild(node: Node) {
        val newNode = Node(parent = node.id)
        val updatedNode = node.copy(childNodes = node.childNodes + newNode.id)
        dao.insert(newNode)
        dao.updateNode(updatedNode)
    }

    override suspend fun deleteChild(node: Node, childId: String) {
        val newNode = node.copy(childNodes = node.childNodes - childId)
        val child = dao.getChildById(childId)
        deleteNode(child)
        dao.updateNode(newNode)
    }

    override suspend fun deleteNode(node: Node) {
        if (node.childNodes.isNotEmpty()) {
            for (childId in node.childNodes) {
                val child = dao.getChildById(childId)
                // Apparently nodes are not deleted if we don't use this condition. Can't explain why
                if (child != null) deleteNode(child)
            }
        }
        dao.deleteNode(node)
    }

    override fun getRoot(): Flow<Node> {
        return dao.getRoot()
    }

    override fun getNodeById(id: String): Flow<Node> {
        return dao.getNodeById(id)
    }

    override fun getNodeChildren(id: String): Flow<List<Node>> {
        return dao.getNodeChildren(id)
    }

}