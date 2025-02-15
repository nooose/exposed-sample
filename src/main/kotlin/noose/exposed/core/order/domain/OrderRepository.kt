package noose.exposed.core.order.domain

import java.util.UUID

/**
 * 주문 모델 저장소
 */
interface OrderRepository {

    fun save(order: Order)

    fun findById(id: UUID): Order?
    fun findAll(): List<Order>
    fun update(order: Order)
    fun delete(id: UUID)
}
