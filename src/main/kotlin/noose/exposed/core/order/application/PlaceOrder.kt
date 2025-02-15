package noose.exposed.core.order.application

import noose.exposed.core.order.domain.Order
import java.util.UUID

/**
 * 주문을 처리한다.
 */
interface PlaceOrder {

    /**
     * @param order 주문 객체
     * @return 주문 식별자
     */
    fun place(order: Order): UUID
}
