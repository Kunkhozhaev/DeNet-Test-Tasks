package ru.nurdaulet.denettest.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "nodes_table")
data class Node(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val parent: String = "null",
    val childNodes: List<String> = emptyList(),
){
    fun getHashName() = this.hashCode().toString(2).takeLast(20)
}