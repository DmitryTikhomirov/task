package com.kupreychik.model.dto;

import com.kupreychik.model.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * DTO for {@link Task}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TaskDto implements Serializable, IsDTO {
    private Long id;
    private String title;
    private String description;
    private String createdAt;
    private String stat;
    private String updatedAt;
    private Set<TagTopicDto> tags;
}