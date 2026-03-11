package com.zakariya.mediahub.media.controller;

import com.zakariya.mediahub.media.dto.MediaDTO;
import com.zakariya.mediahub.media.dto.MediaRequestDTO;
import com.zakariya.mediahub.media.dto.MediaUpdateDTO;
import com.zakariya.mediahub.media.dto.responce.PaginationDTO;
import com.zakariya.mediahub.media.dto.responce.StandardResponseDTO;
import com.zakariya.mediahub.media.service.interfaces.MediaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaController {
    private final MediaService service;

    @PostMapping
    public ResponseEntity<@NonNull StandardResponseDTO<@NonNull MediaDTO>> create(
            @RequestBody @Valid MediaRequestDTO dto,
            HttpServletRequest request
    ) {
        MediaDTO result = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(buildResponse(
                "Media created successfully",
                HttpStatus.CREATED,
                request, result
        ));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<@NonNull StandardResponseDTO<@NonNull MediaDTO>> update(
            @PathVariable Long id,
            @RequestBody MediaUpdateDTO dto,
            HttpServletRequest request
    ) {
        MediaDTO result = service.update(id, dto);
        return ResponseEntity.ok(buildResponse(
                "The media was successfully updated",
                HttpStatus.OK,
                request, result
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<@NonNull StandardResponseDTO<@NonNull MediaDTO>> find(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        MediaDTO result = service.get(id);
        return ResponseEntity.ok(buildResponse(
                "Media retrieved successfully",
                HttpStatus.OK,
                request, result
        ));
    }

    @GetMapping
    public ResponseEntity<@NonNull StandardResponseDTO<@NonNull PaginationDTO>> all(
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String category,
            Pageable pageable,
            HttpServletRequest request
    ) {
        PaginationDTO result = service.getAll(genre, category, pageable);
        return ResponseEntity.ok(buildResponse(
                "Media list fetched successfully",
                HttpStatus.OK,
                request, result
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<@NonNull StandardResponseDTO<Void>> delete(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        service.delete(id);
        return ResponseEntity.ok(buildResponse(
                "Media deleted successfully",
                HttpStatus.OK,
                request, null
        ));
    }

    private <T> StandardResponseDTO<T> buildResponse(String message, HttpStatus status, HttpServletRequest request, T data) {
        return StandardResponseDTO.<T>builder()
                .timestamp(LocalDateTime.now())
                .message(message)
                .status(status.value())
                .path(request.getServletPath())
                .data(data)
                .build();
    }
}
