package com.jack_dev.estoque.services;

import com.jack_dev.estoque.dto.AddressRequestDTO;
import com.jack_dev.estoque.entities.Address;
import com.jack_dev.estoque.exceptions.ResourceNotFound;
import com.jack_dev.estoque.mapper.Mapper;
import com.jack_dev.estoque.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AddressService {
	
	@Autowired
	private final AddressRepository addressRepository;
	
	public AddressService(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}
	
	//	GetAll
	public ResponseEntity<List<AddressRequestDTO>> getAllAddress() {
		var addresses = Mapper.parseListObjects(addressRepository.findAll(), AddressRequestDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(addresses);
	}
	
	//	GetById
	public ResponseEntity<AddressRequestDTO> getOneAddress(UUID id) {
		var entity = addressRepository.findById(id).orElseThrow(
				() -> new ResourceNotFound("The Id: " + id + "Not Found"));
		return ResponseEntity.status(HttpStatus.OK).body(Mapper.parseObject(entity, AddressRequestDTO.class));
	}
	
	//Post
	public ResponseEntity<AddressRequestDTO> saveAddress(Address address) {
		var entity = Mapper.parseObject(addressRepository.save(address), AddressRequestDTO.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(entity);
	}
	
	// Put
	public ResponseEntity<AddressRequestDTO> updateAddress(UUID id, Address address) {
		var entity = addressRepository.findById(id).orElseThrow(
				() -> new ResourceNotFound("The Id: " + id + "Not Found"));
		return ResponseEntity.status(HttpStatus.OK)
				.body(Mapper.parseObject(addressRepository.save(entity), AddressRequestDTO.class));
	}
	
	public ResponseEntity<AddressRequestDTO> deleteAddress(UUID id){
		var entity = addressRepository.findById(id).orElseThrow(
				()-> new ResourceNotFound("The Id: " + id + "Not Found"));
		addressRepository.delete(entity);
		return ResponseEntity.status(HttpStatus.OK).body(Mapper.parseObject(entity, AddressRequestDTO.class));
	}
}
