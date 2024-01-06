package utez.edu.mx.diagnosticomigue.model.entity;


import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Builder
@Table(name="cliente")
public class ClienteBean {
    @Id
    @Column(name = "idcliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idclient;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "apellidos", nullable = false)
    private String apellidos;
    @Column(name = "curp", nullable = false)
    private String curp;
    @Column(name = "fechanac", nullable = false)
    private String fechanac;
}
