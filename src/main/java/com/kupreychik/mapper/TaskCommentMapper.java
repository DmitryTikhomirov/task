package com.kupreychik.mapper;

import com.kupreychik.model.dto.TaskCommentDto;
import com.kupreychik.model.entity.TaskComment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskCommentMapper extends AbstractMapper<TaskComment, TaskCommentDto> {
    @Override
    TaskComment fromDto(TaskCommentDto taskCommentDto);

    @Override
    TaskCommentDto fromEntity(TaskComment t);
}
