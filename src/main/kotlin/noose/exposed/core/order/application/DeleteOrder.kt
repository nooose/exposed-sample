package noose.exposed.core.order.application

import java.util.UUID

/**
 * 주문을 삭제한다.
 */
interface DeleteOrder {

    /**
     * @param id 주문 식별자
     */
    fun delete(id: UUID)
}
