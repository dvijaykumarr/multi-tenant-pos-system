package com.tap.service.impl;

import com.tap.mapper.InventoryMapper;
import com.tap.modal.Branch;
import com.tap.modal.Inventory;
import com.tap.modal.Product;
import com.tap.payload.dto.InventoryDTO;
import com.tap.repository.BranchRepository;
import com.tap.repository.InventoryRepository;
import com.tap.repository.ProductRepository;
import com.tap.service.InventoryService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;

    @Override
    public InventoryDTO createInventory(InventoryDTO inventoryDTO) throws Exception {

        //check both objects are present in database
        Branch branch = branchRepository.findById(inventoryDTO.getBranchId()).orElseThrow(
                () -> new Exception("Branch not exist...")
        );

        Product product = productRepository.findById(inventoryDTO.getProductId()).orElseThrow(
                () -> new Exception("product not exists...")
        );

        Inventory inventory = InventoryMapper.toEntity(inventoryDTO,branch,product);
        Inventory savedInventory = inventoryRepository.save(inventory);
        return InventoryMapper.toDTO(savedInventory);
    }

    @Override
    public InventoryDTO updateInventory(Long id, InventoryDTO inventoryDTO) throws Exception {

        Inventory inventory = inventoryRepository.findById(id).orElseThrow(
                ()->new Exception("Inventory not found...")
        );
        inventory.setQuantity(inventoryDTO.getQuantity());
        Inventory updatedInventory = inventoryRepository.save(inventory);

        return InventoryMapper.toDTO(updatedInventory);
    }

    @Override
    public void deleteInventory(Long id) throws Exception {

        Inventory inventory = inventoryRepository.findById(id).orElseThrow(
                ()->new Exception("Inventory not found...")
        );
        inventoryRepository.delete(inventory);

    }

    @Override
    public InventoryDTO getInventoryById(Long id) throws Exception {

        Inventory inventory = inventoryRepository.findById(id).orElseThrow(
                ()->new Exception("Inventory not found...")
        );
        return InventoryMapper.toDTO(inventory);
    }

    @Override
    public InventoryDTO getInventoryByProductIdAndBranchId(Long productId, Long branchId) {

        Inventory inventory = inventoryRepository.findByProductIdAndBranchId(productId,branchId);


        return InventoryMapper.toDTO(inventory);


    }

    @Override
    public List<InventoryDTO> getAllInventoryByBranchId(Long branchId) {

        List<Inventory> inventories = inventoryRepository.findByBranchId(branchId);
        return inventories.stream().map(
                InventoryMapper::toDTO
        ).collect(Collectors.toList());
    }
}
