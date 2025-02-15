package noose.exposed.core.order.domain

import java.util.UUID

/**
 * 주문 모델 저장소
 */
interface OrderRepository {

    fun save(order: Order)

    fun findById(id: UUID): Order?
    fun findOrderItems(orderId: UUID): List<OrderItemQuery>
    fun findWithoutItemsById(id: UUID): OrderWithoutItems?
    fun findAll(): List<Order>
    fun update(order: OrderWithoutItems)
    fun delete(id: UUID)
}
