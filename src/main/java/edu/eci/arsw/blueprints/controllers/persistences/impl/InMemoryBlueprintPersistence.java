package edu.eci.arsw.blueprints.controllers.persistences.impl;

import edu.eci.arsw.blueprints.controllers.persistences.BlueprintNotFoundException;
import org.springframework.stereotype.Service;
import edu.eci.arsw.blueprints.controllers.model.Blueprint;
import edu.eci.arsw.blueprints.controllers.model.Point;
import edu.eci.arsw.blueprints.controllers.persistences.BlueprintsPersistence;
import edu.eci.arsw.blueprints.controllers.persistences.BlueprintPersistenceException;

import java.util.*;

/**
 *
 * @author hcadavid
 */
@Service("serviceInMemoryBlueprintPersistence")
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    public final static Map<Tuple<String,String>,Blueprint> blueprints=new HashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts=new Point[]{new Point(140, 140),new Point(115, 115)};
        Point[] pts2=new Point[]{new Point(130, 170),new Point(185, 175)};
        Point[] pts3=new Point[]{new Point(120, 160),new Point(15, 195)};
        Point[] pts4=new Point[]{new Point(10, 60),new Point(5, 95)};
        Blueprint bp=new Blueprint("Deivid", "planoUno",pts);
        Blueprint bp2=new Blueprint("Deivid", "planoDos",pts2);
        Blueprint bp3=new Blueprint("Cristian", "planoTres",pts3);
        Blueprint bp4=new Blueprint("Cristian", "planoCuatro",pts4);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        blueprints.put(new Tuple<>(bp2.getAuthor(),bp2.getName()), bp2);
        blueprints.put(new Tuple<>(bp3.getAuthor(),bp3.getName()), bp3);
        blueprints.put(new Tuple<>(bp4.getAuthor(),bp4.getName()), bp4);

    }


    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        return blueprints.get(new Tuple<>(author, bprintname));
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
        Set<Blueprint> lista = new HashSet<Blueprint>();
        for(Map.Entry<Tuple<String, String>,Blueprint> entry : blueprints.entrySet()){
            if(entry.getKey().getElem1().equals(author)){
                String name = entry.getKey().getElem2();
                lista.add(blueprints.get(new Tuple<>(author, name)));
            }
        }
        return lista;

    }

    @Override
    public Set<Blueprint> getAllBlueprints(){
        Set<Blueprint> lista = new HashSet<Blueprint>();
        for(Map.Entry<Tuple<String, String>,Blueprint> entry : blueprints.entrySet()){
            lista.add(blueprints.get(entry.getKey()));
        }
        return lista;
    }

    @Override
    public Set<Blueprint> getAllBlueprintsByAuthorAndPlano(String author, String plano) {
        Set<Blueprint> lista = new HashSet<Blueprint>();
        for(Map.Entry<Tuple<String, String>,Blueprint> entry : blueprints.entrySet()){
            if(entry.getKey().getElem1().equals(author) && entry.getKey().getElem2().equals(plano)){
                String name = entry.getKey().getElem2();
                lista.add(blueprints.get(new Tuple<>(author, name)));
                break;
            }
        }
        return lista;
    }

    @Override
    public Set<Blueprint> editBlueprint(String author, String plano, Blueprint newBlueprint) {
        Set<Blueprint> lista = new HashSet<>();
        Blueprint currentBlueprint = null;
        try {
            currentBlueprint = getBlueprint(author, plano);
        } catch (BlueprintNotFoundException e) {
            e.printStackTrace();
        }
        List<Point> newPoints = newBlueprint.getPoints();
        currentBlueprint.setPoints(newPoints);
        lista.add(currentBlueprint);
        return lista;
    }

}
