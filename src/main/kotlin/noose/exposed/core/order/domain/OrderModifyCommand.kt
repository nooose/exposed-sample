package noose.exposed.core.order.domain

data class OrderModifyCommand(
    val status: OrderStatus,
)
