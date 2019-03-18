package edu.kit.informatik.data;

import java.util.ArrayList;
import java.util.HashMap;

import edu.kit.informatik.Terminal;

public class MaterialDataBase {
    /** a list of all components and assamblies */
    private ArrayList<Component> allComponents = new ArrayList<Component>();

    /**
     * constructor for the database
     */
    public MaterialDataBase() {

    }

    /**
     * add a new component to the database
     * @param name the name of the new Component
     */
    public void addComponent(String name) {

    }

    /**
     * add a new component to the database
     * @param name the name of the new Component
     * @param parts a hash map of parts that contains all parts of the component and there amounts
     */
    public void addAssembly(String name, HashMap<Component, Integer> parts) {

    }

    /**
     * test code
     * prints out every existing part of the database
     */
    public void printExistingParts() {
        for (int i = 0; i < allComponents.size(); i++) {
            Terminal.printLine(allComponents.get(i).getName());
        }
    }
}