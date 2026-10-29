package com.tap.service.impl;

import com.tap.domain.StoreStatus;
import com.tap.payload.adminAnalytics.DashBoardSummaryDTO;
import com.tap.payload.adminAnalytics.StoreRegistrationStateDTO;
import com.tap.payload.adminAnalytics.StoreStatusDistributionDTO;
import com.tap.repository.StoreRepository;
import com.tap.service.AdminDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class AdminDashboardServiceImpl implements AdminDashboardService {

    private final StoreRepository storeRepository;

    @Override
    public DashBoardSummaryDTO getDashboardSummary() {

        Long total = storeRepository.count();
        Long active = storeRepository.countByStatus(StoreStatus.ACTIVE);
        Long pending = storeRepository.countByStatus(StoreStatus.PENDING);
        Long blocked = storeRepository.countByStatus(StoreStatus.BLOCKED);


        return DashBoardSummaryDTO.builder()
                .totalStore(total)
                .activeStore(active)
                .pendingStore(pending)
                .blockedStore(blocked)
                .build();
    }

    @Override
    public List<StoreRegistrationStateDTO> getLast7DaysRegistrationStats() {

        LocalDateTime sevenDaysAgo = LocalDate.now()
                .minusDays(6)
                .atStartOfDay();

        List<Object[]> rawStats = storeRepository.getStoreRegistrationStats(sevenDaysAgo);

        Map<String, Long> dataMap = new LinkedHashMap<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate startDate = LocalDate.now().minusDays(6);

        for (int i = 0; i < 7; i++) {
            LocalDate date = startDate.plusDays(i);
            dataMap.put(date.toString(), 0L);
        }

        for (Object[] row : rawStats) {

            LocalDate date = ((java.sql.Date) row[0]).toLocalDate();
            Long count = ((Number) row[1]).longValue();

            dataMap.put(date.toString(), count);
        }

        List<StoreRegistrationStateDTO> result = new ArrayList<>();
        dataMap.forEach((date, count)->result.add(
                StoreRegistrationStateDTO.builder()
                        .date(date)
                        .count(count)
                        .build()
        ));
        return result;
    }

    @Override
    public StoreStatusDistributionDTO getStoreStatusDistribution() {

        Long active = storeRepository.countByStatus(StoreStatus.ACTIVE);
        Long pending = storeRepository.countByStatus(StoreStatus.PENDING);
        Long blocked = storeRepository.countByStatus(StoreStatus.BLOCKED);


        return StoreStatusDistributionDTO.builder()
                .active(active)
                .pending(pending)
                .blocked(blocked)
                .build();
    }
}
