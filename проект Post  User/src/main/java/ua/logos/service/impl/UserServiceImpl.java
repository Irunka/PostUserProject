package ua.logos.service.impl;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.logos.domaim.UserDTO;
import ua.logos.entity.UserEntity;
import ua.logos.exceptions.NotFoundException;
import ua.logos.repository.UserRepository;
import ua.logos.service.UserService;
import ua.logos.utils.ObjectMapperUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapperUtils modelMapper;

    @Override
    public void saveUser(UserDTO user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        userRepository.save(userEntity);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        List<UserDTO> userDTOS = modelMapper.mapAll(users, UserDTO.class);
        return userDTOS;
    }

    @Override
    public UserDTO findUserById(Long id) {
        UserEntity users = userRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("User with id [" + id + "]not found"));
        UserDTO userDTO = modelMapper.map(users, UserDTO.class);

        return userDTO;
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userToUpdate) {
        boolean exists = userRepository.existsById(id);
        UserEntity userFromDB = userRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("User with id [" + id + "]not found"));

        userFromDB.setFirstName(userToUpdate.getFirstName());
        userFromDB.setLastName(userToUpdate.getLastName());
        userFromDB.setNickname(userToUpdate.getNickname());
        userFromDB.setFirstDate(userToUpdate.getFirstDate());
        userRepository.save(userFromDB);
        UserDTO userDTO = modelMapper.map(userFromDB, UserDTO.class);
        userDTO.setId(userFromDB.getId());
        return userDTO;
    }



  /*  private UserDTO entityToDTOMapper(UserEntity userEntity){
        UserDTO userDTO= new UserDTO();
        userDTO.setFirstDate(userEntity.getFirstDate());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setId(userEntity.getId());
        userDTO.setNickname(userEntity.getNickname());

       return  userDTO;
    }
    private  UserEntity dtoToEntityMapper(UserDTO userDTO){
        UserEntity userEntity=new UserEntity();
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setNickname(userDTO.getNickname());
        userEntity.setFirstDate(userDTO.getFirstDate());
        userEntity.setId(userDTO.getId());
        userEntity.setLastName(userDTO.getLastName());

        return  userEntity;
    }*/
}


