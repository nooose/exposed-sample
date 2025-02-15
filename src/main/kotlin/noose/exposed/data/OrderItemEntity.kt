package noose.exposed.data

import noose.exposed.core.order.domain.OrderItem
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class OrderItemEntity(id: EntityID<Long>) : Entity<Long>(id) {
    companion object : EntityClass<Long, OrderItemEntity>(OrderItems)

    var order by OrderEntity referencedOn OrderItems.orderId
    var productName by OrderItems.productName
    var price by OrderItems.price

    fun toOrderItem(): OrderItem {
        return OrderItem(
            price = price,
            productName = productName,
        )
    }
}
