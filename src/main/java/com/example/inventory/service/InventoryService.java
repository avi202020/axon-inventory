package com.example.inventory.service;

import com.example.inventory.model.Inventory;

import java.util.Optional;

public interface InventoryService {
    Inventory addInventory(Inventory inventory);
    Optional<Inventory> getInventoryByProductCode(String pCode);
}
