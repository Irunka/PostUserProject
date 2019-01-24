package ua.logos.service;

import ua.logos.domaim.UserDetailsDTO;
import ua.logos.entity.UserDetailsEntity;

import java.util.List;

public interface UserDetailsService {

void saveUserDetail(UserDetailsDTO userDetail) ;

List<UserDetailsDTO> findAllUserDetails();

UserDetailsDTO findUserDetailById(Long id);

UserDetailsDTO updateUserDetails(Long id,UserDetailsDTO userDetailsToUpdate);

}
