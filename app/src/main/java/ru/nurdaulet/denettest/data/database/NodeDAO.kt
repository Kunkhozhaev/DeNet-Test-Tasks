package ru.nurdaulet.denettest.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.nurdaulet.denettest.data.database.models.Node

@Dao
interface NodeDAO {

    @Query("SELECT * FROM nodes_table WHERE parent LIKE 'null'")
    fun getRoot(): Flow<Node>

    @Query("SELECT * FROM nodes_table WHERE id LIKE :id")
    fun getNodeById(id: String): Flow<Node>

    @Query("SELECT * FROM nodes_table WHERE parent LIKE :id")
    fun getNodeChildren(id: String): Flow<List<Node>>

    @Query("SELECT * FROM nodes_table WHERE id LIKE :id")
    suspend fun getChildById(id: String): Node

    @Insert
    suspend fun insert(node: Node)

    @Delete
    suspend fun deleteNode(node: Node)

    @Update
    suspend fun updateNode(node: Node)
}