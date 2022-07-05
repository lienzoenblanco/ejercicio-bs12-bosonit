package com.example.ej2crud.application.person;

import com.example.ej2crud.domain.Person;
import com.example.ej2crud.infraestructure.dto.output.OutputPersonDto;
import com.example.ej2crud.infraestructure.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListPersonUseCase extends PersonUseCase {

    ListPersonUseCase(PersonRepository personRepository) {
        super(personRepository);
    }

    public List<OutputPersonDto> list() {
        List<Person> personList= this.personRepository.findAll();
        return this.serialize(personList);
    }

}
