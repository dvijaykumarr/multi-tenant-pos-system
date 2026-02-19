package com.tap.service.impl;

import com.tap.domain.UserRole;
import com.tap.exceptions.UserException;
import com.tap.mapper.CategoryMapper;
import com.tap.modal.Category;
import com.tap.modal.Store;
import com.tap.modal.User;
import com.tap.payload.dto.CategoryDTO;
import com.tap.repository.CategoryRepository;
import com.tap.repository.StoreRepository;
import com.tap.service.CategoryService;
import com.tap.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final StoreRepository storeRepository;

    @Override
    public CategoryDTO createCategory(CategoryDTO dto) throws Exception {

        User user = userService.getCurrentUser();
        Store store = storeRepository.findById(dto.getStoreId()).orElseThrow(
                ()-> new Exception("Store not found")
        );

        Category category = Category.builder()
                .store(store)
                .name(dto.getName())
                .build();

        checkAuthority(user, category.getStore());


        return CategoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDTO> getCategoriesByStore(Long storeId) {
        List<Category> categories = categoryRepository.findByStoreId(storeId);


        return categories.stream().map(
                CategoryMapper::toDto
        ).collect(Collectors.toList());

    }

    @Override
    public CategoryDTO updateCategory(Long storeId, CategoryDTO dto) throws Exception {

        Category category = categoryRepository.findById(storeId).orElseThrow(
                ()->new Exception("Category not exists")
        );

        User user = userService.getCurrentUser();


        category.setName(dto.getName());
        checkAuthority(user, category.getStore());
        return CategoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) throws Exception {

        Category category = categoryRepository.findById(id).orElseThrow(
                ()->new Exception("Category not exists")
        );

        User user = userService.getCurrentUser();
        checkAuthority(user, category.getStore());

        categoryRepository.delete(category);

    }

//    private void checkAuthority(User user , Store store) throws Exception {
//        boolean isAdmin = user.getRole().equals(UserRole.ROLE_STORE_ADMIN);
//        boolean isManager = user.getRole().equals(UserRole.ROLE_STORE_MANAGER);
//        boolean isSameStore = user.equals(store.getStoreAdmin());
//
//        if(!(isAdmin && isSameStore) && !isManager){
//            throw new Exception("You don't have permission to manage this category");
//
//        }
//    }

    private void checkAuthority(User user, Store store) throws Exception {
        boolean isAdmin = user.getRole().equals(UserRole.ROLE_STORE_ADMIN);
        boolean isManager = user.getRole().equals(UserRole.ROLE_STORE_MANAGER);
        boolean isSameStore = store.getStoreAdmin() != null && user.equals(store.getStoreAdmin());

        if (!isManager && !(isAdmin && isSameStore)) {
            throw new Exception("You don't have permission to manage this category");
        }
    }
}
