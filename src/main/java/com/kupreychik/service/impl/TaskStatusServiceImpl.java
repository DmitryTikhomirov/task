package com.kupreychik.service.impl;

import com.kupreychik.mapper.TaskMapper;
import com.kupreychik.mapper.TaskStatusMapper;
import com.kupreychik.model.dto.TaskStatusDto;
import com.kupreychik.model.dto.paginig.PageDTO;
import com.kupreychik.model.entity.TaskStatus;
import com.kupreychik.model.enums.StatusDirection;
import com.kupreychik.repository.TaskStatusRepository;
import com.kupreychik.service.TaskStatusService;
import com.kupreychik.service.abstracts.AbstractService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class TaskStatusServiceImpl extends AbstractService<TaskStatus, TaskStatusDto> implements TaskStatusService {

    private final TaskStatusRepository taskStatusRepository;
    private final TaskStatusMapper taskStatusMapper;


    public TaskStatusServiceImpl(TaskStatusRepository taskStatusRepository, TaskStatusMapper taskStatusMapper) {
        super(taskStatusRepository, taskStatusMapper);
        this.taskStatusRepository = taskStatusRepository;
        this.taskStatusMapper = taskStatusMapper;
    }


    @Override
    public TaskStatus save(TaskStatusDto t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PageDTO<TaskStatusDto> findAll(Pageable pageable) {
        log.info("Finding all tags");
        Page<TaskStatusDto> tags = taskStatusRepository.findAll(pageable)
                .map(taskStatusMapper::fromEntity);
        return new PageDTO<>(tags, pageable);
    }

    @Override
    public void delete(Long aLong) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TaskStatusDto findById(Long aLong) {
        log.info("Finding task status by id");
        var taskStatus = taskStatusRepository.findById(aLong)
                .orElseThrow(() -> new RuntimeException("Task status not found"));
        return taskStatusMapper.fromEntity(taskStatus);
    }

    @Override
    public TaskStatusDto getTaskStatus(String statusName) {
        log.info("Finding status by name {} ", statusName);
        var taskStatus = taskStatusRepository.findByName(statusName)
                .orElseThrow(() -> new RuntimeException("Task status not found"));
        return taskStatusMapper.fromEntity(taskStatus);
    }

    @Override
    public List<String> getNextStatuses(String statusName) {
        List<String> nextStatuses = new ArrayList<>();
        var taskStatus = getTaskStatus(statusName);
        if (taskStatus.getNextStatusName() != null) {
            nextStatuses.add(taskStatus.getNextStatusName());
        }
        if (taskStatus.getPreviousStatusName() != null) {
            nextStatuses.add(taskStatus.getPreviousStatusName());
        }
        nextStatuses.add(statusName);
        return nextStatuses;
    }

}
