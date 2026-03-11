package com.zakariya.mediahub.subscription.dto;

import lombok.Data;

@Data
public class MediaDto {

    private Long id;
    private String title;
    private String description;
    private String type;
    private String genre;
    private String category;
}
