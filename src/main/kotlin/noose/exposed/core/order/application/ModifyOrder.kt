package noose.exposed.core.order.application

import noose.exposed.core.order.domain.OrderModifyCommand
import java.util.UUID

/**
 * 주문을 수정한다.
 */
interface ModifyOrder {

    /**
     * @param id 주문 식별자
     * @param command 주문 변경 요청 객체
     * @return 주문 식별자
     */
    fun modify(id: UUID, command: OrderModifyCommand)
}
