/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.blueprints.controllers.model.Blueprint;
import edu.eci.arsw.blueprints.controllers.persistences.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.controllers.services.BlueprintsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author hcadavid
 */
@RestController //los @RestController anotación en Spring es esencialmente una combinación de @Controller y @ResponseBody. Esta anotación se agregó durante la Spring 4.0 para eliminar la redundancia de declarar el @ResponseBody anotación en su controlador.
@RequestMapping(value = "/blueprints") //Se utiliza para asignar solicitudes web a clases de controlador específicas y/o métodos de controlador. @RequestMappingse puede aplicar a la clase de controlador, así como a los métodos.
public class BlueprintAPIController {

    @Autowired
    BlueprintsServices bps;

    @RequestMapping(method = RequestMethod.GET , produces = {MediaType.APPLICATION_JSON_VALUE}) //Define que formato va a devolver, por defecto es JSON - produces = {MediaType.APPLICATION_JSON_VALUE}
    public ResponseEntity<?> getAllBluePrints(){ //ResponseEntity<?> Envolver la respuesta y además se puede agregar unos parámetros.
        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(bps.getAllBlueprints(), HttpStatus.ACCEPTED);//HttpStatus.ACCEPTED 202 Accepted. https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/HttpStatus.html
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla",HttpStatus.NOT_FOUND);//HttpStatus.NOT_FOUND 404 - Cuando el recurso no existe
        }
    }

    @RequestMapping(value = "/{author}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE}) //Indicar que la ruta recibe un parametro {author}.
    public ResponseEntity<?> getBluePrintAuthor(@PathVariable("author") String author){ //@PathVariable indica que nos referimos a datos incluidos dentro del mismo path del pedido
        try {
            Set<Blueprint> planosAuthor =  bps.getBlueprintsByAuthor(author);
            if(planosAuthor.isEmpty()){
                return new ResponseEntity<>("No existe el Author", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(planosAuthor,HttpStatus.ACCEPTED);
        }catch (Exception ex){
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Hubo un error con la petición", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{author}/{bpname}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getBluePrintAuthorAndPlano(@PathVariable("author") String author, @PathVariable("bpname") String bpname){
        try {
            Set<Blueprint> planoAuthor =  bps.getAllBlueprintsByAuthorAndPlano(author, bpname);
            if(planoAuthor.isEmpty()){
                return new ResponseEntity<>("No existe el Author y/o el plano", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(planoAuthor,HttpStatus.ACCEPTED);
        }catch (Exception ex){
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Hubo un error con la petición", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)	
    public ResponseEntity<?> createNewBlueprint(@RequestBody Blueprint blueprint){
        try {
            bps.addNewBlueprint(blueprint);
            return new ResponseEntity<>("Nuevo plano creado", HttpStatus.CREATED);
        } catch (BlueprintPersistenceException ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla",HttpStatus.FORBIDDEN);            
        }
    }

    @RequestMapping(value = "/{author}/{bpname}", method = RequestMethod.PUT)
    public ResponseEntity<?> editBluePrint(@PathVariable("author") String author,@PathVariable("bpname") String bpname, @RequestBody Blueprint newBlueprint){
        try{
            if (bps.getAllBlueprintsByAuthorAndPlano(author, bpname).isEmpty()){
                return new ResponseEntity<>("No existe el Author y/o el plano que desea editar", HttpStatus.NOT_FOUND);
            }
            bps.editBlueprint(author, bpname, newBlueprint);
            return new ResponseEntity<>("Nuevo plano editado correctamente", HttpStatus.ACCEPTED);
        }catch (Exception ex){
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Hubo un error con la petición", HttpStatus.NOT_FOUND);
        }
    }

}

