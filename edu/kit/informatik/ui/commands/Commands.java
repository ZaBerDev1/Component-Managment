package edu.kit.informatik.ui.commands;

import edu.kit.informatik.exceptions.InputException;
import edu.kit.informatik.Constant;

/**
 * contains all valid commands as well as there signature and behavior
 */
public enum Commands {

    /**
     * closes the program
     */
    QUIT(Constant.CommandRegex.QUITREGEX) {
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