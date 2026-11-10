package com.jknpp.usuario.business.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TelefoneDto {

    private Long id;
    private String numero;
    private String ddd;
}
