package noose.exposed.data

import noose.exposed.core.order.domain.OrderStatus
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object Orders : UUIDTable(name = "orders", columnName = "id") {
    val customerId = long("customerId")
    val status = enumeration("status", OrderStatus::class)
        .default(OrderStatus.SHIPPED)
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    val modifiedAt = datetime("modified_at").clientDefault { LocalDateTime.now() }
}

object OrderItems : LongIdTable(name = "order_items", columnName = "id") {
    val orderId = reference("order_id", Orders)
    val productName = varchar("product_name", 255)
    val price = decimal("price", 10, 2)
}
