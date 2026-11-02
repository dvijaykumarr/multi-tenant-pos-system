package com.tap.controller;


import com.tap.configuration.AccessRules;
import com.tap.payload.dto.InventoryDTO;
import com.tap.payload.response.ApiResponse;
import com.tap.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/inventories")
public class InventoryController {


    private final InventoryService inventoryService;

    @PostMapping()
    @PreAuthorize(AccessRules.BRANCH_LEVEL)  // create
    public ResponseEntity<InventoryDTO> create(@RequestBody InventoryDTO inventoryDTO) throws Exception {

        return ResponseEntity.ok(inventoryService.createInventory(inventoryDTO));

    }

    @PutMapping("/{id}")
    @PreAuthorize(AccessRules.BRANCH_LEVEL)  // update
    public ResponseEntity<InventoryDTO> update(@RequestBody InventoryDTO inventoryDTO,
                                               @PathVariable Long id) throws Exception {

        return ResponseEntity.ok(inventoryService.updateInventory(id, inventoryDTO));

    }

    @DeleteMapping("/{id}")
    @PreAuthorize(AccessRules.BRANCH_LEVEL)  // delete
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) throws Exception {

        inventoryService.deleteInventory(id);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Deleted Inventory...");

        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/branch/{branchId}/product/{productId}")
    @PreAuthorize(AccessRules.ALL_STAFF) // getInventoryByProductAndBranchId
    public ResponseEntity<InventoryDTO> getInventoryByProductAndBranchId(
            @PathVariable Long productId,
            @PathVariable Long branchId)

            throws Exception {

        return ResponseEntity.ok(inventoryService.getInventoryByProductIdAndBranchId(productId,branchId));

    }

    @GetMapping("/branch/{branchId}")
    @PreAuthorize(AccessRules.ALL_STAFF)     // getInventoryByBranch
    public ResponseEntity<List<InventoryDTO>> getInventoryByBranch(@PathVariable Long branchId) throws Exception {

        return ResponseEntity.ok(inventoryService.getAllInventoryByBranchId(branchId));

    }




}
