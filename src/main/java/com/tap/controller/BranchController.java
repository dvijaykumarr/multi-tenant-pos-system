package com.tap.controller;


import com.tap.configuration.AccessRules;
import com.tap.exceptions.UserException;
import com.tap.payload.dto.BranchDTO;
import com.tap.payload.response.ApiResponse;
import com.tap.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;


    @PostMapping()
    @PreAuthorize(AccessRules.STORE_LEVEL)   // createBranch
    public ResponseEntity<BranchDTO> createBranch(@RequestBody BranchDTO branchDTO) throws UserException {

        BranchDTO createdBranch = branchService.createBranch(branchDTO);

        return ResponseEntity.ok(createdBranch);
    }

    @GetMapping("/{id}")
    @PreAuthorize(AccessRules.STORE_LEVEL)   // getBranchById
    public ResponseEntity<BranchDTO> getBranchById(@PathVariable Long id) throws Exception {

        BranchDTO createdBranch = branchService.getBranchById(id);

        return ResponseEntity.ok(createdBranch);
    }

    @GetMapping("/store/{storeId}")
    @PreAuthorize(AccessRules.STORE_LEVEL)   // getAllBranchesByStoreId
    public ResponseEntity<List<BranchDTO>> getAllBranchesByStoreId(@PathVariable Long storeId) throws Exception {

        List<BranchDTO> createdBranch = branchService.getAllBranchesByStoreId(storeId);
        return ResponseEntity.ok(createdBranch);
    }


    @PutMapping("/{id}")
    @PreAuthorize(AccessRules.STORE_LEVEL)   // updateBranch
    public ResponseEntity<BranchDTO> updateBranch(@PathVariable Long id,
                                                  @RequestBody BranchDTO branchDTO) throws Exception {

        BranchDTO createdBranch = branchService.updateBranch(id,branchDTO);

        return ResponseEntity.ok(createdBranch);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(AccessRules.STORE_LEVEL)   // deleteBranch
    public ResponseEntity<ApiResponse> deleteBranch(@PathVariable Long id) throws Exception {

       branchService.deleteBranch(id);

       ApiResponse apiResponse = new ApiResponse();
       apiResponse.setMessage("Branch deleted successfully");

        return ResponseEntity.ok(apiResponse);
    }




}
