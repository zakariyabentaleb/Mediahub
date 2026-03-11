package com.zakariya.mediahub.media.entity;

import com.zakariya.mediahub.media.enums.MediaType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medias")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private MediaType type;

    private String genre;
    private String category;
}
