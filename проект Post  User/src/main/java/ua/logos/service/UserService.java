package ua.logos.service;

import ua.logos.domaim.UserDTO;
import ua.logos.entity.UserEntity;

import java.util.List;

public interface UserService {

    void saveUser(UserDTO user);

    List<UserDTO> findAllUsers();

    UserDTO findUserById(Long id);

UserDTO updateUser(Long id,UserDTO userToUpdate);

}
