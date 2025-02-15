package noose.exposed.data

import noose.exposed.core.order.domain.Order
import noose.exposed.core.order.domain.OrderRepository
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class OrderExposedRepository : OrderRepository {

    override fun save(order: Order) {
        transaction {
//            Orders.insert {
//                it[id] = order.id
//                it[price] = order.price
//                it[customerId] = order.customerId
//                it[status] = order.status
//                it[createdAt] = order.createdAt
//            }

            // DAO 방식
            OrderEntity.new(order.id) {
                price = order.price
                customerId = order.customerId
                status = order.status
                createdAt = order.createdAt
            }
        }
    }

    override fun findById(id: UUID): Order? {
        return transaction {
//            val findOrder = Orders.selectAll()
//                .where { Orders.id eq id }
//                .singleOrNull()

            val findOrder = OrderEntity.findById(id)
            findOrder?.toOrder()
        }
    }

    override fun findAll(): List<Order> {
        return OrderEntity.all()
            .map { it.toOrder() }
    }

    override fun update(order: Order) {
        val findOrder = OrderEntity.findById(order.id)
        findOrder?.update(order)
    }

    override fun delete(id: UUID) {
        OrderEntity.findById(id)?.delete()
    }
}
