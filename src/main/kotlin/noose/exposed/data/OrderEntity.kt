package noose.exposed.data

import noose.exposed.core.order.domain.Order
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

class OrderEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<OrderEntity>(Orders)

    var customerId by Orders.customerId
    var price by Orders.price
    var status by Orders.status
    var createdAt by Orders.createdAt
    var modifiedAt by Orders.createdAt

    fun update(that: Order) {
        this.customerId = that.customerId
        this.price = that.price
        this.status = that.status
        this.modifiedAt = that.modifiedAt
    }

    fun toOrder() = Order(
        customerId = customerId,
        price = price,
        id = id.value,
        status = status,
        createdAt = createdAt,
    )
}
