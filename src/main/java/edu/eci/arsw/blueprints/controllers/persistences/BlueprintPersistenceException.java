package edu.eci.arsw.blueprints.controllers.persistences;

public class BlueprintPersistenceException extends Exception{

    public BlueprintPersistenceException(String message) {
        super(message);
    }

    public BlueprintPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

}