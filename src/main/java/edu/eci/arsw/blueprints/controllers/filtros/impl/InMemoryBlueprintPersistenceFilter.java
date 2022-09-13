package edu.eci.arsw.blueprints.controllers.filtros.impl;

import edu.eci.arsw.blueprints.controllers.filtros.BluePrintFilter;
import edu.eci.arsw.blueprints.controllers.model.Blueprint;
import edu.eci.arsw.blueprints.controllers.model.Point;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service("serviceInMemoryBlueprintPersistenceFilter")
public class InMemoryBlueprintPersistenceFilter implements BluePrintFilter {

    @Override
    public ArrayList<Blueprint> filtrarPuntosA(ArrayList<Blueprint> blueprints) {
        for(Blueprint bps : blueprints) {
            filtrarA(bps);
        }
        return blueprints;
    }

    @Override
    public ArrayList<Blueprint> filtrarPuntosB(ArrayList<Blueprint> blueprints) {
        for(Blueprint bps : blueprints) {
            filtrarB(bps);
        }
        return blueprints;
    }

    @Override
    public List<Point> filtrarA(Blueprint blueprintA) {
        List<Point> listaActual =  blueprintA.getPoints();
        List<Point> listaFiltrada = new ArrayList<>();
        int cont = listaActual.size();
        for (int i = 0; i < cont-1; i ++){
            Point puntoActual = listaActual.get(i);
            Point puntoSiguiente = listaActual.get(i+1);
            if(puntoActual.getX() == puntoSiguiente.getX() && puntoActual.getY() == puntoSiguiente.getY()){
                listaFiltrada.add(puntoActual);
                i ++;
            }
            else{
                listaFiltrada.add(puntoActual);
            }
        }
        blueprintA.setPoints(listaFiltrada);
        return blueprintA.getPoints();
    }

    @Override
    public List<Point> filtrarB(Blueprint blueprintB) {
        List<Point> listaActual =  blueprintB.getPoints();
        List<Point> listaFiltrada = new ArrayList<>();
        int cont = listaActual.size();
        boolean bandera = true;
        for (int i = 0; i < cont; i += 2){
            if(i == cont-1 || bandera){
                Point puntoActual = listaActual.get(i);
                listaFiltrada.add(puntoActual);
                bandera = false;
            }
            else {
                Point puntoSiguiente = listaActual.get(i+1);
                listaFiltrada.add(puntoSiguiente);
                bandera = true;
            }
        }
        blueprintB.setPoints(listaFiltrada);
        return blueprintB.getPoints();
    }
}
