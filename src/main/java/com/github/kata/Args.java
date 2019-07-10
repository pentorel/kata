package com.github.kata;

import java.util.HashMap;
import java.util.Map;

public class Args {
    private String schema;
    private String args;
    private Map<Character, Parser> parserMap = new HashMap<Character, Parser>();
    public Args(String schema,String args) {
        this.schema = schema;
        this.args = args;
        parseSchema();
        parseArgs();
    }

    private void fetalError(String msg) {
        throw new ArgsException(msg);
    }
    private void parseSchema() {
        String[] schemas = schema.split(",");
        if(schemas != null && schemas.length > 0) {
            for(String schema : schemas)
                parseSchemaElement(schema.trim());
        }
    }

    private void parseArgs() {
        String[] argItems = args.split("\\-");
        for(String item : argItems) {
            if(item.trim().length() < 1) continue;
            parseArgItem(item);
        }
    }

    private void parseArgItem(String item) {
        char name = item.charAt(0);
        Parser p = parserMap.get(name);
        if (p == null) fetalError("cannot found the parser for the " + name);
        p.setItem(item.substring(1));
    }

    private void parseSchemaElement(String schema) {
        if(schema.length() < 1) fetalError("Expected schema element,but not found");
        char name = schema.charAt(0);
        if (!Character.isLetter(name)) fetalError("flagName must be letter,but found " + name);
        String reset = schema.substring(1);
        if(reset.length() == 0) {
            parserMap.put(name, new BooleanParser());
        } else if ("*".equals(reset)) {
            parserMap.put(name, new StringParser());
        } else if ("#".equals(reset)) {
            parserMap.put(name, new IntegerParser());
        } else {
            fetalError("Unknown argument type " + reset + " found");
        }
    }

    public int getInt(char flagName) {
        IntegerParser p = (IntegerParser)parserMap.get(flagName);
        return p.getValue();
    }

    public String getString(char flagName) {
        StringParser p = (StringParser)parserMap.get(flagName);
        return p.getValue();
    }

    public boolean getBoolean(char flagName) {
        if (!parserMap.containsKey(flagName)) return  false;
        Parser p = parserMap.get(flagName);
        if(!(p instanceof BooleanParser)) fetalError("expect boolean parser,but found " + p.getClass().getName());
        return ((BooleanParser)p).getValue();
    }

    public Map<Character, Parser> getParserMap() {
        return parserMap;
    }

    public static void main(String[] args) {


    }
}
