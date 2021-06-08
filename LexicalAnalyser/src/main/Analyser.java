package main;

import java.util.ArrayList;

public class Analyser {
	
	private ArrayList<String> tokenList;
	
	//Constructor
	public Analyser() {
		tokenList = new ArrayList<String>();
	}
	
	//Getters and Setters
	public ArrayList<String> getTokenList() {
		return tokenList;
	}
	public void setTokenList(ArrayList<String> tokenList) {
		this.tokenList = tokenList;
	}
	
	//Analyse
	public void Analyse(String str) {
		
		StringBuilder token = new StringBuilder();
		
		int i = 0;
		boolean num = false;
		
		while (i<str.length()-1) {
			
			char chr = str.charAt(i);
			
			//Word
			if (isLetter(chr)) {
				token.append(str.substring(i, i+1));
				if (isSpace(str.charAt(i+1))) {
					this.tokenList.add(token.toString());
					token = new StringBuilder();
				}
			}
			
			//Number
			else if (isNum(chr)) {
				num = true;
				token.append(str.substring(i, i+1));
				if (isSpace(str.charAt(i+1))) {
					this.tokenList.add(token.toString());
					token = new StringBuilder();
				}
			}
			
			//Semicolon
			else if (isSemicolon(chr)) {
				this.tokenList.add(token.toString());
				token = new StringBuilder();
				token.append(";");
				this.tokenList.add(token.toString());
				token = new StringBuilder();
			}
			
			//Quoted Text
			else if (isQuote(chr)) {
				token.append(str.substring(i+1, str.indexOf("\"", i+1)));
				this.tokenList.add(token.toString());
				token = new StringBuilder();
				i = str.indexOf("\"", i+1)-1;
			}
			
			i++;
		}
	}
	
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
	
	public boolean isQuote(char c) {
		if (c == '"')
			return true;
		return false;
	}
	
	public boolean isNum(char c) {
		if (Character.isDigit(c)) 
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
