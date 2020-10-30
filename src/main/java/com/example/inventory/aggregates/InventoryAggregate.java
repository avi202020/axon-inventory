package com.example.inventory.aggregates;

import com.axon.command.CreateInventoryCommand;
import com.axon.command.CreateProductCommand;
import com.axon.event.InventoryCreatedEvent;
import com.axon.event.ProductCreatedEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Data
@NoArgsConstructor
@Aggregate
public class InventoryAggregate {

    @AggregateIdentifier
    private UUID inventoryId;
    private String productCode;
    private Long qty;

    @CommandHandler
    public InventoryAggregate(CreateInventoryCommand createInventoryCommand) {
        apply(new InventoryCreatedEvent(createInventoryCommand.getInventoryId(), createInventoryCommand.getProductCode(), createInventoryCommand.getQty()));
    }

    @EventSourcingHandler
    protected void on(InventoryCreatedEvent inventoryCreatedEvent) {
        this.inventoryId = inventoryCreatedEvent.getInventoryId();
        this.qty = inventoryCreatedEvent.getQty();
        this.productCode = inventoryCreatedEvent.getProductCode();
    }
}
