package com.example.ej2crud.infraestructure.controller;

import com.example.ej2crud.infraestructure.dto.output.OutputProfessorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

public class ClientController {

    @GetMapping("/professor/{id}")
    public ResponseEntity<OutputProfessorDto> getProfessor(@PathVariable int id){
        ResponseEntity<OutputProfessorDto> professor = new RestTemplate().getForEntity("http://localhost:8080/professor" + id + "?outputType=full", OutputProfessorDto.class);
        return ResponseEntity.ok(professor.getBody());
    }
}
