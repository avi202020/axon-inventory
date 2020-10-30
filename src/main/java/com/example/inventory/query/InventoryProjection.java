package com.example.inventory.query;

import com.axon.event.InventoryCreatedEvent;
import com.axon.event.ProductCreatedEvent;
import com.example.inventory.model.Inventory;
import com.example.inventory.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InventoryProjection {

    private final InventoryService inventoryService;

    public InventoryProjection(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }


    @EventHandler
    public void on(InventoryCreatedEvent event) {
        log.trace("projecting {}", event);
        Inventory inventory = inventoryService.getInventoryByProductCode(event.getProductCode()).orElseGet(() -> {
            Inventory inv = new Inventory();
            inv.setQty(0L);
            inv.setProductCode(event.getProductCode());
            return inv;
        });
        inventory.setQty(inventory.getQty() + 1);
        inventoryService.addInventory(inventory);
    }
}
