package com.kupreychik.model.dto;

import com.kupreychik.model.entity.TaskStatus;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link TaskStatus}
 */
@Value
public class TaskStatusDto implements Serializable, IsDTO {
    Long id;
    String name;
    Long nextStatusId;
    String nextStatusName;
    Long previousStatusId;
    String previousStatusName;
}