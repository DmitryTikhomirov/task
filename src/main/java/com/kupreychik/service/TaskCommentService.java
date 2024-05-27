package com.kupreychik.service;

import com.kupreychik.model.dto.TaskCommentDto;
import com.kupreychik.model.entity.Task;
import com.kupreychik.model.entity.TaskComment;
import com.kupreychik.service.abstracts.BasicService;

public interface TaskCommentService extends BasicService<TaskCommentDto, TaskComment, Long> {

    void buildInformationTaskComment(Task task, String message);
}
