package edu.kit.informatik.data;

import java.util.Comparator;
import java.util.TreeMap;

import edu.kit.informatik.exceptions.MaterialListException;

public class MaterialList {
    /** list of all materials and there amount in a sorted way */
    TreeMap<Component, Integer> treeMap;
    private final int maxAmount = 999;

    /**
     * constructor that also creates an Comparator for the attribute treeMap
     */
    MaterialList() {
        // The new comparator is basicly the String comperator used on the name of each
        // Component
        Comparator<Component> comparator = new Comparator<Component>() {
            public int compare(Component c1, Component c2) {
                return c1.getName().compareTo(c2.getName());
            }
        };
        treeMap = new TreeMap<Component, Integer>(comparator);
    }

    /**
     * getter for the treeMap
     * 
     * @return the treeMap which contains the whole data
     */
    public TreeMap<Component, Integer> getTreeMap() {
        return treeMap;
    }

    /**
     * checks if the component is in the materialList
     * 
     * @param component the component which is searched for
     * @return if the materialList contains the component
     */
    public boolean contains(Component component) {
        return treeMap.containsKey(component);
    }

    /**
     * returns the amount of a component
     * 
     * @param component the component in which we are intrested in
     * @return the amount of the searched component
     * @throws MaterialListException if the materialList doesn't contain the
     *                               component
     */
    public int getAmount(Component component) throws MaterialListException {
        if (!contains(component)) {
            throw new MaterialListException("The material list does't contain the " + component.getName());
        }
        return treeMap.get(component);
    }

    /**
     * adds a component to the material list
     * 
     * @param component the new component
     * @param amount    the amount of the new component
     * @throws MaterialListException if the amount gets over the limit
     */
    public void addComponent(Component component, int amount) throws MaterialListException {
        int newAmount = amount;
        if (contains(component)) {
            // already existing
            newAmount += treeMap.get(component);
            if (newAmount > maxAmount) {
                throw new MaterialListException(
                        "It is not posible to have more than " + maxAmount + " pieces of one kind in a material list.");
            }
        }
        treeMap.put(component, newAmount);
    }

    /**
     * removes an speciefied amount of a certain component
     * @param component the component which should be removed
     * @param amount the speciefied amount
     * @throws MaterialListException if there is no component like this
     */
    public void remove(Component component, int amount) throws MaterialListException {
        int oldAmount = treeMap.get(component);
        if (oldAmount < amount) {
            // too many removed
            throw new MaterialListException("The amount of components should be lower than or equal than " + oldAmount);
        }
        if (oldAmount == amount) {
            // remove completly
            treeMap.remove(component);
        }
        if (oldAmount > amount) {
            // remove only the given amount not completly
            treeMap.put(component, oldAmount - amount);
        }
    }

    /**
     * returns the size of the material list
     * @return the size of the material list
     */
    public int size() {
        return treeMap.size();
    }

    /**
     * puts one of each component in an array
     * @return an array of Components that are all contained in the material list
     */
    public Component[] componentSet() {
        Component[] components = new Component[treeMap.size()];
        int i = 0;
        for (Component curr : treeMap.keySet()) {
            components[i] = curr;
            i++;
        }
        return components;
    }

    /**
     * test code returns the whole treeMap as a String in form of a list
     */
    @Override
    public String toString() {
        String output = "";
        for (Component entry : treeMap.keySet()) {
            output += "name: " + entry.getName() + " | amount: " + treeMap.get(entry);
        }
        return output;
    }
}