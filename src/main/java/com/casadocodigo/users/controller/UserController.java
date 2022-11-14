package com.casadocodigo.users.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casadocodigo.users.dto.UserDTO;

@RestController
@RequestMapping("/users")
public class UserController {

    public static List<UserDTO> usuarios = new ArrayList<>();

    @GetMapping("/mensagem")
    public String getMensagem() {
        return "Spring boot is working";
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        return usuarios;
    }

    @PostMapping
    public UserDTO inserir(@RequestBody UserDTO userDTO) {
        userDTO.setDataCadastro(new Date());
        usuarios.add(userDTO);
        return userDTO;
    }

    @GetMapping("/{cpf}")
    public UserDTO getUsersFiltro(@PathVariable String cpf) {
        
        Optional<UserDTO> userFilter = usuarios.stream()
            .filter(user -> user.getCpf().equals(cpf))
            .findFirst();
        
        if (userFilter.isPresent()) {
            return userFilter.get();
        }
        
        return null;
    }

    @DeleteMapping("/{cpf}")
    public Boolean remover(@PathVariable String cpf) {
        for (UserDTO userFilter : usuarios) {
            if (userFilter.getCpf().equals(cpf)) {
                usuarios.remove(userFilter);
                return true;
            }
        }

        return false;
    }



    @PostConstruct
    public void initiateList() {
        UserDTO userDto = UserDTO.builder()
            .nome("Eduardo")
            .cpf("123")
            .endereco("Rua a")
            .email("eduardo@email.com")
            .telefone("1234-3454")
            .dataCadastro(new Date())
            .build();

        UserDTO userDto2 = UserDTO.builder()
            .nome("Luiz")
            .cpf("456")
            .endereco("Rua b")
            .email("luiz@email.com")
            .telefone("1234-3454")
            .dataCadastro(new Date())
            .build();
        
        UserDTO userDto3 = UserDTO.builder()
            .nome("Bruna")
            .cpf("678")
            .endereco("Rua c")
            .email("bruna@email.com")
            .telefone("1234-3454")
            .dataCadastro(new Date())
            .build();

        usuarios.addAll(Arrays.asList(userDto, userDto2, userDto3));
    }
    
}
