package noose.exposed.core.order.domain

import java.time.LocalDateTime
import java.util.UUID

/**
 * 주문 도메인 모델 클래스
 */
data class Order(
    val customerId: Long,
    val id: UUID = UUID.randomUUID(),
    val items: List<OrderItem> = emptyList(),
    var status: OrderStatus = OrderStatus.SHIPPED,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var modifiedAt: LocalDateTime = createdAt,
) {
    init {
        require(items.isNotEmpty()) { "Items must not be empty" }
    }
}
