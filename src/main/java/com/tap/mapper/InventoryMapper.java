package com.tap.mapper;


import com.tap.modal.Branch;
import com.tap.modal.Inventory;
import com.tap.modal.Product;
import com.tap.payload.dto.InventoryDTO;

public class InventoryMapper {

    public static InventoryDTO toDTO(Inventory inventory){
        return InventoryDTO.builder()
                .id(inventory.getId())
                .branchId(inventory.getBranch().getId())
                .branch(BranchMapper.toDTO(inventory.getBranch())) // ← add this
                .productId(inventory.getProduct().getId())
                .product(ProductMapper.toDTO(inventory.getProduct()))
                .quantity(inventory.getQuantity())
                .lastUpdated(inventory.getLastUpdated())  // ← add this
                .build();
    }

    public static Inventory toEntity(InventoryDTO inventoryDTO, Branch branch, Product product){
        return Inventory.builder()
                .branch(branch)
                .product(product)
                .quantity(inventoryDTO.getQuantity())
                .build();
    }
}
