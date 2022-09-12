package com.practium.FT.service;

import com.practium.FT.dto.request.UserRequestDTO;
import com.practium.FT.dto.response.UserResponseDTO;
import com.practium.FT.entity.User;
import com.practium.FT.exception.NotFoundException;
import com.practium.FT.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Cacheable(value = "user")
    public List<UserResponseDTO> findAllUser() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Cacheable(value = "user")
    public UserResponseDTO findUserById(Long id) {
        return modelMapper
                .map(userRepository
                        .findUserById(id)
                        .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı")), UserResponseDTO.class);

    }

    public User findById(Long id){
        return userRepository.findById(id).get();

    }
    @Transactional
    public UserResponseDTO createOneUser(UserRequestDTO userRequestDTO) {
        return modelMapper.map(userRepository.save(modelMapper.map(userRequestDTO,User.class)),UserResponseDTO.class);
    }
    @Transactional
    @CachePut(value = "user")
    public UserResponseDTO updateUser(Long id,UserRequestDTO newUserRequest) {
        User oldUser = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Kullanıcı Bulunamadı"));
                oldUser.setUserName(newUserRequest.getUserName());
                oldUser.setUserSurname(newUserRequest.getUserSurname());
                oldUser.setUserEmail(newUserRequest.getUserEmail());
                oldUser.setUserPhoneNumber(newUserRequest.getUserPhoneNumber());

                userRepository.save(oldUser);

                return modelMapper.map(userRepository.findById(oldUser.getId()),UserResponseDTO.class);


    }
    @CacheEvict(value = "user",allEntries = true)
    public void deleteUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı."));
        userRepository.deleteById(user.getId());
    }


}



