package edu.kit.informatik.exceptions;

import edu.kit.informatik.Constant;

public class MaterialDataBaseException extends Exception {
    private static final long serialVersionUID = 3;

    /**
     * constructor
     * 
     * @param errorMessage the speciefied error message
     */
    public MaterialDataBaseException(String errorMessage) {
        super(Constant.EXCEPTIONBEGINNING + errorMessage);
    }
}