package com.example.inventory.command;

import com.axon.command.CreateInventoryCommand;
import com.example.inventory.model.Inventory;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class InventoryCommandServiceImpl implements InventoryCommandService {
    private final CommandGateway commandGateway;

    public InventoryCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<UUID> createInventory(Inventory inventory) {
        return commandGateway.send(
                new CreateInventoryCommand(UUID.randomUUID(), inventory.getProductCode(), inventory.getQty())
        );
    }
}
