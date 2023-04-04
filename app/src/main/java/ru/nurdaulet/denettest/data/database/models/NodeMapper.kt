/*
package ru.nurdaulet.denettest.data.database.models

import ru.nurdaulet.denettest.domain.entities.Node
import javax.inject.Inject

class NodeMapper @Inject constructor() {
    fun mapEntityToDbModel(node: Node): NodeDbModel {
        val nodeDb = NodeDbModel(
            id = node.id,
            parent = node.parent,
            childNodes = node.childNodes
        )
        nodeDb.getHashName() = node.getHashName()
    }

    fun mapDbModelToEntity(nodeDbModel: NodeDbModel): Node = Node(
        id = nodeDbModel.id,
        parent = nodeDbModel.parent,
        childNodes = nodeDbModel.childNodes
    )
}
*/

