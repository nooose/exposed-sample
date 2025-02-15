package noose.exposed.data

import noose.exposed.core.order.domain.Order
import noose.exposed.core.order.domain.OrderWithoutItems
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

class OrderEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<OrderEntity>(Orders)

    var customerId by Orders.customerId
    var status by Orders.status
    val items by OrderItemEntity referrersOn OrderItems.orderId
    var createdAt by Orders.createdAt
    var modifiedAt by Orders.modifiedAt

    fun update(that: OrderWithoutItems) {
        this.customerId = that.customerId
        this.status = that.status
        this.modifiedAt = that.modifiedAt
    }

    fun toOrder() = Order(
        customerId = customerId,
        id = id.value,
        status = status,
        items = items.map { it.toOrderItem() },
        createdAt = createdAt,
        modifiedAt = modifiedAt,
    )

    fun toWithoutItems() = OrderWithoutItems(
        customerId = customerId,
        id = id.value,
        status = status,
        createdAt = createdAt,
        modifiedAt = modifiedAt,
    )
}
