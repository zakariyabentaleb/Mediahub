package com.zakariya.mediahub.subscription.entity;

import com.zakariya.mediahub.subscription.enums.Plan;
import com.zakariya.mediahub.subscription.enums.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "subscriptions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // We store only the userId (a Long), NOT a @ManyToOne User object.
    // Why? Because User lives in a completely different service and database.
    // In microservices, services do NOT share a database — each owns its data.
    private Long userId;

    @Enumerated(EnumType.STRING)
    private Plan plan;

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;

    private LocalDate startDate;
    private LocalDate endDate;
}
