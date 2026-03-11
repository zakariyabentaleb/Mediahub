package com.zakariya.mediahub.media.service.impl;

import com.zakariya.mediahub.media.dto.MediaDTO;
import com.zakariya.mediahub.media.dto.MediaRequestDTO;
import com.zakariya.mediahub.media.dto.MediaUpdateDTO;
import com.zakariya.mediahub.media.dto.responce.PaginationDTO;
import com.zakariya.mediahub.media.entity.Media;
import com.zakariya.mediahub.media.mapper.MediaMapper;
import com.zakariya.mediahub.media.repository.MediaRepository;
import com.zakariya.mediahub.media.service.interfaces.MediaService;
import jakarta.persistence.criteria.Predicate;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MediaServiceImpl implements MediaService {
    private final MediaRepository repository;
    private final MediaMapper mapper;

    @Override
    @Transactional
    public MediaDTO create(@NonNull MediaRequestDTO dto) {
        Media media = mapper.toEntity(dto);
        repository.save(media);
        return mapper.toDto(media);
    }

    @Override
    @Transactional
    public MediaDTO update(@NonNull Long id, @NonNull MediaUpdateDTO dto) {
        Media media = findMedia(id);
        mapper.updateEntityFromDto(dto, media);
        return mapper.toDto(media);
    }

    @Override
    public PaginationDTO getAll(String genre, String category, @NonNull Pageable pageable) {
        var spec = getSpecification(genre, category);

        Page<@NonNull Media> mediaPage = repository.findAll(spec, pageable);

        return PaginationDTO.builder()
                .content(mapper.toDtos(mediaPage.getContent()))
                .page(mediaPage.getNumber())
                .size(mediaPage.getSize())
                .totalElements(mediaPage.getTotalElements())
                .totalPages(mediaPage.getTotalPages())
                .isFirst(mediaPage.isFirst())
                .isLast(mediaPage.isLast())
                .build();
    }

    @Override
    public MediaDTO get(@NonNull Long id) {
        return mapper.toDto(findMedia(id));
    }

    @Override
    @Transactional
    public void delete(@NonNull Long id) {
        Media media = findMedia(id);
        repository.delete(media);
    }

    private Media findMedia(@NonNull Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Media not found with id: " + id));
    }

    private Specification<@NonNull Media> getSpecification(String genre, String category) {
        return (root, query, cb) -> {
            query.distinct(true);

            List<Predicate> predicates = new ArrayList<>();

            if (genre != null) {
                String pattern = "%" + genre.toLowerCase() + "%";
                predicates.add(cb.like(cb.lower(root.get("genre")), pattern));
            }

            if (category != null) {
                String pattern = "%" + category.toLowerCase() + "%";
                predicates.add(cb.like(cb.lower(root.get("category")), pattern));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
