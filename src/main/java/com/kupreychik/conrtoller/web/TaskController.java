package com.kupreychik.conrtoller.web;

import com.kupreychik.model.dto.TaskDto;
import com.kupreychik.service.TagService;
import com.kupreychik.service.TaskService;
import com.kupreychik.service.TaskStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Slf4j
@Controller(value = "webTaskController")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final TagService tagService;
    private final TaskStatusService taskStatusService;

    @GetMapping("/")
    public ModelAndView getTasks(ModelAndView modelAndView,
                                 @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                 @RequestParam(value = "sortDir", defaultValue = "ASC", required = false) String sortDir,
                                 @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                                 @RequestParam(value = "page", defaultValue = "0", required = false) int page) {
        var tasks = taskService.findAll(PageRequest.of(page, 10));
        if (!tasks.getContent().isEmpty()) {
            modelAndView.addObject("tasks", tasks.getContent());
            modelAndView.addObject("currentPage", page);
            modelAndView.addObject("totalPages", tasks.getTotalPages());
        } else {
            modelAndView.addObject("tasks", new ArrayList<>());
        }
        modelAndView.addObject("sortBy", sortBy);
        modelAndView.addObject("sortDir", sortDir);
        modelAndView.setViewName("tasks");
        return modelAndView;
    }

    @GetMapping("/tasks/{id}")
    public ModelAndView getTask(@PathVariable(name = "id") Long id, ModelAndView modelAndView) {
        var task = taskService.findById(id);
        modelAndView.addObject("task", task);
        modelAndView.addObject("tags", tagService.findAll());
        modelAndView.addObject("statuses", taskStatusService.getNextStatuses(task.getStat()));
        modelAndView.setViewName("task-form");
        return modelAndView;
    }

    @PostMapping("/tasks")
    public ModelAndView updateTask(@ModelAttribute TaskDto task, ModelAndView modelAndView) {
        taskService.updateTask(task.getId(), task);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
