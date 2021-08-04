package main;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
	
	private ArrayList<Token> tokenList;
	private ArrayList<String> listCom;
	
	//Constructor
	public Lexer(List<String> listCom) {
		tokenList = new ArrayList<Token>();
		this.listCom = new ArrayList<>(listCom.size());
		this.listCom.addAll(listCom);
	}
	
	//Getters and Setters
	public ArrayList<Token> getTokenList() {
		return tokenList;
	}
	
	public void setTokenList(ArrayList<Token> tokenList) {
		this.tokenList = tokenList;
	}
	

	public ArrayList<String> getResWords() {
		return listCom;
	}
	
	public void setResWords(ArrayList<String> resWords) {
		this.listCom = resWords;
	}

	
	
	//Analisador
	public void Analyse(String str) {
		
		//StringBuilder para juntar símbolos que formarão um dado
		StringBuilder data = new StringBuilder();
		int i = 0;
		
		
		//Loop percorrendo o script
		while (i<str.length()-1) {
			
			char chr = str.charAt(i);
			
			//Identificando palavra
			if (isLetter(chr)) {
				ETypes type = ETypes.IDENTIFIER;
				while(!isSpace(chr) && !isSemicolon(chr) && !isParentheses(chr) && !isBraces(chr)) {
					data.append(String.valueOf(chr));
					i++;
					chr = str.charAt(i);
				}

				if(listCom.indexOf(data.toString()) != -1)
					type = ETypes.KEYWORD;
				
				this.tokenList.add(new Token(data.toString(), type));
				data = new StringBuilder();
			}
			
			
			//Identificando número
			else if (isNum(chr)) {
				
				ETypes type = ETypes.INTEGER;
				
				while(isNum(chr) || isPoint(chr) || isComma(chr)) {
					data.append(String.valueOf(chr));

					if((isPoint(chr) || isComma(chr)) && isNum(str.charAt(i+1)))
						type = ETypes.FLOAT;
					
					i++;
					chr = str.charAt(i);
					
					//Se possuir letras após os números será nome
					if(isLetter(chr)) {
						data.append(String.valueOf(chr));
						type = ETypes.IDENTIFIER;
					}
				}
				
				if(isSpace(chr) || isSemicolon(chr)) {
					this.tokenList.add(new Token(data.toString(), type));
					data = new StringBuilder();
				}
			}
			
			//Identificando literal (aspas duplas e simples)
			else if (isQuote(chr)) {
				
				data.append(str.substring(i+1, str.indexOf("\"", i+1)));
				this.tokenList.add(new Token(data.toString(), ETypes.LITERAL));
				
				data = new StringBuilder();
				i = str.indexOf("\"", i+1);
			}
			
			else if (isSimpleQuote(chr)) {
				
				data.append(str.substring(i+1, str.indexOf("\'", i+1)));
				this.tokenList.add(new Token(data.toString(), ETypes.LITERAL));
				
				data = new StringBuilder();
				i = str.indexOf("\'", i+1);
			}
			
			//Identificando comparação
			else if (isRelOperator(chr, str.charAt(i+1))) {
				
				data.append(chr);
				data.append(str.charAt(i+1));
				this.tokenList.add(new Token(data.toString(), ETypes.REL_OPERATOR));
				
				data = new StringBuilder();
				i++;
			}
			
			else if (isAssignment(chr)) {
				
				data.append(chr);
				this.tokenList.add(new Token(data.toString(), ETypes.ASSIGNMENT));
				
				data = new StringBuilder();
			}
			
			//Identificando operação
			else if (isOperator(chr)) {
				
				data.append(chr);
				this.tokenList.add(new Token(data.toString(), ETypes.OPERATOR));
				
				data = new StringBuilder();
			}
			
			if (isParentheses(chr)) {
				data.append(chr);
				this.tokenList.add(new Token(data.toString(), ETypes.PARENTHESES));
				
				data = new StringBuilder();
			}
			
			if (isBraces(chr)) {
				
				data.append(chr);
				this.tokenList.add(new Token(data.toString(), ETypes.BRACES));
				
				data = new StringBuilder();
			}
			
			//
			if (isSemicolon(chr)) {
				
				data.append(chr);
				this.tokenList.add(new Token(data.toString(), ETypes.SEMICOLON));
				
				data = new StringBuilder();
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
