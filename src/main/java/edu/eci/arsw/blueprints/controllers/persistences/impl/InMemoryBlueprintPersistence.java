package edu.eci.arsw.blueprints.controllers.persistences.impl;

import edu.eci.arsw.blueprints.controllers.persistences.BlueprintNotFoundException;
import org.springframework.stereotype.Service;
import edu.eci.arsw.blueprints.controllers.model.Blueprint;
import edu.eci.arsw.blueprints.controllers.model.Point;
import edu.eci.arsw.blueprints.controllers.persistences.BlueprintsPersistence;
import edu.eci.arsw.blueprints.controllers.persistences.BlueprintPersistenceException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
        Blueprint bp=new Blueprint("_authorname_", "_bpname_ ",pts);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);

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
            if(entry.getKey().getElem1() == author){
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

}
