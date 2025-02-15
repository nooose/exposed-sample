package noose.exposed.core.order.domain

/**
 * 주문을 검색했을 때 존재하지 않으면 발생할 수 있는 예외 클래스이다.
 */
data class OrderNotFoundException(
    override val message: String,
) : RuntimeException(message)
