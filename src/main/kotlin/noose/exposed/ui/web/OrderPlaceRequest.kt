package noose.exposed.ui.web

import noose.exposed.core.order.domain.Order
import noose.exposed.core.order.domain.OrderItem
import java.math.BigDecimal

/**
 * 주문 요청 규격 객체이다.
 */
data class OrderPlaceRequest(
    val customerId: Long,
    val products: List<ProductRequest>,
) {

    data class ProductRequest(
        val name: String,
        val price: BigDecimal,
    )

    fun toOrder(): Order {
        return Order(
            customerId = customerId,
            items = products.map {
                OrderItem(
                    price = it.price,
                    productName = it.name,
                )
            }
        )
    }
}
