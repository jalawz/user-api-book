package com.casadocodigo.users.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.casadocodigo.users.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user", schema = "users")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String endereco;
    private String email;
    private String telefone;
    private Date dataCadastro;

    public static User convert(UserDTO dto) {
        return User.builder()
            .nome(dto.getNome())
            .endereco(dto.getEndereco())
            .cpf(dto.getCpf())
            .email(dto.getEmail())
            .telefone(dto.getTelefone())
            .dataCadastro(dto.getDataCadastro())
            .build();
    }
}
