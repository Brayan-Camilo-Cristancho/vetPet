package com.vetpet.apprest.controller;

import com.vetpet.apprest.domain.dto.UserDto;
import com.vetpet.apprest.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUser() {
        return userService.userGetAll();
    }

    @PostMapping("save-customer")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto) {
        return userService.userSave(userDto);
    }

    @PatchMapping("/update-other")
    public ResponseEntity<?> updateUserOther(@RequestBody UserDto userDto) {
        return userService.userUpdateOther(userDto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam String username) {
        return userService.userDelete(username);
    }

    @GetMapping("/by-sex/{sex}")
    public ResponseEntity<?> getBySexUser(@PathVariable Character sex) {
        return userService.userGetBySex(sex);
    }

    @GetMapping("/by-information")
    public ResponseEntity<?> getByEmailOrIdentificationUser(@RequestParam String email, @RequestParam String identification) {
        return userService.userGetByEmailOrIdentification(email, identification);
    }

    @GetMapping("/by-phone/{phone}")
    public ResponseEntity<?> getByPhoneUser(@PathVariable String phone) {
        return userService.userGetByPhone(phone);
    }

    @GetMapping("/all-page")
    public ResponseEntity<?> getAllPage(@RequestParam Integer page, @RequestParam Integer size) {
        return userService.userGetAllPage(page, size);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto) {
        return userService.userUpdate(userDto);

    }
}
