package com.zakariya.mediahub.media.mapper;

import com.zakariya.mediahub.media.dto.MediaDTO;
import com.zakariya.mediahub.media.dto.MediaRequestDTO;
import com.zakariya.mediahub.media.dto.MediaUpdateDTO;
import com.zakariya.mediahub.media.entity.Media;
import org.jspecify.annotations.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MediaMapper {
    MediaDTO toDto(Media media);
    Media toEntity(MediaRequestDTO dto);
    void updateEntityFromDto(MediaUpdateDTO dto, @MappingTarget Media media);

    List<Media> toDtos(List<@NonNull Media> medias);
}
