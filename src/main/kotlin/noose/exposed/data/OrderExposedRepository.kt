package noose.exposed.data

import noose.exposed.core.order.domain.Order
import noose.exposed.core.order.domain.OrderItemQuery
import noose.exposed.core.order.domain.OrderRepository
import noose.exposed.core.order.domain.OrderWithoutItems
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class OrderExposedRepository : OrderRepository {

    // Tx 명시적 선언
    override fun save(order: Order) {
        transaction {
            val orderEntity = OrderEntity.new(order.id) {
                customerId = order.customerId
                status = order.status
                createdAt = order.createdAt
            }

            order.items.forEach { orderItem ->
                OrderItemEntity.new {
                    this.order = orderEntity
                    this.productName = orderItem.productName
                    this.price = orderItem.price
                }
            }
        }
    }

    override fun findById(id: UUID): Order? {
        val findOrder = OrderEntity.findById(id)
        return findOrder?.toOrder()
    }

    override fun findOrderItems(orderId: UUID): List<OrderItemQuery> {
        return (Orders innerJoin OrderItems)
            .selectAll()
            .where { OrderItems.orderId eq orderId }
            .map {
                OrderItemQuery(
                    orderId = it[Orders.id].value,
                    productName = it[OrderItems.productName]
                )
            }
    }

    override fun findWithoutItemsById(id: UUID): OrderWithoutItems? {
        val findOrder = OrderEntity.findById(id)
        return findOrder?.toWithoutItems()
    }

    override fun findAll(): List<Order> {
        return OrderEntity.all()
            .map { it.toOrder() }
    }

    override fun update(order: OrderWithoutItems) {
        val findOrder = OrderEntity.findById(order.id)
        findOrder?.update(order)
    }

    override fun delete(id: UUID) {
        OrderItemEntity.find { OrderItems.orderId eq id }.forEach {
            it.delete()
        }
        OrderEntity.findById(id)?.delete()
    }
}
