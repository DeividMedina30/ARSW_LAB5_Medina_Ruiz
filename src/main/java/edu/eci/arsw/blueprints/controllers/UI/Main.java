package edu.eci.arsw.blueprints.controllers.UI;

import edu.eci.arsw.blueprints.controllers.model.Blueprint;
import edu.eci.arsw.blueprints.controllers.model.Point;
import edu.eci.arsw.blueprints.controllers.persistences.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.controllers.persistences.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.controllers.services.BlueprintsServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
    public static void main_spring(String arg[]) throws BlueprintPersistenceException, BlueprintNotFoundException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bps = ac.getBean(BlueprintsServices.class);
    }
}
