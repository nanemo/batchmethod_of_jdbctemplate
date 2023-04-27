package com.nanemo.controller;

import com.nanemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static java.lang.System.currentTimeMillis;

@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("people", personService.getAllPerson());
        return "person_page/first_page";
    }


    @PostMapping("/with_wbatch_method")
    public String addPeopleWithBatchMethod(Model model) {
        long afterAdding = currentTimeMillis();
        personService.addPeopleWithBatchMethod();
        long beforeAdding = currentTimeMillis();

        model.addAttribute("time_difference", beforeAdding - afterAdding);
        return "redirect:/person";
    }

    @PostMapping("/with_simple_update_method")
    public String addPeopleWithSimpleUpdateMethod(Model model) {
        long afterAdding = currentTimeMillis();
        personService.addPeopleWithSimpleUpdateMethod();
        long beforeAdding = currentTimeMillis();

        model.addAttribute("time_difference", beforeAdding - afterAdding);
        return "redirect:/person";
    }

    @DeleteMapping("/delete")
    public String dropAndCreatePersonTable() {
        personService.dropAndCreatePersonTable();
        return "redirect:/person";
    }

}
