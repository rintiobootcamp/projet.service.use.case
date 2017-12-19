package com.bootcamp.controllers;

import com.bootcamp.services.StatistiqueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.QueryParam;

/**
 * Created by Bignon .
 */
@RestController("StatistiqueController")
@RequestMapping("/stats")
@Api(value = "statistique API", description = "statistique API")
@CrossOrigin(origins = "*")
public class StatistiqueController {

    @Autowired
    StatistiqueService statistiqueService;

    @RequestMapping(method = RequestMethod.GET, value = "/{entityType}/{entityId}")
    @ApiOperation(value=" Get all statistics about an entity",notes="Get all statistics about an entity")
    public ResponseEntity<List<HashMap<String,Double>>> getStatForEntity(@PathVariable("entityType") String entityType, @PathVariable("entityId") int entityId, @RequestParam("startDate") long startDate, @RequestParam("endDate") long endDate) throws IOException{
        List<HashMap<String,Double>> toreturn = statistiqueService.getStatistique(entityType,entityId,startDate,endDate);
        return new ResponseEntity<>(toreturn,HttpStatus.OK);

    }

//    @RequestMapping(method = RequestMethod.GET, value = "/{entityType}")
//    @ApiOperation(value=" Get all statistics about an entity",notes="Get all statistics about an entity")
//    public ResponseEntity<List<HashMap<String,Double>>> getAllStatByEntity(@PathVariable("entityType") String entityType,@RequestParam("startDate") long startDate, @RequestParam("endDate") long endDate) throws IOException{
//        List<HashMap<String,Double>> toreturn = statistiqueService.getStatistique(entityType,startDate,endDate);
//        return new ResponseEntity<>(toreturn,HttpStatus.OK);
//
//    }

}
