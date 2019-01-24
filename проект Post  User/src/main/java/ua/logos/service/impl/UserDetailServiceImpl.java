package ua.logos.service.impl;


import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.logos.domaim.UserDetailsDTO;
import ua.logos.entity.UserDetailsEntity;
import ua.logos.exceptions.NotFoundException;
import ua.logos.repository.UserDetailsRepository;
import ua.logos.service.UserDetailsService;
import ua.logos.utils.ObjectMapperUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl  implements UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private ObjectMapperUtils modelMapper;

    @Override
    public void saveUserDetail(UserDetailsDTO userDetail) {
        UserDetailsEntity userDetailsEntity = modelMapper.map(userDetail,UserDetailsEntity.class);
        userDetailsRepository.save(userDetailsEntity);
    }

    @Override
    public List<UserDetailsDTO> findAllUserDetails() {
        List<UserDetailsEntity>userDetails=userDetailsRepository.findAll();
    List<UserDetailsDTO>userDetailsDTOS=modelMapper.mapAll(userDetails,UserDetailsDTO.class);
    return userDetailsDTOS;
    }

    @Override
    public UserDetailsDTO findUserDetailById(Long id) {
        UserDetailsEntity userDetails=userDetailsRepository.findById(id)
                .orElseThrow(
                        ()-> new NotFoundException("UserDetail with id ["+ id+"]not found"));
        UserDetailsDTO userDetailsDTO = modelMapper.map(userDetails,UserDetailsDTO.class);

        return userDetailsDTO;
    }

    @Override
    public UserDetailsDTO updateUserDetails(Long id, UserDetailsDTO userDetailsToUpdate) {
        boolean exists=userDetailsRepository.existsById(id);
        UserDetailsEntity userDetailsFromDB=userDetailsRepository.findById(id)
                .orElseThrow(
                        ()-> new NotFoundException("UserDetail with id ["+ id+"]not found"));

        userDetailsFromDB.setTechnologiesExperience(userDetailsToUpdate.getTechnologiesExperience());
        userDetailsFromDB.setMaritalStatus(userDetailsToUpdate.getMaritalStatus());
        userDetailsFromDB.setHobby(userDetailsToUpdate.getHobby());
        userDetailsFromDB.setEmail(userDetailsToUpdate.getEmail());
        userDetailsFromDB.setBirthDate(userDetailsToUpdate.getBirthDate());
        userDetailsFromDB.setUser(userDetailsToUpdate.getUser());
        userDetailsRepository.save(userDetailsFromDB);
        UserDetailsDTO userDetailsDTO = modelMapper.map(userDetailsFromDB,UserDetailsDTO.class);
userDetailsDTO.setId(userDetailsFromDB.getId());
return  userDetailsDTO;
    }
   // private  UserDetailsDTO entityToDTOMapper(UserDetailsEntity userDetailsEntity){
   /*     UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setId(userDetailsEntity.getId());
        userDetailsDTO.setBirthDate(userDetailsEntity.getBirthDate());
        userDetailsDTO.setEmail(userDetailsEntity.getEmail());
        userDetailsDTO.setHobby(userDetailsEntity.getHobby());
        userDetailsDTO.setMaritalStatus(userDetailsEntity.getMaritalStatus());
        userDetailsDTO.setTechnologiesExperience(userDetailsEntity.getTechnologiesExperience());
        userDetailsDTO.setUser(userDetailsEntity.getUser());

        return  userDetailsDTO;
    }
    private  UserDetailsEntity dtoToEntityMapper(UserDetailsDTO userDetailsDTO){
        UserDetailsEntity userDetailsEntity=new UserDetailsEntity();
        userDetailsEntity.setId(userDetailsDTO.getId());
        userDetailsEntity.setUser(userDetailsDTO.getUser());
        userDetailsEntity.setBirthDate(userDetailsDTO.getBirthDate());
        userDetailsEntity.setEmail(userDetailsDTO.getEmail());
        userDetailsEntity.setHobby(userDetailsDTO.getHobby());
        userDetailsEntity.setMaritalStatus(userDetailsDTO.getMaritalStatus());
        userDetailsEntity.setTechnologiesExperience(userDetailsDTO.getTechnologiesExperience());

        return  userDetailsEntity;
    }*/


}
