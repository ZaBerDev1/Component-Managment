package edu.kit.informatik.data;

import java.util.ArrayList;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.exceptions.MaterialDataBaseException;
import edu.kit.informatik.exceptions.MaterialListException;

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
     * @throws MaterialListException if one of the parts is not available in the MaterialList
     * @throws MaterialDataBaseException if the added assembly exists already
     */
    public void addAssembly(String name, MaterialList parts) throws MaterialListException, MaterialDataBaseException {
        Component assembly  = new Component(name);
        Component[] partArray = parts.componentSet();
        for (int i = 0; i < parts.size(); i++) {
            Component currComponent = partArray[i];
            assembly.addPart(currComponent, parts.getAmount(currComponent));   
        }
        if (allComponents.contains(assembly)) {
            throw new MaterialDataBaseException("The an assembly named " + assembly.getName() + " exists already.");
        }
        allComponents.add(assembly);
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