package com.example.ej2crud.application.person;

import com.example.ej2crud.application.dto.ResponseDto;
import com.example.ej2crud.application.exceptions.UnprocessableException;
import com.example.ej2crud.domain.Person;
import com.example.ej2crud.infraestructure.dto.input.InputPersonDto;
import com.example.ej2crud.infraestructure.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class AddPersonUseCase extends PersonUseCase {

    AddPersonUseCase(PersonRepository personRepository) {
        super(personRepository);
    }

    public ResponseDto add(InputPersonDto inputPersonDto) {
        if (inputPersonDto.getUser() == null) {
            throw new UnprocessableException("El usuario no puede ser nulo");
        } else if (inputPersonDto.getUser().length() > 10){
            throw new UnprocessableException("La longitud del campo usuario no puede ser superior a 10 caracteres");
        }
        Person person = new Person();
        person.setUser(inputPersonDto.getUser());
        person.setPassword(inputPersonDto.getPassword());
        person.setName(inputPersonDto.getName());
        person.setSurname(inputPersonDto.getSurname());
        person.setCompany_email(inputPersonDto.getCompany_email());
        person.setPersonal_email(inputPersonDto.getPersonal_email());
        person.setCity(inputPersonDto.getCity());
        person.setActive(inputPersonDto.getActive());
        person.setCreated_date(inputPersonDto.getCreated_date());
        person.setImage_url(inputPersonDto.getImage_ulr());
        this.personRepository.saveAndFlush(person);
        return new ResponseDto(true, "El usuario "+inputPersonDto.getUser()+" ha sido creado");
    }

}
