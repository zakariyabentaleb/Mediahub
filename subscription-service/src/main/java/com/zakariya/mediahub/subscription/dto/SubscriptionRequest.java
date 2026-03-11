package com.zakariya.mediahub.subscription.dto;

import com.zakariya.mediahub.subscription.enums.Plan;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SubscriptionRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Plan is required")
    private Plan plan;

    private LocalDate startDate;
    private LocalDate endDate;
}
