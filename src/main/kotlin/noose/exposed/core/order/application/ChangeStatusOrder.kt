package noose.exposed.core.order.application

import noose.exposed.core.order.domain.OrderModifyCommand
import java.util.UUID

/**
 * 주문을 상태를 변경한다.
 */
interface ChangeStatusOrder {

    /**
     * @param id 주문 식별자
     * @param command 주문 변경 요청 객체
     * @return 주문 식별자
     */
    fun change(id: UUID, command: OrderModifyCommand)
}
