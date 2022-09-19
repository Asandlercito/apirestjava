package com.apirest.apirest.models;

import java.util.Set;

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
@Table(name = "alquileres")

public class Alquiler {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "descripcion_alquiler")
    private String nombreEmpresa;
       
    @Column(name = "codigo")
    private String codigo;
    
    @CreatedDate
    @Column(name = "created")
    private String createdAt;
    
    @LastModifiedDate 
    @Column(name = "updated")
    private String updatedAt;
    
    @OneToOne
    @JoinColumn(name="empresa_id", nullable=false)
    private Empresa empresa;
    
    @OneToOne
    @JoinColumn(name="asesor_id", nullable=false)
    private Asesor asesor;
    
    @OneToMany(mappedBy="alquiler")
    private Set<Equipo> equipos;

}
