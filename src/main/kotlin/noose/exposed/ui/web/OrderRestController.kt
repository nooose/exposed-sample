package noose.exposed.ui.web

import noose.exposed.core.order.application.OrderService
import noose.exposed.core.order.domain.OrderItemQuery
import noose.exposed.core.order.domain.OrderModifyCommand
import noose.exposed.core.order.domain.OrderStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.UUID

@RestController
class OrderRestController(
    private val service: OrderService,
) {

    @PostMapping("/api/orders")
    fun placeOrder(@RequestBody request: OrderPlaceRequest): ResponseEntity<Unit> {
        val order = request.toOrder()
        val uuid = service.place(order)
        val uri = ServletUriComponentsBuilder
            .fromCurrentRequestUri()
            .path("/${uuid}")
            .build()
            .toUri()
        return ResponseEntity.created(uri).build()
    }

    @GetMapping("/api/orders")
    fun getOrders(): List<OrderDetailResponse> {
        val orders = service.all()
        return orders.map { OrderDetailResponse.from(it) }
    }

    @GetMapping("/api/orders/{orderId}")
    fun getOrder(
        @PathVariable orderId: UUID,
    ): OrderDetailResponse {
        val order = service.byId(orderId)
        return OrderDetailResponse.from(order)
    }

    @GetMapping("/api/orders/{orderId}/items")
    fun getOrderItems(
        @PathVariable orderId: UUID,
    ): List<OrderItemQuery> {
        return service.itemsById(orderId)
    }

    @PutMapping("/api/orders/{orderId}")
    fun modifyOrder(
        @PathVariable orderId: UUID,
    ) {
        val command = OrderModifyCommand(
            status = OrderStatus.DELIVERED,
        )
        service.change(orderId, command)
    }

    @DeleteMapping("/api/orders/{orderId}")
    fun deleteOrder(
        @PathVariable orderId: UUID,
    ) {
        service.delete(orderId)
    }
}
