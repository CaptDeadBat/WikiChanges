package com.wordcount.kafkaconsumerwordcount.restapi_domainchanges;
import java.net.URI;

import org.springframework.beans
        .factory.annotation.Autowired;
import org.springframework.http
        .ResponseEntity;
import org.springframework.web.bind
        .annotation.GetMapping;
import org.springframework.web.bind
        .annotation.PostMapping;
import org.springframework.web.bind
        .annotation.RequestBody;
import org.springframework.web.bind
        .annotation.RequestMapping;
import org.springframework.web.bind
        .annotation.RestController;
import org.springframework.web.servlet
        .support.ServletUriComponentsBuilder;



@RestController
@RequestMapping(path = "/domainchanges")
public class DomainChangeController {
    @Autowired
    private DomainChangeDAO domainChangeDao;


    @GetMapping(
            path = "/",
            produces = "application/json")

    public DomainChanges getDomainChanges()
    {

        return domainChangeDao
                .getAllDomainChanges();
    }

    @GetMapping(path = "/clear")                //clear list of changes if needed
    public String clearDomainChanges(){
        System.out.println("recieved clear api");
        domainChangeDao.removeAllChanges();
        return "Cleared";
    }

    @PostMapping(
            path = "/",
            consumes = "application/json",
            produces = "application/json")

    public ResponseEntity<Object> addDomain(
            @RequestBody DomainChange domainChange)
    {


        System.out.println("added"+domainChange.getDomainName());
        domainChangeDao
                .addDomain(domainChange);

        URI location
                = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{domainName}")
                .buildAndExpand(
                        domainChange.getDomainName())
                .toUri();

        return ResponseEntity
                .created(location)
                .build();
    }


}
