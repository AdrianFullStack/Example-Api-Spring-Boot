package com.adrianfullstack.erp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adrianfullstack.erp.models.Client;

public interface ClientRepository extends JpaRepository<Client, String> {
	
	public List<Client> findByLastNameCli(String lastNameCli);
	
	public Client findByDocumentCli(String documentCli);
}
