package com.jack_dev.inventory_control.controllers;

import com.jack_dev.inventory_control.dto.AddressRequestDTO;
import com.jack_dev.inventory_control.entities.Address;
import com.jack_dev.inventory_control.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Controller
@RestController
@RequestMapping("/address")
public class AddressController {
	
	private final AddressService addressService;
	
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}
	
	@GetMapping(
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<List<Address>> findAll() {
		return addressService.getAllAddress();
	}
	
	@GetMapping(value = "/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Address> findById(@PathVariable String id) {
		return addressService.getOneAddress(id);
	}
	
	@PostMapping(
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<AddressRequestDTO> save(@RequestBody AddressRequestDTO addressRequestDTO) {
		return addressService.saveAddress(addressRequestDTO);
	}
	
}
