package noose.exposed.core.order.domain

import java.util.UUID

data class OrderItemQuery(
    val orderId: UUID,
    val productName: String,
)
