/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {

    @Autowired
    @Qualifier("bpservice")
    BlueprintsServices bps;

    @RequestMapping(method = RequestMethod.GET)

    public ResponseEntity<ArrayList<Blueprint>> manejadorGetRecursoXX() {
        try {
            //obtener datos que se enviarán a través del API

            return new ResponseEntity<ArrayList<Blueprint>>(bps.getAllBlueprints(), HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @RequestMapping(path = "/{author}", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Blueprint>> manejadorGetRecursoAuthors(@PathVariable("author") String author) {
        try {
            //obtener datos que se enviarán a través del API

            return new ResponseEntity((ArrayList<Blueprint>) bps.getBlueprintsByAuthor(author), HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException e) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<ArrayList<Blueprint>>((ArrayList<Blueprint>) null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/{author}/{bpname}", method = RequestMethod.GET)
    public ResponseEntity<Blueprint> manejadorGetRecursoAuthorsNombres(@PathVariable("author") String author, @PathVariable("bpname") String bpname) {
        try {
            //obtener datos que se enviarán a través del API

            return new ResponseEntity<Blueprint>(bps.getBlueprint(author, bpname), HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException e) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<Blueprint>((Blueprint) null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/crear", method = RequestMethod.POST)
    public ResponseEntity<Blueprint> manejadorPostRecursoNuevoBlueprint(@RequestBody Blueprint bp) {
        try {
            //registrar dato
            bps.addNewBlueprint(bp);
            return new ResponseEntity<Blueprint>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<Blueprint>((Blueprint) null, HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(path = "/{author}/{bpname}", method = RequestMethod.PUT)
    public ResponseEntity<Blueprint> manejadorPutRecursoAuthorsNombres(@PathVariable("author") String author, @PathVariable("bpname") String bpname, @RequestBody Blueprint bp) {
        try {
            //registrar dato
            bps.updateBlueprint(author, bpname, bp.getPoints());
            return new ResponseEntity<Blueprint>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<Blueprint>((Blueprint) null, HttpStatus.FORBIDDEN);
        }

    }
}
