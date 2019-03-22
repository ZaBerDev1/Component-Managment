package edu.kit.informatik.ui.commands;

import edu.kit.informatik.Constant;
import edu.kit.informatik.data.*;
import edu.kit.informatik.exceptions.InputException;
import edu.kit.informatik.exceptions.MaterialDataBaseException;
import edu.kit.informatik.exceptions.MaterialListException;

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
            if (!parameters.contains("=") || !parameters.contains(":")) {
                return "The command should like addAssembly nameAssembly=amount1:name1;amount2:name2;...";
            }
            String equalSympbolSplited[] = parameters.split("="); // 1 is the assembly name
            String componentPairs[] = equalSympbolSplited[1].split(";");
            MaterialList parts = new MaterialList();
            for (int i = 0; i < componentPairs.length; i++) {
                String[] splitedComponentPair = componentPairs[i].split(":");
                int amount = 0;
                try {
                    amount = Integer.parseInt(splitedComponentPair[0]);
                } catch (NumberFormatException e) {
                    return "The amount of each part should be a number between 0 and 999";
                }
                Component component = new Component(splitedComponentPair[1]);
                if (parts.contains(component)) {
                    return "A part was mentioned twice.";
                }
                try {
                    parts.addComponent(component, amount);
                } catch (MaterialListException e) {
                    return e.getMessage();
                }
            }
            try {
                materialDataBase.addAssembly(equalSympbolSplited[0], parts);
            } catch (MaterialDataBaseException e) {
                return e.getMessage();
            } catch (MaterialListException e) {
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
            try {
                materialDataBase.removeAssembly(parameters);
            } catch (MaterialDataBaseException e) {
                return e.getMessage();
            }
            return Constant.OK;
        }
    },

    /**
     * prints a material list of an assembly
     */
    PRINTASSEMBLY(Constant.CommandRegex.PRINTASSEMBLY) {
        @Override
        public String execute(String parameters, MaterialDataBase materialDataBase) {
            try {
                return materialDataBase.printAssembly(parameters);
            } catch (MaterialDataBaseException e) {
                return e.getMessage();
            }
        }
    },

    /**
     * gets all assemblies
     */
    GETASSEMBLIES(Constant.CommandRegex.GETASSEMBLIES) {
        @Override
        public String execute(String parameters, MaterialDataBase materialDataBase) {
            try {
                return materialDataBase.getAssembly(parameters);
            } catch (MaterialDataBaseException e) {
                return e.getMessage();
            }
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
     * adds a part to an excisting assembly addPart X+1:A
     */
    ADDPART(Constant.CommandRegex.ADDPART) {
        @Override
        public String execute(String parameters, MaterialDataBase materialDataBase) {
            String[] splitedPlus = parameters.split("\\+");
            String[] wholeSplited = splitedPlus[1].split(":");
            int amount = 0;
            String name = wholeSplited[1];
            String nameAssembly = splitedPlus[0];
            try {
                amount = Integer.parseInt(wholeSplited[0]);
            } catch (NumberFormatException e) {
                return e.getMessage();
            }
            try {
                materialDataBase.addPart(nameAssembly, name, amount);
            } catch (MaterialDataBaseException e) {
                return e.getMessage();
            } catch (MaterialListException e) {
                return e.getMessage();
            }
            return Constant.OK;
        }
    },

    /**
     * removes a part form an excisting assembly
     */
    REMOVEPART(Constant.CommandRegex.REMOVEPART) {
        @Override
        public String execute(String parameters, MaterialDataBase materialDataBase) {
            String[] splitedPlus = parameters.split("\\-");
            String[] wholeSplited = splitedPlus[1].split(":");
            int amount = 0;
            String name = wholeSplited[1];
            String nameAssembly = splitedPlus[0];
            try {
                amount = Integer.parseInt(wholeSplited[0]);
            } catch (NumberFormatException e) {
                return e.getMessage();
            }
            try {
                materialDataBase.removePart(nameAssembly, name, amount);
            } catch (MaterialDataBaseException e) {
                return e.getMessage();
            } catch (MaterialListException e) {
                return e.getMessage();
            }
            return Constant.OK;
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