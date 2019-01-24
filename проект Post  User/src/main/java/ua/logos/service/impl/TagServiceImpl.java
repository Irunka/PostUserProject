package ua.logos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.logos.domaim.TagDTO;
import ua.logos.entity.TagEntity;
import ua.logos.exceptions.NotFoundException;
import ua.logos.repository.TagRepository;
import ua.logos.service.TagService;
import ua.logos.utils.ObjectMapperUtils;

import javax.swing.text.html.HTML;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl  implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ObjectMapperUtils modelMapper;

    @Override
    public void saveTag(TagDTO tag) {
        TagEntity tagEntity = modelMapper.map(tag, TagEntity.class);
        tagRepository.save(tagEntity);
    }

    @Override
    public List<TagDTO> findAllTags() {
        List<TagEntity> tags = tagRepository.findAll();
        List<TagDTO> tagDTOS = modelMapper.mapAll(tags, TagDTO.class);
        return tagDTOS;
    }

    @Override
    public TagDTO findTagById(Long id) {
        TagEntity tags = tagRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Tag with id [" + id + "]not found"));
        TagDTO tagDTO = modelMapper.map(tags, TagDTO.class);

        return tagDTO;
    }

    @Override
    public TagDTO updateTag(Long id, TagDTO tagToUpdate) {
        boolean exists = tagRepository.existsById(id);
        TagEntity tagFromDB = tagRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Tag with id [" + id + "]not found"));

        tagFromDB.setName(tagToUpdate.getName());
        tagRepository.save(tagFromDB);
        TagDTO tagDTO = modelMapper.map(tagFromDB, TagDTO.class);
        tagDTO.setId(tagFromDB.getId());
        return tagDTO;
 /*private  TagDTO entityToDTOMapper(TagEntity tagEntity){

        TagDTO tagDTO=new TagDTO();
  tagDTO.setId(tagEntity.getId());
  tagDTO.setName(tagEntity.getName());

  return  tagDTO;
 }
private TagEntity dtoToEntityMapper(TagDTO tagDTO){
        TagEntity tagEntity=new TagEntity();
        tagEntity.setId(tagDTO.getId());
        tagEntity.setName(tagDTO.getName());
        tagEntity.setName(tagDTO.getName());

        return  tagEntity;
*/
    }
}










