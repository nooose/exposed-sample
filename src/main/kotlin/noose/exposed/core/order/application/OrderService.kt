package noose.exposed.core.order.application

import noose.exposed.core.order.domain.Order
import noose.exposed.core.order.domain.OrderModifyCommand
import noose.exposed.core.order.domain.OrderNotFoundException
import noose.exposed.core.order.domain.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Transactional
@Service
class OrderService(
    private val orderRepository: OrderRepository,
) : PlaceOrder, FindOrder, ModifyOrder, DeleteOrder {

    override fun place(order: Order): UUID {
        orderRepository.save(order)
        return order.id
    }

    @Transactional(readOnly = true)
    override fun byId(id: UUID): Order {
        return orderRepository.findById(id)
            ?: throw OrderNotFoundException("Order with ID $id not found")
    }

    @Transactional(readOnly = true)
    override fun all(): List<Order> {
        return orderRepository.findAll()
    }

    override fun modify(id: UUID, command: OrderModifyCommand) {
        val order = byId(id)
        order.modify(command)
        orderRepository.update(order)
    }

    override fun delete(id: UUID) {
        orderRepository.delete(id)
    }
}
