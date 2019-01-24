package ua.logos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.logos.domaim.UserDetailsDTO;
import ua.logos.entity.UserDetailsEntity;
import ua.logos.service.UserDetailsService;

import java.util.List;

@RestController
@RequestMapping("userDetails")
public class UserDetailsController {

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping
    public ResponseEntity<?>createUserDetail(
            @RequestBody UserDetailsDTO userDetailsDTO
            ){
        System.out.println(
                userDetailsDTO.getBirthDate()+ " "+
                        userDetailsDTO.getEmail()+ " "+
                        userDetailsDTO.getHobby()+ " "+
                        userDetailsDTO.getMaritalStatus()+" "+
                        userDetailsDTO.getTechnologiesExperience()
        );

        userDetailsService.saveUserDetail(userDetailsDTO);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public  ResponseEntity<?>getUserDetails(){
        List<UserDetailsDTO>userDetails=userDetailsService.findAllUserDetails();
        return  new ResponseEntity<>(userDetails,HttpStatus.OK);
    }

    @GetMapping("{userDetailId:[0-9]{1,5}}")
    public ResponseEntity<?>getUserDetailById(@PathVariable("userDetailId")Long id){
        UserDetailsDTO userDetail=userDetailsService.findUserDetailById(id);
        return new ResponseEntity<>(userDetail,HttpStatus.OK);
    }

    @PutMapping("{userDetailId:[0-9]{1,5}}")
    public  ResponseEntity<?>updateUserDetail(
            @PathVariable("userDetailId")Long id,@RequestBody UserDetailsDTO userDetailToUpdate
    ){

        UserDetailsDTO userDetail=userDetailsService.updateUserDetails(id,userDetailToUpdate);

        if(userDetail==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(userDetail,HttpStatus.OK);
    }


}
