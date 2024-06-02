package com.group.vitalmedapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.vitalmedapi.services.CirurgiaService;

@RestController
@RequestMapping("cirurgias")
public class CirurgiaController {
    
    @Autowired
    CirurgiaService cirurgiaServiceService;

}
