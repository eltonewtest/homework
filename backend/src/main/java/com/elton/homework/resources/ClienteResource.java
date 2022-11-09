package com.elton.homework.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elton.homework.dto.ClientDTO;
import com.elton.homework.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClienteResource {

	@Autowired
	private ClientService cliService;
	
	@GetMapping
	public ResponseEntity<Page<ClientDTO>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
												@RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage,
												@RequestParam(value = "direction", defaultValue = "ASC") String direction,
												@RequestParam(value = "orderBy", defaultValue = "name") String orderBy)
	{
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		Page<ClientDTO> list = cliService.findAllPaged(pageRequest);
		return ResponseEntity.ok().body(list);
	}
	
}
