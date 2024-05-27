package com.kupreychik.service.impl;

import com.kupreychik.mapper.TaskCommentMapper;
import com.kupreychik.model.dto.TaskCommentDto;
import com.kupreychik.model.dto.paginig.PageDTO;
import com.kupreychik.model.entity.Task;
import com.kupreychik.model.entity.TaskComment;
import com.kupreychik.repository.TaskCommentRepository;
import com.kupreychik.service.TaskCommentService;
import com.kupreychik.service.abstracts.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class TaskCommentServiceImpl extends AbstractService<TaskComment, TaskCommentDto> implements TaskCommentService {

    private final TaskCommentRepository taskCommentRepository;
    private final TaskCommentMapper taskCommentMapper;

    public TaskCommentServiceImpl(TaskCommentRepository taskCommentRepository, TaskCommentMapper taskCommentMapper) {
        super(taskCommentRepository, taskCommentMapper);
        this.taskCommentRepository = taskCommentRepository;
        this.taskCommentMapper = taskCommentMapper;
    }

    @Override
    public void buildInformationTaskComment(Task task, String message) {
        log.info("Creating task comment for message {}", message);
        var taskCommentDTO = buildTaskCommentDTO(message);
        var taskCommentEntity = taskCommentMapper.fromDto(taskCommentDTO);
        taskCommentEntity.setTask(task);
        taskCommentRepository.save(taskCommentEntity);
        log.info("Information task comment created");
    }

    @Override
    public TaskComment save(TaskCommentDto t) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public TaskCommentDto findById(Long aLong) {
        return null;
    }

    @Override
    public PageDTO<TaskCommentDto> findAll(Pageable pageable) {
        return null;
    }

    private TaskCommentDto buildTaskCommentDTO(String message) {
        return TaskCommentDto
                .builder()
                .comment(message)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
