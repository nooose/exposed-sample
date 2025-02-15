package noose.exposed.core.order.application

import noose.exposed.core.order.domain.Order
import java.util.UUID

/**
 * 주문을 검색한다.
 */
interface FindOrder {

    /**
     * 주문을 상세 조회한다.
     *
     * @param id 주문 식별자
     * @return 주문
     */
    fun byId(id: UUID): Order

    /**
     * 주문을 모두 조회한다.
     *
     * @return 모든 주문 목록
     */
    fun all(): List<Order>
}
