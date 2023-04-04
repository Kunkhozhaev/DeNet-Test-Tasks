package ru.nurdaulet.denettest.data.repository

import kotlinx.coroutines.flow.Flow
import ru.nurdaulet.denettest.data.database.models.Node

interface NodeRepository {

    suspend fun createRoot()

    suspend fun addChild(node: Node)

    suspend fun deleteChild(node: Node, childId: String)

    suspend fun deleteNode(node: Node)

    fun getRoot(): Flow<Node>

    fun getNodeById(id: String): Flow<Node>

    fun getNodeChildren(id: String): Flow<List<Node>>
}