package hello.service;

import hello.model.Greeting;
import hello.model.Person;
import hello.repository.GreetingRepository;
import hello.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class GreetingService {


    private GreetingRepository greetingRepository;


    private final AtomicLong counter = new AtomicLong();
    private PersonRepository personRepository;

    @Autowired
    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public int insert(Greeting greeting) {
        System.out.println("Inserting Greeting In Database");
        Person person=getPersonFromGreeting(greeting);
        greetingRepository.insert(greeting,person);
        return 1;
    }

    private Person getPersonFromGreeting(Greeting greeting) {
        Person person = greeting.getPerson();
        Person personDto = new Person();
        if(person!=null){
            personDto.setName(person.getName());
            personDto.setPhone(person.getPhone());
            personDto.setAddress(personDto.getAddress());
        }
        return personDto;

    }

    public java.util.List<Greeting> findGreeting(String name) {
        System.out.println("**************finding greeting ****************");
        return greetingRepository.findGreeting(name);

    }
}
