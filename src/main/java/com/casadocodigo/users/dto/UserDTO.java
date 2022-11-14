package com.casadocodigo.users.dto;

import java.util.Date;

import com.casadocodigo.users.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    
    private String nome;
    private String cpf;
    private String endereco;
    private String email;
    private String telefone;
    private Date dataCadastro;

    public static UserDTO convert(User user) {
        return UserDTO.builder()
            .nome(user.getNome())
            .endereco(user.getEndereco())
            .cpf(user.getCpf())
            .email(user.getEmail())
            .telefone(user.getTelefone())
            .dataCadastro(user.getDataCadastro())
            .build();
    }
}
