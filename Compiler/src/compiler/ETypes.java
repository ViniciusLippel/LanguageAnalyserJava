package compiler;

public enum ETypes {
	IDENTIFIER("Identifier"), 
	KEYWORD("Keyword"), 
	INTEGER("Integer"), 
	FLOAT("Float"), 
	REL_OPERATOR("Relational_operator"), 
	OPERATOR("Operator"),
	ASSIGNMENT("Assignment"),
	LITERAL("String_literal"),
	SEMICOLON("Semicolon"),
	COLON("Colon"),
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
