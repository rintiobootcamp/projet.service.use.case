package com.bootcamp.controllers;

import com.bootcamp.output.SondagePrime;
import com.bootcamp.services.SondageService;
import infos.ProjetAIO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController("SondageController")
@RequestMapping("/sondage")
@Api(value = "Sondage API", description = "Sondage API")
@CrossOrigin(origins = "*")
public class SondageController {
    @Autowired
    private SondageService sondageService;

    @RequestMapping(method = RequestMethod.GET , value = "/prime")
    @ApiOperation(value = " Lire le sondage a la une", notes = "lire le sondage a la une")
    public ResponseEntity<SondagePrime> getPrime() throws Exception {
        SondagePrime sondagePrime = sondageService.getPrime();
        return new ResponseEntity<SondagePrime>(sondagePrime, HttpStatus.OK);

    }
}
