/**
 * 
 */
package com.adrianfullstack.erp.resources;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adrianfullstack.erp.models.Client;
import com.adrianfullstack.erp.resources.vo.ClientVo;
import com.adrianfullstack.erp.services.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author AdrianFullStack
 *
 */
@RestController
@RequestMapping("/api/v1/clients")
@Api(tags = "clients")
public class ClientResource {
	private final ClienteService clienteService;
	
	public ClientResource(ClienteService clienteService)
	{
		this.clienteService = clienteService;
	}
	
	@GetMapping
	@ApiOperation(value = "Listar Clientes", notes = "Servico para listar todos los clientes")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Clientes encontrados "),
			@ApiResponse(code = 400, message = "Clientes no encontrados")
	})
	public ResponseEntity<List<Client>> findAll()
	{
		return ResponseEntity.ok(this.clienteService.findAll());
	}
	
	@PostMapping
	@ApiOperation(value = "Crear Cliente", notes = "Servico para crear un nuevo cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Cliente creado correctamente"),
			@ApiResponse(code = 400, message = "Solicitud inválida")
	})
	public ResponseEntity<Client> create(@RequestBody  ClientVo clientVo)
	{
		Client cli = new Client();
		cli.setNameCli(clientVo.getNameCli());
		cli.setLastNameCli(clientVo.getLastNameCli());
		cli.setDocumentCli(clientVo.getDocumentCli());
		cli.setAddressCli(clientVo.getAddressCli());
		cli.setPhoneCli(clientVo.getPhoneCli());
		cli.setEmailCli(clientVo.getEmailCli());
		return new ResponseEntity<>(this.clienteService.createOrUpdate(cli), HttpStatus.CREATED);
	}
	
	@PutMapping("/{documentCli}")
	@ApiOperation(value = "Actualizar Cliente", notes = "Servico para actualizar un nuevo cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Cliente actualizado correctamente"),
			@ApiResponse(code = 400, message = "Cliente no encontrado")
	})
	public ResponseEntity<Client> update(@PathVariable("documentCli") String documentCli, 
			ClientVo clientVo)
	{
		Client cli = this.clienteService.findByDocumentCli(documentCli);
		if (cli == null) 
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else
		{
			cli.setNameCli(clientVo.getNameCli());
			cli.setLastNameCli(clientVo.getLastNameCli());
			cli.setDocumentCli(clientVo.getDocumentCli());
			cli.setAddressCli(clientVo.getAddressCli());
			cli.setPhoneCli(clientVo.getPhoneCli());
			cli.setEmailCli(clientVo.getEmailCli());
			return new ResponseEntity<>(this.clienteService.createOrUpdate(cli), HttpStatus.OK);			
		}
	}
	
	@DeleteMapping("/{documentCli}")
	@ApiOperation(value = "Eliminar Cliente", notes = "Servico para eliminar un nuevo cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Cliente eliminado correctamente"),
			@ApiResponse(code = 400, message = "Cliente no encontrado")
	})
	public void destroy(@PathVariable("documentCli") String documentCli)
	{
		Client cli = this.clienteService.findByDocumentCli(documentCli);
		if (cli != null) 
		{
			this.clienteService.delete(cli);
		}
	}
}
