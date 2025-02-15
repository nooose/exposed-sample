package noose.exposed.ui.web

import noose.exposed.core.order.domain.Order
import noose.exposed.core.order.domain.OrderItem
import noose.exposed.core.order.domain.OrderStatus
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class OrderDetailResponse(
    val id: UUID,
    val customerId: Long,
    val status: OrderStatus,
    val items: List<OrderItemResponse>,
    val createdAt: LocalDateTime,
) {

    data class OrderItemResponse(
        val productName: String,
        val price: BigDecimal,
    ) {
        companion object {
            fun from(orderItem: OrderItem): OrderItemResponse {
                return OrderItemResponse(
                    productName = orderItem.productName,
                    price = orderItem.price,
                )
            }
        }
    }

    companion object {
        fun from(order: Order): OrderDetailResponse {
            return with(order) {
                return OrderDetailResponse(
                    id = id,
                    customerId = customerId,
                    status = status,
                    createdAt = createdAt,
                    items = items.map { OrderItemResponse.from(it) },
                )
            }
        }
    }
}
