package com.kupreychik.conrtoller.rest;

import com.kupreychik.model.dto.TaskStatusDto;
import com.kupreychik.model.dto.paginig.PageDTO;
import com.kupreychik.service.TaskStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.kupreychik.consts.WebConsts.API;

@RestController
@RequestMapping(API + "/tasks/statuses")
@RequiredArgsConstructor
public class TaskStatusesController {

    private final TaskStatusService taskStatusService;

    @GetMapping
    public ResponseEntity<PageDTO<TaskStatusDto>> getTags(@PageableDefault Pageable pageable) {
        return new ResponseEntity<>(taskStatusService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{name}/next")
    public ResponseEntity<TaskStatusDto> getNext(@PathVariable String name) {
        return null;
    }
}
