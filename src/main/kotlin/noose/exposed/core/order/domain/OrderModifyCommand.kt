package noose.exposed.core.order.domain

data class OrderModifyCommand(
    val price: Long,
    val status: OrderStatus,
)
