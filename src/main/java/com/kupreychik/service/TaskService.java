package com.kupreychik.service;

import com.kupreychik.model.dto.TaskDto;
import com.kupreychik.model.entity.Task;
import com.kupreychik.service.abstracts.BasicService;

public interface TaskService extends BasicService<TaskDto, Task, Long> {

    TaskDto updateTask(Long id, TaskDto taskDto);
}
