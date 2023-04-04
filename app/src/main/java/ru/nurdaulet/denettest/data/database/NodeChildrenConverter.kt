package ru.nurdaulet.denettest.data.database

import androidx.room.TypeConverter

class NodeChildrenConverter {
    @TypeConverter
    fun toChildren(childNodes: List<String>): String {
        return childNodes.joinToString(" ")
    }

    @TypeConverter
    fun fromChildren(childNodes: String): List<String> {
        return childNodes.split(" ").toList()
    }
}