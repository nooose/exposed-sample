package noose.exposed.core.order.domain

import java.time.LocalDateTime
import java.util.UUID

/**
 * 주문 도메인 모델 클래스
 */
data class OrderWithoutItems(
    val customerId: Long,
    val id: UUID = UUID.randomUUID(),
    var status: OrderStatus = OrderStatus.SHIPPED,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var modifiedAt: LocalDateTime = createdAt,
) {

    fun modify(command: OrderModifyCommand) {
        this.status = command.status
        this.modifiedAt = LocalDateTime.now()
    }
}
