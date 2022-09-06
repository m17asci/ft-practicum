package com.practium.FT.service;

import com.practium.FT.dto.request.UserRequestDTO;
import com.practium.FT.dto.response.UserResponseDTO;
import com.practium.FT.entity.User;
import com.practium.FT.exception.UserNotFoundException;
import com.practium.FT.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<UserResponseDTO> findAllUser() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }


    public UserResponseDTO findUserById(Long id) {
        return modelMapper
                .map(userRepository
                        .findUserById(id)
                        .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı")), UserResponseDTO.class);

    }

    public User findById(Long id){
        return userRepository.findById(id).get();

    }
    public UserResponseDTO createOneUser(UserRequestDTO userRequestDTO) {
        return modelMapper.map(userRepository.save(modelMapper.map(userRequestDTO,User.class)),UserResponseDTO.class);
    }

    public UserResponseDTO updateUser(UserRequestDTO newUserRequest) {
        User oldUser = userRepository.findById(newUserRequest.getId()).orElseThrow(() -> new UserNotFoundException("Kullanıcı Bulunamadı"));
        userRepository.findById(newUserRequest.getId())
                .ifPresent(user -> {
                    user.setUserName(newUserRequest.getUserName());
                    user.setUserSurname(newUserRequest.getUserSurname());
                    user.setUserEmail(newUserRequest.getUserEmail());
                    user.setUserPhoneNumber(newUserRequest.getUserPhoneNumber());
                    userRepository.save(user);
                });

        return modelMapper.map(userRepository.findById(oldUser.getId()).get(), UserResponseDTO.class);
    }

    public void deleteUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı."));
        userRepository.deleteById(user.getId());
    }


}



