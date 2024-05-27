package com.kupreychik.conrtoller.rest;

import com.kupreychik.model.dto.TaskDto;
import com.kupreychik.model.dto.paginig.PageDTO;
import com.kupreychik.model.entity.Task;
import com.kupreychik.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.kupreychik.consts.WebConsts.API;


@RestController
@RequestMapping(API + "/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public PageDTO<TaskDto> getPeople(@PageableDefault(size = 100, page = 0) Pageable pageable) {
        return taskService.findAll(pageable);
    }

    @PostMapping
    public Task save(@RequestBody TaskDto taskDto) {
        return taskService.save(taskDto);
    }
}
