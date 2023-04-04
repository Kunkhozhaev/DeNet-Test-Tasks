package ru.nurdaulet.denettest.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.nurdaulet.denettest.data.database.models.Node

@Database(entities = [Node::class], version = 1, exportSchema = false)
@TypeConverters(NodeChildrenConverter::class)
abstract class NodeDatabase : RoomDatabase() {
    abstract val nodeDao: NodeDAO

    companion object {
        @Volatile
        private var INSTANCE: NodeDatabase? = null
        private const val DB_NAME = "node_db.db"

        fun getInstance(context: Context): NodeDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NodeDatabase::class.java, DB_NAME
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}