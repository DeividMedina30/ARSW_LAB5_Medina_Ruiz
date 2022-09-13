package edu.eci.arsw.blueprints.controllers.filtros;

import edu.eci.arsw.blueprints.controllers.model.Blueprint;
import edu.eci.arsw.blueprints.controllers.model.Point;

import java.util.ArrayList;
import java.util.List;


public interface BluePrintFilter {
    /**
     * Clase que me permite recorrer la lista de BluePrint y aplicar FiltroA
     * @param blueprints ArrayList<BluePrint>
     * @return ArrayList<Blueprint>, La nueva BluePrint con los puntos filtrados.
     */
    public ArrayList<Blueprint> filtrarPuntosA(ArrayList<Blueprint> blueprints);

    /**
     * Clase que me permite recorrer la lista de BluePrint y aplicar FiltroB
     * @param blueprints ArrayList<BluePrint>
     * @return ArrayList<Blueprint>, La nueva BluePrint con los puntos filtrados.
     */
    public ArrayList<Blueprint> filtrarPuntosB(ArrayList<Blueprint> blueprints);

    /**
     *Clase que me permite realizar la comparación correspondiente para aplicar el FiltroA
     * @param blueprintA , Lista blueprintA con los puntos correspondientes.
     * @return List(Point), Lista que contiene los nuevos puntos filtrados.
     */
    public List<Point> filtrarA(Blueprint blueprintA);

    /**
     *Clase que me permite realizar la comparación correspondiente para aplicar el FiltroB
     * @param blueprintB , Lista blueprintB con los puntos correspondientes.
     * @return List(Point), Lista que contiene los nuevos puntos filtrados.
     */
    public List<Point> filtrarB(Blueprint blueprintB);
}
