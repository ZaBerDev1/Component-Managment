package edu.kit.informatik.data;

import java.util.ArrayList;

import edu.kit.informatik.exceptions.ComponentException;
import edu.kit.informatik.exceptions.MaterialDataBaseException;
import edu.kit.informatik.exceptions.MaterialListException;

public class Component {
    /** the name of the component */
    private String name = "";
    private boolean isAssembly = false;
    /** true if this component is not part of another assembly */
    private boolean isRoot = false;
    private MaterialList materialList = new MaterialList();

    /**
     * initalices an component (not an assembly)
     * 
     * @param name the name of the new component
     */
    public Component(String name) {
        this.name = name;
    }

    /**
     * getter for the name
     * 
     * @return the name of the component
     */
    public String getName() {
        return name;
    }

    /**
     * returns true if the component is an assembly
     * 
     * @return the variable isAssembly
     */
    public boolean getIsAssembly() {
        return isAssembly;
    }

    /**
     * setter for the attribute isAssembly
     * @param isAssembly sets the attribute isAssembly to the new value
     */
    public void setIsAssembly(boolean isAssembly) {
        this.isAssembly = isAssembly;
    }

    /**
     * getter for the attribute isRoot
     * 
     * @return the attribute isRoot
     */
    public boolean getIsRoot() {
        return isRoot;
    }

    /**
     * setter for the attribute isRoot
     * 
     * @param isRoot the new value of isRoot
     */
    public void setIsRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }

    /**
     * returns the amount of an given component of the assembly
     * 
     * @param component the component which should be part of the assembly
     * @return the amount of the searched component
     * @throws ComponentException    indicates if the component is not available
     * @throws MaterialListException if the materialList doesn't contain the
     *                               component
     */
    public long getAmount(Component component) throws ComponentException, MaterialListException {
        if (!checkIsComponent(component)) {
            throw new ComponentException("This component is not a piece of " + name + ".");
        }
        return materialList.getAmount(component);
    }

    /**
     * returns an array that contains all components
     * 
     * @return all components in an array
     */
    public Component[] getAllComponents() {

        return materialList.componentSet();
    }

    /**
     * checks if a component is part of an assembly
     * 
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
     * 
     * @param component the new component which should be added to the list of parts
     * @param amount    the amount of pieces that are added form this kind
     * @throws MaterialListException if the amount of the component gets higher than
     *                               the max amount
     */
    public void addPart(Component component, long amount) throws MaterialListException {
        isAssembly = true;
        materialList.addComponent(component, amount, true);
    }

    /**
     * removes an amount of component of the assembly
     * 
     * @param component the component which should be removed
     * @param amount    the amount of pieces which should be removed
     * @throws MaterialListException if the amount of the components is higher than
     *                               the actual amount
     */
    public void removePart(Component component, long amount) throws MaterialListException {
        materialList.remove(component, amount);
        if (materialList.size() == 0) {
            isAssembly = false;
        }
    }

    /**
     * checks for a posible cylce in the new assembly
     * 
     * @return true if a cycle was found
     * @throws MaterialDataBaseException if the part is only a component and not an
     *                                   assembly
     */
    public boolean checkForCycle() throws MaterialDataBaseException {
        if (!isAssembly) {
            throw new MaterialDataBaseException("This is not an assembly so it obviously can't have any cycle.");
        }
        ArrayList<Component> startComponent = new ArrayList<Component>();
        startComponent.add(this);
        return recursiveCycleCheck(startComponent, false);
    }

    private boolean recursiveCycleCheck(ArrayList<Component> previousComponents, boolean lastResult) {
        if (lastResult) {
            return true;
        }
        Component curr = previousComponents.get(previousComponents.size() - 1);
        // checks if curr by it self creats already a cycle
        for (int i = 0; i < previousComponents.size() - 1; i++) {
            if (previousComponents.get(i).equals(curr)) {
                return true;
            }
        }
        // checks currs parts for a cycle
        Component[] parts = new Component[0];
        if (curr.getIsAssembly()) {
            parts = curr.getAllComponents();
            for (int i = 0; i < parts.length; i++) {
                previousComponents.add(parts[i]);
                if (recursiveCycleCheck(previousComponents, false))
                    return true;
                previousComponents.remove(parts[i]);
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object component) {
        if (!(component instanceof Component)) {
            return false;
        }
        return this.getName().equals(((Component) component).getName());
    }

    @Override
    public int hashCode() {
        return name.compareTo("");
    }
}