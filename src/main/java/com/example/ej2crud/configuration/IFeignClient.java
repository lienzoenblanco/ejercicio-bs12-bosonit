package com.example.ej2crud.configuration;

import com.example.ej2crud.infraestructure.dto.output.OutputProfessorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="simpleFeign",url="http://localhost:8081/")
public interface IFeignClient {
    @GetMapping("/professor/{id}")
    public ResponseEntity<OutputProfessorDto> callServer(@PathVariable int id);
}
