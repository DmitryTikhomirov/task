package com.kupreychik.service;

import com.kupreychik.model.dto.TaskStatusDto;
import com.kupreychik.model.entity.TaskStatus;
import com.kupreychik.service.abstracts.BasicService;

import java.util.List;

public interface TaskStatusService extends BasicService<TaskStatusDto, TaskStatus, Long> {

    TaskStatusDto getTaskStatus(String statusName);

    List<String> getNextStatuses(String statusName);
}
