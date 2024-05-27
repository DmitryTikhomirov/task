package com.kupreychik.mapper;

import com.kupreychik.model.dto.TagTopicDto;
import com.kupreychik.model.dto.TaskDto;
import com.kupreychik.model.entity.Tag;
import com.kupreychik.model.entity.Task;
import com.kupreychik.model.entity.TaskStatus;
import com.kupreychik.repository.TagRepository;
import com.kupreychik.repository.TaskStatusRepository;
import com.kupreychik.service.TagService;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TaskMapper implements AbstractMapper<Task, TaskDto> {

    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @Autowired
    private TagService tagService;

    @Override
   // @Mapping(source = "tags", target = "tags", qualifiedByName = "stringTagsToTags")
    @Mapping(target = "tags", ignore = true)
    @Mapping(source = "stat", target = "status", qualifiedByName = "stringToTaskStatus")
    public abstract Task fromDto(TaskDto taskDTO);

    @Override
    //@Mapping(target = "tags", source = "tags", qualifiedByName = "tagsToSetStringTags")
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "stat", source = "status.name")
    public abstract TaskDto fromEntity(Task t);


    //@Mapping(source = "tags", target = "tags", qualifiedByName = "stringTagsToTags")
    @Mapping(target = "tags", ignore = true)
    @Mapping(source = "stat", target = "status", qualifiedByName = "stringToTaskStatus")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    public abstract Task updateTask(TaskDto taskDto, @MappingTarget Task t);

    @Named("stringToTaskStatus")
    public TaskStatus stringToTaskStatus(String statusName) {
        return taskStatusRepository.findByName(statusName)
                .orElseThrow(() -> new IllegalArgumentException("Task status not found: " + statusName));
    }

    @Named("stringTagsToTags")
    public Set<Tag> tagsToTags(Set<String> tags) {
        HashSet<Tag> tagSet = new HashSet<>();
        for (String tag : tags) {
            tagSet.add(tagService.findByName(tag));
        }
        return tagSet;
    }
}
