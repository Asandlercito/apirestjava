package com.apirest.apirest.models;

import java.util.Set;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity 
@Getter 
@Setter
@ToString 
@EqualsAndHashCode
@Table(name = "usuarios")

public class User  {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	

    @Column(name = "nombre")
    private String nombre;
	
    @Column(name = "apellido")
    private String apellido;
	
    @Column(name = "email",unique=true)
    private String email;
    
	@Column(name = "telefono",unique=true)
    private String telefono;

    @Column(name = "password")
    private String password;
    

    @OneToMany(mappedBy="user")
    private Set<Nota> notas;
	
    public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Nota> getNotas() {
		return notas;
	}

	public void setNotas(Set<Nota> notas) {
		this.notas = notas;
	}

	public Long getId() {
		return id;
	}


}
