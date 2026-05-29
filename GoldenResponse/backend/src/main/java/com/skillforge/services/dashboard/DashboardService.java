package com.skillforge.services.dashboard;

import com.skillforge.dto.response.DashboardOverviewResponse;

public interface DashboardService {
    DashboardOverviewResponse getOverview(Long userId);
}
