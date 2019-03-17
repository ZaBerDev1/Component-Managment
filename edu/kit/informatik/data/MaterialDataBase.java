package edu.kit.informatik.data;

import java.util.ArrayList;
import java.util.HashMap;

import edu.kit.informatik.exceptions.ComponentException;

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
        Component component = new Component(name);
        allComponents.add(component);
    }

    /**
     * add a new component to the database
     * @param name the name of the new Component
     * @param parts a hash map of parts that contains all parts of the component and there amounts
     * @throws ComponentException UNKOWN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
    public void addAssembly(String name, HashMap<Component, Integer> parts) throws ComponentException {
        Component component = new Component(name);
        for (Component curr : parts.keySet()) {
            component.addPart(curr, parts.get(curr));
        }
        allComponents.add(component);
    }
}