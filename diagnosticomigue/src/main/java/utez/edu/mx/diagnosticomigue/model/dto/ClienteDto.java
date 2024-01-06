package utez.edu.mx.diagnosticomigue.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ClienteDto {

    private Integer idcliente;
    private String nombre;
    private String apellidos;
    private String curp;
    private String fechanac;
}
