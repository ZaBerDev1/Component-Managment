package edu.kit.informatik.data;

import edu.kit.informatik.exceptions.ComponentException;
import edu.kit.informatik.exceptions.MaterialListException;

public class Component {
    /** the name of the component */
    private String name = "";
    private boolean isAssembly = false;
    private MaterialList materialList = new MaterialList();
    
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
     * @throws MaterialListException if the materialList doesn't contain the component
     */
    public int getAmount(Component component) throws ComponentException, MaterialListException {
        if (!checkIsComponent(component)) {
            throw new ComponentException("This component is not a piece of " + name + ".");
        }
        return materialList.getAmount(component);
    }

    /**
     * returns an array that contains all components
     * @return all components in an array
     */
    public Component[] getAllComponents() {

        return materialList.componentSet();
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
        return materialList.contains(component);
    }

    /**
     * adds an amount of components to the assembly
     * @param component the new component which should be added to the list of parts
     * @param amount the amount of pieces that are added form this kind
     * @throws MaterialListException if the amount of the component gets higher than the max amount
     */
    public void addPart(Component component, int amount) throws MaterialListException {
        isAssembly = true;
        materialList.addComponent(component, amount);
    }

    public boolean checkForCycle() throws ComponentException {
        boolean cycleFree = true;
        if (!isAssembly) {
            throw new ComponentException("This is not an assembly so it obviously can't have any cycle.");
        }
        
        return cycleFree;
    }

    @Override
    public boolean equals(Object component) {
        if (!(component instanceof Component)) {
            return false;
        }
        return this.getName().equals(((Component) component).getName());
    }
}