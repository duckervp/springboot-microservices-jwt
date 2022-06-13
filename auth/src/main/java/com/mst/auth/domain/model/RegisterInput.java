package com.mst.auth.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterInput {
    @NotNull
    @NotBlank
    @Size(min = 5, max = 30)
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 25)
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 25)
    private String password;

    private Integer majorId;

    private List<Integer> roleIds;
}
