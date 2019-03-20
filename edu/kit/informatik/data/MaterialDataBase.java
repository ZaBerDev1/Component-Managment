package edu.kit.informatik.data;

import java.util.ArrayList;
import java.util.Comparator;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.exceptions.ComponentException;
import edu.kit.informatik.exceptions.MaterialDataBaseException;
import edu.kit.informatik.exceptions.MaterialListException;

public class MaterialDataBase {
    /** a list of all components and assamblies */
    private ArrayList<Component> allComponents = new ArrayList<Component>();
    private Comparator<Component> componentComparator;

    /**
     * constructor for the database that also creates an comparator
     */
    public MaterialDataBase() {
        componentComparator = new Comparator<Component>() {
            public int compare(Component c1, Component c2) {
                return c1.getName().compareTo(c2.getName());
            }
        };
    }

    /**
     * add a new component to the database
     * 
     * @param name  the name of the new Component
     * @param parts a hash map of parts that contains all parts of the component and
     *              there amounts
     * @throws MaterialListException     if one of the parts is not available in the
     *                                   MaterialList
     * @throws MaterialDataBaseException if the added assembly exists already
     */
    public void addAssembly(String name, MaterialList parts) throws MaterialListException, MaterialDataBaseException {
        Component assembly = new Component(name);
        Component[] partArray = parts.componentSet();
        for (int i = 0; i < parts.size(); i++) {
            Component currComponent = partArray[i];
            // check if one of the parts already exists so no new one gets created
            if (allComponents.contains(partArray[i])) {
                int indexAll = allComponents.indexOf(partArray[i]);
                assembly.addPart(allComponents.get(indexAll), parts.getAmount(currComponent));
            } else {
                assembly.addPart(currComponent, parts.getAmount(currComponent));
            }
        }
        if (allComponents.contains(assembly)) {
            throw new MaterialDataBaseException("The an assembly named " + assembly.getName() + " exists already.");
        }
        if (assembly.checkForCycle()) {
            throw new MaterialDataBaseException("This assembly would create a cycle.");
        }
        allComponents.add(assembly);
        allComponents.sort(componentComparator);
    }

    /**
     * removess a component from allComponents
     * 
     * @param name the name of the component
     * @throws MaterialDataBaseException if the component doesn't exist in the
     *                                   database.
     */
    public void removeAssembly(String name) throws MaterialDataBaseException {
        // works because the equal method only compares the name not the parts
        Component component = new Component(name);
        if (!allComponents.contains(component)) {
            throw new MaterialDataBaseException("A component with the name " + name + " doesn't exist.");
        }
        allComponents.remove(component);
    }

    /**
     * prints out all parts of an assembly
     * 
     * @param name the name of the assembly
     * @return all parts and there amount in a String
     * @throws MaterialDataBaseException if there is no assembly with the given name
     */
    public String printAssembly(String name) throws MaterialDataBaseException {
        // works because the equal method only compares the name not the parts
        Component refAssembly = new Component(name);
        if (!allComponents.contains(refAssembly)) {
            throw new MaterialDataBaseException("A component with the name " + name + " doesn't exist.");
        }
        int indexOfRefAssembly = allComponents.indexOf(refAssembly);
        Component realAssembly = allComponents.get(indexOfRefAssembly);
        Component[] partArray = realAssembly.getAllComponents();
        String output = "";
        for (int i = 0; i < partArray.length; i++) {
            try {
                output += partArray[i].getName() + ":" + realAssembly.getAmount(partArray[i]);
            } catch (ComponentException e) {
                return e.getMessage();
            } catch (MaterialListException e) {
                return e.getMessage();
            }
            if (i != partArray.length - 1) {
                output += ";";
            }
        }
        return output;
    }

    /**
     * adds an amount of parts to an assembly
     * @param nameAssembly the name of the assembly
     * @param name the name of the part
     * @param amount the amount which should be added
     * @throws MaterialDataBaseException when there is no assembly with that name
     * @throws MaterialListException if the amout gets over the limit
     */
    public void addPart(String nameAssembly, String name, int amount)
            throws MaterialDataBaseException, MaterialListException {
        // works because the equal method only compares the name not the parts
        Component refAssembly = new Component(nameAssembly);
        if (!allComponents.contains(refAssembly)) {
            throw new MaterialDataBaseException("A component with the name " + nameAssembly + " doesn't exist.");
        }
        int indexOfRefAssembly = allComponents.indexOf(refAssembly);
        Component realAssembly = allComponents.get(indexOfRefAssembly);
        Component part = new Component(name);
        realAssembly.addPart(part, amount);
    }

    /**
     * removes an amount of parts from an assembly
     * @param nameAssembly the name of the assembly
     * @param name the name of the part
     * @param amount the amount which should be removed
     * @throws MaterialDataBaseException when there is no assembly with that name
     * @throws MaterialListException if the amount get lower than 0
     */
    public void removePart(String nameAssembly, String name, int amount)
            throws MaterialDataBaseException, MaterialListException {
        // works because the equal method only compares the name not the parts
        Component refAssembly = new Component(nameAssembly);
        if (!allComponents.contains(refAssembly)) {
            throw new MaterialDataBaseException("A component with the name " + nameAssembly + " doesn't exist.");
        }
        int indexOfRefAssembly = allComponents.indexOf(refAssembly);
        Component realAssembly = allComponents.get(indexOfRefAssembly);
        Component part = new Component(name);
        realAssembly.removePart(part, amount);
    }

    /**
     * test code prints out every existing part of the database
     */
    public void printExistingParts() {
        for (int i = 0; i < allComponents.size(); i++) {
            Terminal.printLine(allComponents.get(i).getName());
        }
    }
}