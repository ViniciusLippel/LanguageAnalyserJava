package compiler;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
	
	private ArrayList<Token> tokenList;
	private ArrayList<String> keywordList;
	
	//Constructor
	public Lexer(List<String> keywordList) {
		tokenList = new ArrayList<Token>();
		this.keywordList = new ArrayList<>(keywordList.size());
		this.keywordList.addAll(keywordList);
	}
	
	//Getters and Setters
	public ArrayList<Token> getTokenList() {
		return tokenList;
	}
	
	public void setTokenList(ArrayList<Token> tokenList) {
		this.tokenList = tokenList;
	}
	

	public ArrayList<String> getResWords() {
		return keywordList;
	}
	
	public void setResWords(ArrayList<String> resWords) {
		this.keywordList = resWords;
	}

	
	
	//Analisador
	public void Analyse(String str) {
		
		//StringBuilder para juntar símbolos que formarão um lexema
		StringBuilder lexeme = new StringBuilder();
		
		
		//Loop percorrendo o script
		int i = 0;
		while (i<str.length()-1) {
			
			char chr = str.charAt(i);
			
			//Identificando palavra
			if (isLetter(chr)) {
				ETypes type = ETypes.IDENTIFIER;
				while(!isSpace(chr) && !isSemicolon(chr) && !isParentheses(chr) && !isBraces(chr)) {
					lexeme.append(String.valueOf(chr));
					i++;
					chr = str.charAt(i);
				}

				if(keywordList.indexOf(lexeme.toString()) != -1)
					type = ETypes.KEYWORD;
				
				this.tokenList.add(new Token(lexeme.toString(), type));
				lexeme = new StringBuilder();
			}
			
			
			//Identificando número
			else if (isNum(chr)) {
				
				ETypes type = ETypes.INTEGER;
				
				while(isNum(chr) || isPoint(chr) || isComma(chr)) {
					lexeme.append(String.valueOf(chr));

					if((isPoint(chr) || isComma(chr)) && isNum(str.charAt(i+1)))
						type = ETypes.FLOAT;
					
					i++;
					chr = str.charAt(i);
					
					//Se possuir letras após os números será nome
					if(isLetter(chr)) {
						lexeme.append(String.valueOf(chr));
						type = ETypes.IDENTIFIER;
					}
				}
				
				if(isSpace(chr) || isSemicolon(chr) || isParentheses(chr) || isBraces(chr)) {
					this.tokenList.add(new Token(lexeme.toString(), type));
					lexeme = new StringBuilder();
				}
			}
			
			//Identificando literal (aspas duplas e simples)
			else if (isQuote(chr)) {
				
				lexeme.append(str.substring(i+1, str.indexOf("\"", i+1)));
				this.tokenList.add(new Token(lexeme.toString(), ETypes.LITERAL));
				
				lexeme = new StringBuilder();
				i = str.indexOf("\"", i+1);
			}
			
			else if (isSimpleQuote(chr)) {
				
				lexeme.append(str.substring(i+1, str.indexOf("\'", i+1)));
				this.tokenList.add(new Token(lexeme.toString(), ETypes.LITERAL));
				
				lexeme = new StringBuilder();
				i = str.indexOf("\'", i+1);
			}
			
			//Identificando operador relacional
			else if (isRelOperator(chr, str.charAt(i+1))) {
				
				lexeme.append(chr);
				lexeme.append(str.charAt(i+1));
				this.tokenList.add(new Token(lexeme.toString(), ETypes.REL_OPERATOR));
				
				lexeme = new StringBuilder();
				i++;
			}
			
			else if (isAssignment(chr)) {
				
				lexeme.append(chr);
				this.tokenList.add(new Token(lexeme.toString(), ETypes.ASSIGNMENT));
				
				lexeme = new StringBuilder();
			}
			
			//Identificando operação
			else if (isOperator(chr)) {
				
				lexeme.append(chr);
				this.tokenList.add(new Token(lexeme.toString(), ETypes.OPERATOR));
				
				lexeme = new StringBuilder();
			}
			
			if (isParentheses(chr)) {
				lexeme.append(chr);
				this.tokenList.add(new Token(lexeme.toString(), ETypes.PARENTHESES));
				
				lexeme = new StringBuilder();
			}
			
			if (isBraces(chr)) {
				
				lexeme.append(chr);
				this.tokenList.add(new Token(lexeme.toString(), ETypes.BRACES));
				
				lexeme = new StringBuilder();
			}
			
			//
			if (isSemicolon(chr)) {
				
				lexeme.append(chr);
				this.tokenList.add(new Token(lexeme.toString(), ETypes.SEMICOLON));
				
				lexeme = new StringBuilder();
			}
			
			if (isColon(chr)) {
				
				lexeme.append(chr);
				this.tokenList.add(new Token(lexeme.toString(), ETypes.COLON));
				
				lexeme = new StringBuilder();
			}
			
			i++;
		}
	}
	
	
	
	//Verificadores símbolos
	public boolean isLetter(char c) {
		if (Character.isAlphabetic(c))
			return true;
		return false;
	}
	
	public boolean isSpace(char c) {
		if (c == ' ')
			return true;
		return false;
	}
	
	public boolean isSemicolon(char c) {
		if (c == ';')
			return true;
		return false;
	}
	
	public boolean isColon(char c) {
		if (c == ':')
			return true;
		return false;
	}
	
	public boolean isParentheses(char c) {
		if (c == '(' || c == ')')
			return true;
		return false;
	}
	
	public boolean isBraces(char c) {
		if (c == '{' || c == '}')
			return true;
		return false;
	}
	
	public boolean isQuote(char c) {
		if (c == '"')
			return true;
		return false;
	}
	
	public boolean isSimpleQuote(char c) {
		if (c == '\'')
			return true;
		return false;
	}
	
	public boolean isNum(char c) {
		if (Character.isDigit(c)) 
			return true;
		return false;
	}
	
	public boolean isPoint(char c) {
		if(c == '.')
			return true;
		return false;
	}
	
	public boolean isComma(char c){
		if(c == ',')
			return true;
		return false;
	}
	
	public boolean isAssignment(char c) {
		if(c=='=')
			return true;
		return false;
	}
	
	public boolean isOperator(char c) {
		if(c=='+' || c=='-' || c=='*' || c=='/')
			return true;
		return false;
	}
	
	public boolean isRelOperator(char c1, char c2) {
		String c = String.valueOf(c1)+String.valueOf(c2);
		if(c=="==" || c=="!=" || c.equals(">=") || c=="<=")
			return true;	
		return false;
	}
	
	public boolean isIn(char c1, char c2) {
		String c = String.valueOf(c1)+String.valueOf(c2);
		if(c.equalsIgnoreCase("in"))
			return true;	
		return false;
	}

	
	
	//toString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Analyser [tokenList=");
		builder.append(tokenList);
		builder.append("]");
		return builder.toString();
	}
	
	
}
