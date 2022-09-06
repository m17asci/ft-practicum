package com.practium.FT.controller;

import com.practium.FT.dto.request.UserRequestDTO;
import com.practium.FT.dto.response.UserResponseDTO;
import com.practium.FT.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAllUser() {
        return ResponseEntity.ok(userService.findAllUser());
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));

    }

   @PostMapping
   public ResponseEntity<UserResponseDTO>createOneUser(@RequestBody UserRequestDTO userRequestDTO){
        return  ResponseEntity.ok(userService.createOneUser(userRequestDTO));
   }


    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO newUser) {
        return ResponseEntity.ok(userService.updateUser(newUser));
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    }
