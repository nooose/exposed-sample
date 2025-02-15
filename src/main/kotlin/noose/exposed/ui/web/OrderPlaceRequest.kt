package noose.exposed.ui.web

import noose.exposed.core.order.domain.Order

/**
 * 주문 요청 규격 객체이다.
 */
data class OrderPlaceRequest(
    val customerId: Long,
    val price: Long,
) {

    fun toOrder(): Order {
        return Order(
            customerId = customerId,
            price = price,
        )
    }
}
