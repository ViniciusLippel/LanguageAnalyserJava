package compiler;

import java.util.ArrayList;

public class Parser {
	
	private ArrayList<Token> tokenList;
	private ArrayList<ArrayList<Token>> groupTokens;
	private ArrayList<String> errorList;
	
	public Parser(ArrayList<Token> tokenList) {
		this.tokenList = tokenList;
		this.groupTokens = new ArrayList<ArrayList<Token>>();
		this.errorList = new ArrayList<String>();
	}
	
	public ArrayList<Token> getTokenList() {
		return tokenList;
	}

	public void setTokenList(ArrayList<Token> tokenList) {
		this.tokenList = tokenList;
	}

	public ArrayList<ArrayList<Token>> getGroupTokens() {
		return groupTokens;
	}

	public void setGroupTokens(ArrayList<ArrayList<Token>> groupTokens) {
		this.groupTokens = groupTokens;
	}
	
	public ArrayList<String> getErrorList() {
		return errorList;
	}

	public void setErrorList(ArrayList<String> errorList) {
		this.errorList = errorList;
	}

	public void parse() {
		this.groupTokens();
		this.syntaxAnalisis();
	}

	public void groupTokens() {
		
		ArrayList<Token> subTokenList = new ArrayList<Token>();
		
		for(int i=0; i<this.tokenList.size(); i++) {
			
			if(tokenList.get(i).getType() != ETypes.SEMICOLON && tokenList.get(i).getType() != ETypes.COLON)
				subTokenList.add(tokenList.get(i));
			
			else {
				
				subTokenList.add(tokenList.get(i));
				this.groupTokens.add(subTokenList);
				subTokenList = new ArrayList<Token>();
			}
		}
	}
	
	public void syntaxAnalisis() {
		
		ArrayList<ArrayList<Token>> gt = this.groupTokens;
		
		for(int i=0; i<gt.size(); i++) {
			
			ArrayList<Token> line = gt.get(i);
			
			//IDENTIFIER
			if(isIdentifier(line.get(0))) {
				
				if(isAssignment(line.get(1))) {
					if(isOperand(line.get(2))) {
						if(!isSemicolon(line.get(3))) {
							if(isOperator(line.get(3))) {
								if(!isOperand(line.get(4)))
									this.errorList.add("Line "+(i+1)+": Missing second operand");
							}
							else
								this.errorList.add("Line "+(i+1)+": Missing operator");
						}
					}
					else
						this.errorList.add("Line "+(i+1)+": Missing first operand");
				}
				
				else 
					this.errorList.add("Line "+(i+1)+": Identifier must come with assignment");
			}
			
			
			//KEYWORD IF
			else if(isKeyword(line.get(0)) && line.get(0).getData().equals("if")) {
				this.verifyIf(line, i);
			}
			
			
			//KEYWORD ELSE
			if(isKeyword(line.get(0)) && line.get(0).getData().equals("else")) {
				
				if(gt.get(i-1).get(0).getData().equals("if")) {
					if(line.get(1).getData().equals("if"))
						this.verifyIf(line, i);
				}
				else
					this.errorList.add("Line "+(i+1)+": Else must come after an if");
			}
			
			
			//KEYWORD FOR
			if(isKeyword(line.get(0)) && line.get(0).getData().equals("for")) {
				
				if(isParentheses(line.get(1))) {
					if(isOperand(line.get(2))) {
						if(isKeyword(line.get(3)) && line.get(3).getData().equalsIgnoreCase("in")) {
							if(isOperand(line.get(4))) {
								if(isParentheses(line.get(5))) {
									if(isColon(line.get(6)));
									else
										this.errorList.add("Line "+(i+1)+": Missing colon");
								}
								else
									this.errorList.add("Line "+(i+1)+": Missing parentheses");
							}
							else
								this.errorList.add("Line "+(i+1)+": Missing second operand");
						}
						else
							this.errorList.add("Line "+(i+1)+": Command 'for' must work with 'in' statement");
					}
					else
						this.errorList.add("Line "+(i+1)+": Missing first operand");
				}
				else
					this.errorList.add("Line "+(i+1)+": Missing parentheses");
			}
			
			
			//KEYWORD READ
			if(isKeyword(line.get(0)) && line.get(0).getData().equals("read")) {
				if(!isIdentifier(line.get(1)))
					this.errorList.add("Line "+(i+1)+": Missing read identifier");
			}
			
			//KEYWORD PRINT
			if(isKeyword(line.get(0)) && line.get(0).getData().equals("print")) {
				if(!isOperand(line.get(1)))
					this.errorList.add("Line "+(i+1)+": Missing print operand");
			}
			
		}
	}
	
	
	public void verifyIf(ArrayList<Token> line, int i) {
		
		if(isParentheses(line.get(1))) {
			if(isOperand(line.get(2))) {
				if(isRelOperator(line.get(3))) {
					if(isOperand(line.get(4))) {
						if(isParentheses(line.get(5)));
						else
							this.errorList.add("Line "+(i+1)+": Missing parentheses");
					}
					else
						this.errorList.add("Line "+(i+1)+": Missing second operand");
				}
				else
					this.errorList.add("Line "+(i+1)+": Missing relational operator");
			}
			else
				this.errorList.add("Line "+(i+1)+": Missing first operand");
		}
		else
			this.errorList.add("Line "+(i+1)+": Missing parentheses");
	}
	
	
	public boolean isIdentifier(Token t) {
		if(t.getType() == ETypes.IDENTIFIER)
			return true;
		return false;
	}
	
	public boolean isAssignment(Token t) {
		if(t.getType() == ETypes.ASSIGNMENT)
			return true;
		return false;
	}
	
	public boolean isOperator(Token t) {
		if(t.getType() == ETypes.OPERATOR)
			return true;
		return false;
	}
	
	public boolean isRelOperator(Token t) {
		if(t.getType() == ETypes.REL_OPERATOR)
			return true;
		return false;
	}
	
	public boolean isOperand(Token t) {
		if(isInteger(t) || isFloat(t) || isIdentifier(t) || isLiteral(t))
			return true;
		return false;
	}
	
	public boolean isLiteral(Token t) {
		if(t.getType() == ETypes.LITERAL)
			return true;
		return false;
	}
	
	public boolean isInteger(Token t) {
		if(t.getType() == ETypes.INTEGER)
			return true;
		return false;
	}
	
	public boolean isFloat(Token t) {
		if(t.getType() == ETypes.FLOAT)
			return true;
		return false;
	}
	
	public boolean isKeyword(Token t) {
		if(t.getType() == ETypes.KEYWORD)
			return true;	
		return false;
	}
	
	public boolean isSemicolon(Token t) {
		if(t.getType() == ETypes.SEMICOLON)
			return true;
		return false;
	}
	
	public boolean isColon(Token t) {
		if(t.getType() == ETypes.COLON)
			return true;
		return false;
	}
	
	public boolean isBraces(Token t) {
		if(t.getType() == ETypes.BRACES)
			return true;
		return false;
	}
	
	public boolean isParentheses(Token t) {
		if(t.getType() == ETypes.PARENTHESES)
			return true;
		return false;
	}
	
}
