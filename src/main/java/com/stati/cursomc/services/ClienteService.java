package com.stati.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.stati.cursomc.domain.Cliente;
import com.stati.cursomc.dto.ClienteDTO;
import com.stati.cursomc.repositories.ClienteRepository;
import com.stati.cursomc.services.exceptions.DataIntegrityException;
import com.stati.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(()->new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		return repo.save(cliente);
	}
	
	public Cliente update(Cliente cliente) {
		Cliente newObj =  find(cliente.getId());
		updateData(newObj, cliente);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma cliente que possui pedidos");
		}
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	private void updateData(Cliente newObj, Cliente cliente) {
		newObj.setNome(cliente.getNome());
		newObj.setEmail(cliente.getEmail());
	}

}
