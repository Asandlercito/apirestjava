package com.apirest.apirest.models;

import javax.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity 
@Getter 
@Setter
@ToString 
@EqualsAndHashCode
@Table(name = "empresas")

public class Empresa {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre_empresa")
    private String nombreEmpresa;
    
    @Column(name = "nro_documento")
    private String nroDocumento;

    @Column(name = "codigo")
    private String codigo;
    
    @CreatedDate
    @Column(name = "created")
    private String createdAt;
    
    @LastModifiedDate 
    @Column(name = "updated")
    private String updatedAt;
    
    @ManyToOne
    @JoinColumn(name="asesor_id", nullable=false)
    private Asesor asesor;

    public Empresa() {}
    


}
