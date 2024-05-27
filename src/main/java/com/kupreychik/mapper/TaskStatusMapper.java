package com.kupreychik.mapper;

import com.kupreychik.model.dto.TaskStatusDto;
import com.kupreychik.model.entity.TaskStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskStatusMapper extends AbstractMapper<TaskStatus, TaskStatusDto> {

    @Mapping(source = "nextStatusName", target = "nextStatus.name")
    @Mapping(source = "nextStatusId", target = "nextStatus.id")
    @Mapping(source = "previousStatusName", target = "previousStatus.name")
    @Mapping(source = "previousStatusId", target = "previousStatus.id")
    @Override
    TaskStatus fromDto(TaskStatusDto taskStatusDto);

    @Mapping(source = "nextStatus.name", target = "nextStatusName")
    @Mapping(source = "nextStatus.id", target = "nextStatusId")
    @Mapping(source = "previousStatus.name", target = "previousStatusName")
    @Mapping(source = "previousStatus.id", target = "previousStatusId")
    @Override
    TaskStatusDto fromEntity(TaskStatus t);
}
