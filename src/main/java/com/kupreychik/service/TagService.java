package com.kupreychik.service;

import com.kupreychik.model.dto.TagDto;
import com.kupreychik.model.entity.Tag;
import com.kupreychik.model.dto.TagTopicDto;
import com.kupreychik.service.abstracts.BasicService;

import java.util.List;

public interface TagService extends BasicService<TagDto, Tag, Long> {

    List<TagTopicDto> findAll();

    Tag findByName(String tag);
}
