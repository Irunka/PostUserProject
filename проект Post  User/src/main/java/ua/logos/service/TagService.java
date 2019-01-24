package ua.logos.service;

import ua.logos.domaim.TagDTO;
import ua.logos.entity.TagEntity;

import java.util.List;

public interface TagService {

    void  saveTag(TagDTO tag);

    List<TagDTO> findAllTags();

    TagDTO findTagById(Long id);

    TagDTO updateTag(Long id,TagDTO tagToUpdate);
}
