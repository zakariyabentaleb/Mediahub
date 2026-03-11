package com.zakariya.mediahub.media.dto;

import com.zakariya.mediahub.media.enums.MediaType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MediaRequestDTO {

    @NotBlank(message = "Title is mandatory and cannot be empty")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    private String title;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @NotNull(message = "Media type (MOVIE, SERIES, PODCAST) is required")
    private MediaType type;

    @NotBlank(message = "Genre is mandatory")
    private String genre;

    @NotBlank(message = "Category is mandatory")
    private String category;
}
