package noose.exposed.data

import noose.exposed.core.order.domain.Order
import noose.exposed.core.order.domain.OrderItemQuery
import noose.exposed.core.order.domain.OrderRepository
import noose.exposed.core.order.domain.OrderWithoutItems
import noose.exposed.data.OrderItems.orderId
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
            .select(Orders.id, OrderItems.productName)
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
        val orders = OrderEntity.all()
            .map { it.toWithoutItems() }
        val orderIds = orders.map { it.id }
        val orderItemMap = OrderItemEntity.find { orderId inList orderIds }
            .groupBy { it.order.id.value }

        return orders.map {
            Order(
                customerId = it.customerId,
                id = it.id,
                items = orderItemMap[it.id]?.map { it.toOrderItem() } ?: emptyList(),
                status = it.status,
                createdAt = it.createdAt,
                modifiedAt = it.modifiedAt,
            )
        }
    }

    override fun update(order: OrderWithoutItems) {
        val findOrder = OrderEntity.findById(order.id)
        findOrder?.update(order)
    }

    override fun delete(id: UUID) {
        OrderItemEntity.find { orderId eq id }.forEach {
            it.delete()
        }
        OrderEntity.findById(id)?.delete()
    }
}
