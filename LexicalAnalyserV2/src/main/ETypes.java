package main;

public enum ETypes {
	LITERAL("String_literal"), 
	INTEGER("Integer"), 
	FLOAT("Float"), 
	IDENTIFIER("Identifier"), 
	KEYWORD("Keyword"), 
	REL_OPERATOR("Relational_operator"), 
	OPERATOR("Operator"),
	ASSIGNMENT("Assignment"),
	SEMICOLON("Semicolon"),
	BRACES("Braces"),
	PARENTHESES("Parentheses");
	
	private final String name;
	
	private ETypes(String name) { 
		this.name = name; 
	}
	
	@Override public String toString() {
		return name; 
	}
	
}
