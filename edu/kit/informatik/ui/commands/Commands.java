package edu.kit.informatik.ui.commands;

import java.util.HashMap;

import edu.kit.informatik.Constant;
import edu.kit.informatik.data.*;
import edu.kit.informatik.exceptions.ComponentException;
import edu.kit.informatik.exceptions.InputException;

/**
 * contains all valid commands as well as there signature and behavior
 */
public enum Commands {

    /**
     * creates a new material list for the assembly
     */
    ADDASSEMBLY(Constant.CommandRegex.ADDASSEMBLY) {
        @Override
        public String execute(String parameters, MaterialDataBase materialDataBase) {
            if (!parameters.contains("=") || !parameters.contains(":") || !parameters.contains(";")) {
                return "The command should like addAssembly nameAssembly=amount1:name1;amount2:name2;...";
            }
            String equalSympbolSplited[] = parameters.split("="); // 1 is the assembly name
            String componentPairs[] = equalSympbolSplited[1].split(";");
            HashMap<Component, Integer> parts = new HashMap<Component, Integer>();
            for (int i = 0; i < componentPairs.length; i++) {
                String[] splitedComponentPair = componentPairs[i].split(":");
                int amount = 0;
                try {
                    amount = Integer.parseInt(splitedComponentPair[0]);
                } catch (NumberFormatException e) {
                    return "The amount of each part should be a number between 0 and 999";
                }
                Component component = new Component(splitedComponentPair[1]);
                parts.put(component, amount);
            }
            try {
                materialDataBase.addAssembly(equalSympbolSplited[0], parts);
            } catch (ComponentException e) {
                return e.getMessage();
            }
            return Constant.OK;
        }
    },

    /**
     * removes a material list of an assembly
     */
    REMOVEASSEMBLY(Constant.CommandRegex.REMOVEASSEMBLY) {
        @Override
        public String execute(String parameters, MaterialDataBase materialDataBase) {
            return "remove unfinished";
        }
    },

    /**
     * prints a material list of an assembly
     */
    PRINTASSEMBLY(Constant.CommandRegex.PRINTASSEMBLY) {
        @Override
        public String execute(String parameters, MaterialDataBase materialDataBase) {
            return "print unfinished";
        }
    },

    /**
     * gets all assemblies
     */
    GETASSEMBLIES(Constant.CommandRegex.GETASSEMBLIES) {
        @Override
        public String execute(String parameters, MaterialDataBase materialDataBase) {
            return "getAssemblies unfinished";
        }
    },

    /**
     * gets a material list of an assembly
     */
    GETCOMPONENTS(Constant.CommandRegex.GETCOMPONENTS) {
        @Override
        public String execute(String parameters, MaterialDataBase materialDataBase) {
            return "getComponents unfinished";
        }
    },

    /**
     * adds a part to an excisting assembly
     * addPart X+1:A
     */
    ADDPART(Constant.CommandRegex.ADDPART) {
        @Override
        public String execute(String parameters, MaterialDataBase materialDataBase) {
            //materialDataBase.addComponent(name);
            return "unfinished";
        }
    },

    /**
     * removes a part form an excisting assembly
     */
    REMOVEPART(Constant.CommandRegex.REMOVEPART) {
        @Override
        public String execute(String parameters, MaterialDataBase materialDataBase) {
            return "removePart unfinished";
        }
    },

    /**
     * closes the program
     */
    QUIT(Constant.CommandRegex.QUIT) {
        @Override
        public String execute(String parameters, MaterialDataBase materialDataBase) throws InputException {
            return "exit";
        }
    };

    /** the form of the command */
    private String signature;
    /** the id of the command */
    private String idWord;

    /**
     * constructor for the commands
     * 
     * @param signature the form of the command
     */
    Commands(String signature) {
        this.signature = signature;
        String signatureWithoutEndSymbol = signature.replaceAll("[$]", "");
        this.idWord = signatureWithoutEndSymbol.split(" ")[0].substring(1);
    }

    /**
     * getter for the signature of a command
     * 
     * @return the signature of the command
     */
    public String getSignature() {
        return this.signature;
    }

    /**
     * getter for the idWord of a command
     * 
     * @return the idWord of the command
     */
    public String getIdWord() {
        return this.idWord;
    }

    @Override
    public String toString() {
        return this.idWord;
    }

    /**
     * executes the command
     * 
     * @param parameters       the inputed parameters
     * @param materialDataBase the database
     * @return the terminal output
     * @throws InputException if the inputed commands are not valid
     */
    public abstract String execute(String parameters, MaterialDataBase materialDataBase) throws InputException;
}