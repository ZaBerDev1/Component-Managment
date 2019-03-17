package edu.kit.informatik.data;

import java.util.HashMap;

import edu.kit.informatik.exceptions.ComponentException;

public class Component {
    /** the name of the component */
    private String name = "";
    private boolean isAssembly = false;
    /** if it is a assembly the hash map holds all pieces of it and there amounts*/
    private HashMap<Component, Integer> materialList = new HashMap<Component, Integer>();
    
    /**
     * initalices an component (not an assembly)
     * @param name the name of the new component
     */
    public Component(String name) {
        this.name = name;
    }

    /**
     * getter for the name
     * @return the name of the component
     */
    public String getName() {
        return name;
    }

    /**
     * returns true if the component is an assembly
     * @return the variable isAssembly
     */
    public boolean getIsAssembly() {
        return isAssembly;
    }

    /**
     * returns the amount of an given component of the assembly
     * @param component the component which should be part of the assembly
     * @return the amount of the searched component
     * @throws ComponentException indicates if the component is not available
     */
    public int getAmount(Component component) throws ComponentException {
        if (!checkIsComponent(component)) {
            throw new ComponentException("This component is not a piece of " + name + ".");
        }
        return materialList.get(component);
    }

    /**
     * returns an array that contains all components
     * @return all components in an array
     */
    public Component[] getAllComponents() {
        Component[] components = new Component[materialList.size()];
        int i = 0;
        for (Component curr : materialList.keySet()) {
            components[i] = curr;
            i++;
        }
        return components;
    }

    /**
     * checks if a component is part of an assembly
     * @param component the component which should be part of the assembly
     * @return true if it is part of the assembly
     * @throws ComponentException indicates if the component is not an assembly
     */
    public boolean checkIsComponent(Component component) throws ComponentException {
        if (!isAssembly) {
            throw new ComponentException("This is not an assembly.");
        }
        return materialList.containsKey(component);
    }

    /**
     * adds an amount of components to the assembly
     * @param component the new component which should be added to the list of parts
     * @param amount the amount of pieces that are added form this kind
     */
    public void addPart(Component component, int amount) {
        isAssembly = true;
        materialList.put(component, amount);
    }

    public boolean checkForCycle() throws ComponentException {
        boolean cycleFree = true;
        if (!isAssembly) {
            throw new ComponentException("This is not an assembly.");
        }

        //unfinished!!!!!!!!!!!!


        return cycleFree;
    }
}