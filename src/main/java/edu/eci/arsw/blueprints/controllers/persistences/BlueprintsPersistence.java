package edu.eci.arsw.blueprints.controllers.persistences;

import java.util.Set;
import edu.eci.arsw.blueprints.controllers.model.Blueprint;

public interface BlueprintsPersistence {
    /**
     *
     * @param bp the new blueprint
     * @throws BlueprintPersistenceException if a blueprint with the same name already exists,
     *    or any other low-level persistence error occurs.
     */
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException;

    /**
     * Método que me obtiene todos los autores con el nombre de sus pintura.
     * @param author blueprint's author
     * @param bprintname blueprint's author
     * @return the blueprint of the given name and author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String bprintname) throws BlueprintNotFoundException;

    /**
     * Método que me Obtiene un autor y sus pinturas, dado su nombre.
     * @param author - String nombre del pintor que queremos obtener sus obras o datos.
     * @return Set(Blueprint) - El cual contiene toda la información del pintor que se solicito.
     * @throws BlueprintNotFoundException
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException;

    /**
     * Método que me retorna todos los BluePrints que hay actualmente.
     * @return Set(Blueprint) - El cual contiene toda la información de los BluePrint
     * @throws BlueprintNotFoundException
     */
    public Set<Blueprint> getAllBlueprints();

    /**
     * Método que me retorna el autor dado su nombre y el plano de ese autor que buscamos.
     * @param author - String nombre del pintor que queremos obtener sus obras o datos.
     * @param plano - String nombre del plano que queremos obtener.
     * @return Set(Blueprint) - El cual contiene toda la información de los BluePrint por su autor y un plano
     *
     */
    public Set<Blueprint> getAllBlueprintsByAuthorAndPlano(String author, String plano);

    /**
     * Método que permite actualizar los puntos de un BluePrint dado su nombre y autor, como los puntos a actualziar.
     * @param author - String nombre del pintor que queremos obtener sus obras o datos.
     * @param name - String nombre del plano que queremos obtener.
     * @param newBlueprint - Blueprint, contiene los nuevos puntos a actualizar.
     * @return
     */
    public Set<Blueprint> editBlueprint(String author, String name, Blueprint newBlueprint);
}
