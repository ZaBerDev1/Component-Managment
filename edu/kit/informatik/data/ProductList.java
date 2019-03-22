package edu.kit.informatik.data;

import edu.kit.informatik.exceptions.ComponentException;
import edu.kit.informatik.exceptions.MaterialListException;

public class ProductList {
    private MaterialList allParts;
    private Component assembly;

    /**
     * constructor that also creates the MaterialList allparts and sets the core assembly
     * @param assembly the core assembly which the product list is based around
     * @throws MaterialListException errors that can occure in the materialList operations
     * @throws ComponentException errors that can occure in the component operations
     */
    public ProductList(Component assembly) throws MaterialListException, ComponentException {
        this.assembly = assembly;
        allParts = startGetAssembly(assembly, 1);
    }

    /**
     * getter for the core assembly which the product list is based around
     * @return the core assembly
     */
    public Component getAssembly() {
        return assembly;
    }

    /**
     * a modiefied form of the toString method
     * if both parameters are false the function will return an empty string
     * @param printAssemblies if true it will add all assemblies
     * @param printComponents if false it will add all components
     * @return the product list as a String
     * @throws MaterialListException 
     */
    public String toString(boolean printAssemblies, boolean printComponents) throws MaterialListException {
        Component[] componentArray = allParts.componentSet();
        String output = "";
        for (int i = 0; i < componentArray.length; i++) {
            final String seperateSymbol = ";";
            //handels all assemblies
            if (componentArray[i].getIsAssembly() && printAssemblies) {
                output += componentArray[i].getName() + ":" + allParts.getAmount(componentArray[i]) + seperateSymbol;
            }
            //handels all components
            if (!componentArray[i].getIsAssembly() && printComponents) {
                output += componentArray[i].getName() + ":" + allParts.getAmount(componentArray[i]) + seperateSymbol;
            }
            if (i == componentArray.length - 1) {
                output = output.substring(0, output.length() - seperateSymbol.length());
            }
        }
        return output;
    }

    private MaterialList startGetAssembly(Component startComponent, int amount)
            throws MaterialListException, ComponentException {
        Component[] parts = startComponent.getAllComponents();
        MaterialList connectedAssemblys = new MaterialList();
        for (int i = 0; i < parts.length; i++) {
            connectedAssemblys.addComponent(parts[i], startComponent.getAmount(parts[i]));
        }
        MaterialList nMaterialList = recrusiveGetAssembly(connectedAssemblys);
        nMaterialList.multiplyList(amount);
        return nMaterialList;
    }

    private MaterialList recrusiveGetAssembly(MaterialList connectedAssemblys)
            throws MaterialListException, ComponentException {
        Component[] allComponents = connectedAssemblys.componentSet();
        MaterialList nConnectedAssemlbys = new MaterialList();
        nConnectedAssemlbys.merge(connectedAssemblys);
        for (int i = 0; i < allComponents.length; i++) {
            if (allComponents[i].getIsAssembly()) {
                nConnectedAssemlbys
                        .merge(startGetAssembly(allComponents[i], connectedAssemblys.getAmount(allComponents[i])));
            }
        }
        return nConnectedAssemlbys;
    }
}