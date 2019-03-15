package edu.kit.informatik.data;

import java.util.HashMap;

class Component {
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
     */
    public int getAmount(Component component) {
        return materialList.get(component);
    }
}