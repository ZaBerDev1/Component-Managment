package edu.kit.informatik;

/**
 * a class that contains all globally used constants
 */
public final class Constant {
    /** used to output OK */
    public static final String OK = "OK";
    /** the start of every Exception */
    public static final String EXCEPTIONBEGINNING = "404 ";
    /** used to output the word COMPONENT */
    public static final String COMPONENT = "COMPONENT";
    /** a int that shows that an error occured */
    public static final int ERRORINT = -1;

    /**
     * returns all commands as a String with muliple lines
     * 
     * @return the commands in one String
     */
    public String getAllCommands() {
        return "Not finished yet.";
    }

    /**
     * returns the regex for any Exception
     * 
     * @return the regex as a String
     */
    public static String getExceptionBeginningRegex() {
        return "^" + EXCEPTIONBEGINNING + ".*";
    }

    /**
     * class that contains all regex of the commands
     */
    public final class CommandRegex {
        /** the end symbol for the regex */
        public static final String ENDSYMBOL = "$";
        /** regex for the name of the assemblies and components */
        public static final String NAME = "[A-Za-z]+";
        /** regex for the amount of components in a assembley */
        public static final String AMOUNT = "(\\d{3}|\\d{2}|\\d)";
        /** the regex for the addAssembly command */
        public static final String ADDASSEMBLY = "^addAssembly " + NAME + "=" + AMOUNT + ":" + NAME + "(;" + AMOUNT
                + ":" + NAME + ")*" + ENDSYMBOL;
        /** the regex for the removeAssembly command */
        public static final String REMOVEASSEMBLY = "^removeAssembly " + NAME + ENDSYMBOL;
        /** the regex for the printAssembly command */
        public static final String PRINTASSEMBLY = "^printAssembly " + NAME + ENDSYMBOL;
        /** the regex for the getAssembly command */
        public static final String GETASSEMBLIES = "^getAssemblies " + NAME + ENDSYMBOL;
        /** the regex for the getAssembly command */
        public static final String GETCOMPONENTS = "^getComponents " + NAME + ENDSYMBOL;
        /** the regex for the addPart command */
        public static final String ADDPART = "^addPart " + NAME + "\\+" + AMOUNT + ":" + NAME + ENDSYMBOL;
        /** the regex for the removePart command */
        public static final String REMOVEPART = "^removePart " + NAME + "\\-" + AMOUNT + ":" + NAME + ENDSYMBOL;
        /** the regex for the quit command */
        public static final String QUIT = "^quit$";
    }
}