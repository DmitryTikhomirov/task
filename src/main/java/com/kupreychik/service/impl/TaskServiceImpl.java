package com.kupreychik.service.impl;

import com.kupreychik.mapper.TaskMapper;
import com.kupreychik.model.dto.TagTopicDto;
import com.kupreychik.model.dto.TaskDto;
import com.kupreychik.model.dto.paginig.PageDTO;
import com.kupreychik.model.entity.Tag;
import com.kupreychik.model.entity.TagTopic;
import com.kupreychik.model.entity.Task;
import com.kupreychik.repository.TaskRepository;
import com.kupreychik.service.TagService;
import com.kupreychik.service.TaskService;
import com.kupreychik.service.abstracts.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TaskServiceImpl extends AbstractService<Task, TaskDto> implements TaskService {

    private final TagService tagService;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TagService tagService, TaskRepository taskRepository, TaskMapper taskMapper) {
        super(taskRepository, taskMapper);
        this.tagService = tagService;
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskDto updateTask(Long id, TaskDto taskDto) {
        var curretnTask = taskRepository.findById(id)
                .orElse(null);
        if (curretnTask != null) {
            var updatedTask = taskMapper.updateTask(taskDto, curretnTask);
            updatedTask = taskRepository.save(updatedTask);
            return taskMapper.fromEntity(updatedTask);
        }
        throw new IllegalArgumentException("Task not found");
    }

    @Override
    @Transactional
    public Task save(TaskDto taskDto) {
        log.info("Saving task: {}", taskDto);
        var taskToSave = taskMapper.fromDto(taskDto);
        taskToSave = taskRepository.save(taskToSave);
        return taskToSave;
    }

    @Override
    public PageDTO<TaskDto> findAll(Pageable pageable) {
        log.info("Finding all tasks: {}", pageable);

        Page<Task> tasks = taskRepository.findAll(pageable);
        Long count = tasks.getTotalElements();

        var allTags = tagService.findAll();
        var tasksDTO = new ArrayList<TaskDto>();

        tasks.getContent().forEach(task -> {
            var taskDto = taskMapper.fromEntity(task);
            mapTaskTags(task, taskDto, allTags);
            tasksDTO.add(taskDto);
        });
        return new PageDTO<>(tasksDTO, pageable, count);
    }

    private void mapTaskTags(Task task, TaskDto taskDto, List<TagTopicDto> allTags) {
        var tags = task.getTags();
        if (tags != null) {
            taskDto.setTags(tags.stream()
                    .map(tag -> allTags.stream()
                            .filter(el -> el.getId().equals(tag.getId()))
                            .findFirst()
                            .get())
                    .collect(Collectors.toSet()));
        }
    }

    @Override
    public void delete(Long aLong) {
        log.info("Deleting task: {}", aLong);
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TaskDto findById(Long aLong) {
        log.info("Finding task by id: {}", aLong);
        var task = taskRepository.findById(aLong)
                .map(taskMapper::fromEntity)
                .orElse(null);
        log.info("Found task: {}", task);
        return task;
    }
}
