package com.kupreychik.service.impl;

import com.kupreychik.mapper.TagMapper;
import com.kupreychik.model.dto.TagDto;
import com.kupreychik.model.dto.TagTopicDto;
import com.kupreychik.model.dto.paginig.PageDTO;
import com.kupreychik.model.entity.Tag;
import com.kupreychik.repository.TagRepository;
import com.kupreychik.service.TagService;
import com.kupreychik.service.abstracts.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class TagsServiceImpl extends AbstractService<Tag, TagDto> implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;
    private final Map<String, Tag> tagMap = new ConcurrentHashMap<>();

    public TagsServiceImpl(TagRepository tagRepository, TagMapper tagMapper) {
        super(tagRepository, tagMapper);
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;

        cacheTags(tagRepository);
    }

    @Override
    public Tag save(TagDto t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PageDTO<TagDto> findAll(Pageable pageable) {
        log.info("Finding all tags");
        Page<TagDto> tags = tagRepository.findAll(pageable)
                .map(tagMapper::fromEntity);
        return new PageDTO<>(tags, pageable);
    }

    @Override
    public Tag findByName(String tag) {
        return tagMap.get(tag);
    }

    @Override
    public void delete(Long aLong) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TagDto findById(Long aLong) {
        log.info("Finding tag by id {}", aLong);
        var tag = tagRepository.findById(aLong)
                .map(tagMapper::fromEntity)
                .orElse(null);
        log.info("Found tag {}", tag);
        return tag;
    }

    @Override
    public List<TagTopicDto> findAll() {
        return tagRepository.findAll()
                .stream().map(tagMapper::mapToTagTopicDto)
                .toList();
    }

    private void cacheTags(TagRepository tagRepository) {
        var allTags = tagRepository.findAll();
        for (var allTag : allTags) {
            tagMap.put(allTag.getName(), allTag);
        }
        log.info("Successfully loaded {} tags", allTags.size());
    }
}
