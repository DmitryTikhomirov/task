package com.kupreychik.listeners;

import com.kupreychik.model.entity.Task;
import com.kupreychik.service.TaskCommentService;
import lombok.RequiredArgsConstructor;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
public class TaskEntityListener implements PostInsertEventListener {

    private final TaskCommentService taskCommentService;

    @Override
    public void onPostInsert(PostInsertEvent postInsertEvent) {
        taskCommentService.buildInformationTaskComment((Task) postInsertEvent.getEntity(), "Создана задача");

    }

    @Override
    public boolean requiresPostCommitHandling(EntityPersister entityPersister) {
        return false;
    }
}