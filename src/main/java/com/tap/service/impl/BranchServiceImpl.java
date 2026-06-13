package com.tap.service.impl;

import com.tap.exceptions.UserException;
import com.tap.mapper.BranchMapper;
import com.tap.modal.Branch;
import com.tap.modal.Store;
import com.tap.modal.User;
import com.tap.payload.dto.BranchDTO;
import com.tap.repository.BranchRepository;
import com.tap.repository.StoreRepository;
import com.tap.repository.UserRepository;
import com.tap.service.BranchService;
import com.tap.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final StoreRepository storeRepository;
    private final UserService userService;


    @Override
    public BranchDTO createBranch(BranchDTO branchDTO) throws UserException {

        User currentUser = userService.getCurrentUser();

        Store store = storeRepository.findById(branchDTO.getStoreId())
                .orElseThrow(() -> new UserException("Store not found"));

        try {
            checkAuthority(currentUser, store);
        } catch (Exception e) {
            throw new UserException(e.getMessage());
        }

        Branch branch = BranchMapper.toEntity(branchDTO, store);

        Branch savedBranch = branchRepository.save(branch);

        return BranchMapper.toDTO(savedBranch);
    }

    @Override
    public BranchDTO updateBranch(Long id, BranchDTO branchDTO) throws Exception {

        Branch existing = branchRepository.findById(id)
                .orElseThrow(() -> new Exception("Branch not exist..."));

        User currentUser = userService.getCurrentUser();

        checkAuthority(currentUser, existing.getStore());

        existing.setName(branchDTO.getName());
        existing.setWorkingDays(branchDTO.getWorkingDays());
        existing.setEmail(branchDTO.getEmail());
        existing.setPhone(branchDTO.getPhone());
        existing.setAddress(branchDTO.getAddress());
        existing.setOpenTime(branchDTO.getOpenTime());
        existing.setCloseTime(branchDTO.getCloseTime());
        existing.setUpdatedAt(LocalDateTime.now());

        Branch updatedBranch = branchRepository.save(existing);

        return BranchMapper.toDTO(updatedBranch);
    }

    @Override
    public void deleteBranch(Long id) throws Exception {

        Branch existing = branchRepository.findById(id)
                .orElseThrow(() -> new Exception("Branch not exist..."));

        User currentUser = userService.getCurrentUser();

        checkAuthority(currentUser, existing.getStore());

        branchRepository.delete(existing);
    }

    @Override
    public BranchDTO getBranchById(Long id) throws Exception {

        Branch existing = branchRepository.findById(id)
                .orElseThrow(() -> new Exception("Branch not exist..."));

        User currentUser = userService.getCurrentUser();

        checkAuthority(currentUser, existing.getStore());

        return BranchMapper.toDTO(existing);
    }

    @Override
    public List<BranchDTO> getAllBranchesByStoreId(Long storeId) throws Exception {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new Exception("Store not found"));

        User currentUser = userService.getCurrentUser();

        checkAuthority(currentUser, store);

        List<Branch> branches = branchRepository.findByStoreId(storeId);

        return branches.stream()
                .map(BranchMapper::toDTO)
                .collect(Collectors.toList());
    }

    private void checkAuthority(User user, Store store) throws Exception {

        boolean isStoreAdmin =
                user.getRole().name().equals("ROLE_STORE_ADMIN");

        boolean isStoreManager =
                user.getRole().name().equals("ROLE_STORE_MANAGER");

        boolean isSameStore =
                store.getStoreAdmin() != null &&
                        store.getStoreAdmin().getId().equals(user.getId());

        if (!(isStoreAdmin && isSameStore) && !isStoreManager) {
            throw new Exception("You don't have permission to manage this branch");
        }
    }
}
