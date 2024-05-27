package com.kupreychik.mapper;

import com.kupreychik.model.dto.TagDto;
import com.kupreychik.model.dto.TagTopicDto;
import com.kupreychik.model.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper extends AbstractMapper<Tag, TagDto> {

    @Override
    Tag fromDto(TagDto tagDto);

    @Override
    TagDto fromEntity(Tag t);

    @Mapping(target = "tagTopicId", source = "tagTopic.id")
    @Mapping(target = "tagTopicName", source = "tagTopic.name")
    @Mapping(target = "tagTopicColor", source = "tagTopic.color")
    TagTopicDto mapToTagTopicDto(Tag tag);
}
