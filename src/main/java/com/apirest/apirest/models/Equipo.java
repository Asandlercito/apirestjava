package com.apirest.apirest.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "equipos")

public class Equipo {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "codigo")
    private String codigo;
    
    @CreatedDate
    @Column(name = "created")
    private String createdAt;
    
    @LastModifiedDate 
    @Column(name = "updated")
    private String updatedAt;
    
    @ManyToOne
    @JoinColumn(name="alquiler_id", nullable=false)
    private Alquiler alquiler;

    public Equipo() {}
    

    
}
