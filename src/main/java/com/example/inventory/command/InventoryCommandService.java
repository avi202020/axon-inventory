package com.example.inventory.command;

import com.example.inventory.model.Inventory;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface InventoryCommandService {
    CompletableFuture<UUID> createInventory(Inventory inventory);
}
