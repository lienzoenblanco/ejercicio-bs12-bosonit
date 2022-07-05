package com.example.ej2crud.infraestructure.controller;

import com.example.ej2crud.application.exceptions.NotFoundException;
import com.example.ej2crud.application.person.*;
import com.example.ej2crud.infraestructure.dto.input.InputPersonDto;
import com.example.ej2crud.infraestructure.dto.output.OutputCustomErrorDto;
import com.example.ej2crud.infraestructure.dto.output.OutputPersonDto;
import com.example.ej2crud.application.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private AddPersonUseCase addPersonUseCase;
    @PostMapping
    public ResponseEntity add(@RequestBody InputPersonDto inputPersonDto){
        ResponseDto responseDto = this.addPersonUseCase.add(inputPersonDto);
        if (!responseDto.getSuccess()) {
            return new ResponseEntity<>(responseDto,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "https://cdpn.io")
    @PostMapping("/addperson")
    public ResponseEntity createdPerson(@RequestBody InputPersonDto inputPersonDto){
        ResponseDto responseDto = this.addPersonUseCase.add(inputPersonDto);
        return new ResponseEntity(responseDto, HttpStatus.CREATED);
    }


    @Autowired
    private ListPersonUseCase listPersonUseCase;
    @GetMapping
    public List<OutputPersonDto> list(){
        return this.listPersonUseCase.list();
    }

    @CrossOrigin(origins = "https://cdpn.io")
    @GetMapping("/getall")
    public List<OutputPersonDto> listPerson(){
        return this.listPersonUseCase.list();
    }


    @Autowired
    private ByIdPersonUseCase byIdPersonUseCase;
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable int id) {
        try{
            OutputPersonDto output = this.byIdPersonUseCase.getById(id);
            return new ResponseEntity<OutputPersonDto>(output, HttpStatus.OK);
        } catch (NotFoundException exception) {
            OutputCustomErrorDto error = new OutputCustomErrorDto(new Date(), HttpStatus.NOT_FOUND.value(), exception.getMessage());
            return new ResponseEntity<OutputCustomErrorDto>(error, HttpStatus.NOT_FOUND);
        }
    }


    @Autowired
    private ByNamePersonUseCase byNamePersonUseCase;
    @GetMapping("/name/{name}")
    public List<OutputPersonDto> getByName(@PathVariable String name) {
        return this.byNamePersonUseCase.findByName(name);
    }


    @Autowired
    private UpdatePersonUseCase updatePersonUseCase;
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @RequestBody InputPersonDto person,
            @PathVariable int id
    ) {
        if (!this.updatePersonUseCase.update(person, id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @Autowired
    DeletePersonUseCase deletePersonUseCase;
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        this.deletePersonUseCase.delete(id);
    }




}
