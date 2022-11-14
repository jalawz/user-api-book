package com.casadocodigo.users.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import com.casadocodigo.users.dto.UserDTO;
import com.casadocodigo.users.model.User;
import com.casadocodigo.users.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
            .map(UserDTO::convert)
            .collect(Collectors.toList());
    }

    public UserDTO findById(Long userId) {
        User user = findUserEntityById(userId);

        return UserDTO.convert(user);
    }

    public User save(UserDTO dto) {
        return userRepository.save(User.convert(dto));
    }

    public void delete(Long userId) {
        User user = findUserEntityById(userId);
        userRepository.delete(user);
    }

    public UserDTO findByCpf(String cpf) {
        User user = userRepository.findByCpf(cpf);
        if (Objects.nonNull(user)) {
            return UserDTO.convert(user);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User not found by cpf: %s", cpf));
    }

    public List<UserDTO> queryByName(String name) {
        List<User> users = userRepository.queryByNomeLike(name);
        return users.stream()
            .map(UserDTO::convert)
            .collect(Collectors.toList());
    }

    private User findUserEntityById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %d not found", userId)));
    }
}
