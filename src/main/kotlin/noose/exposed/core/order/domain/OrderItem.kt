package noose.exposed.core.order.domain

import java.math.BigDecimal

data class OrderItem(
    val price: BigDecimal,
    val productName: String,
)
