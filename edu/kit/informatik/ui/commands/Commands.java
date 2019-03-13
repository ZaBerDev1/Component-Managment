package edu.kit.informatik.ui.commands;

import edu.kit.informatik.exceptions.InputException;
import edu.kit.informatik.Constant;

/**
 * contains all valid commands as well as there signature and behavior
 */
public enum Commands {

    /**
     * creates a new material list for the assembly
     */
    ADDASSEMBLY(Constant.CommandRegex.ADDASSEMBLY) {
        @Override
        public String execute(String parameters) {
            return "execute unfinshed";
        }
    },

    /**
     * removes a material list of an assembly
     */
    REMOVEASSEMBLY(Constant.CommandRegex.REMOVEASSEMBLY) {
        @Override
        public String execute(String parameters) {
            return "remove unfinished";
        }
    },

    /**
     * prints a material list of an assembly
     */
    PRINTASSEMBLY(Constant.CommandRegex.PRINTASSEMBLY) {
        @Override
        public String execute(String parameters) {
            return "print unfinished";
        }
    },

    /**
     * gets all assemblies
     */
    GETASSEMBLIES(Constant.CommandRegex.GETASSEMBLIES) {
        @Override
        public String execute(String parameters) {
            return "getAssemblies unfinished";
        }
    },

    /**
     * gets a material list of an assembly
     */
    GETCOMPONENTS(Constant.CommandRegex.GETCOMPONENTS) {
        @Override
        public String execute(String parameters) {
            return "getComponents unfinished";
        }
    },

    /**
     * adds a part to an excisting assembly
     */
    ADDPART(Constant.CommandRegex.ADDPART) {
        @Override
        public String execute(String parameters) {
            return "addPart unfinished";
        }
    },

    /**
     * removes a part form an excisting assembly
     */
    REMOVEPART(Constant.CommandRegex.REMOVEPART) {
        @Override
        public String execute(String parameters) {
            return "removePart unfinished";
        }
    },

    /**
     * closes the program
     */
    QUIT(Constant.CommandRegex.QUIT) {
        @Override
        public String execute(String parameters) throws InputException {
            return "exit";
        }
    };

    /** the form of the command */
    private String signature;
    /** the id of the command */
    private String idWord;

    /**
     * constructor for the commands
     * @param signature the form of the command
     */
    Commands(String signature) {
            this.signature = signature;
            String signatureWithoutEndSymbol = signature.replaceAll("[$]", "");
            this.idWord = signatureWithoutEndSymbol.split(" ")[0].substring(1);
    }

    /**
     * getter for the signature of a command
     * @return the signature of the command
     */
    public String getSignature() {
        return this.signature;
    }

    /**
     * getter for the idWord of a command
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
     * @param parameters the inputed parameters
     * @return the terminal output
     * @throws InputException if the inputed commands are not valid
     */
    public abstract String execute(String parameters) throws InputException;
}