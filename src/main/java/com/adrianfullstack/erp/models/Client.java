package com.adrianfullstack.erp.models;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import lombok.Data;

@Data
@Entity
@Table(name="clients")
public class Client {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String idCli;
	private String nameCli;
	private String lastNameCli;
	private String documentCli;
	private String addressCli;
	private String phoneCli;
	private String emailCli;
	@OneToMany
	private Set<Reserva> reservas;
	
	public Client() {}
}
