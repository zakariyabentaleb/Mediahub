package com.zakariya.mediahub.media.dto;

import com.zakariya.mediahub.media.enums.MediaType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MediaDTO {
    private Long id;
    private String title;
    private String description;
    private MediaType type;
    private String genre;
    private String category;
}
