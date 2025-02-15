package noose.exposed.data

import noose.exposed.core.order.domain.OrderStatus
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object Orders : UUIDTable(name = "orders", columnName = "id") {
    val customerId = long("customerId")
    val price = long("price")
    val status = enumeration("status", OrderStatus::class)
        .default(OrderStatus.SHIPPED)
    val createdAt = datetime("created_at").default(LocalDateTime.now())
    val modifiedAt = datetime("modified_at").default(LocalDateTime.now())
}
