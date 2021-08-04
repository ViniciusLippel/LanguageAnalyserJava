package main;

import java.util.ArrayList;

public class Parser {
	
	private ArrayList<Token> tokenList;
	private ArrayList<ArrayList<Token>> groupTokens;
	
	public Parser(ArrayList<Token> tokenList) {
		this.tokenList = tokenList;
		this.groupTokens = new ArrayList<ArrayList<Token>>();
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



	public void groupTokens() {
		ArrayList<Token> subTokenList = new ArrayList<Token>();
		for(int i=0; i<this.tokenList.size(); i++) {
			if(tokenList.get(i).getType() != ETypes.SEMICOLON)
				subTokenList.add(tokenList.get(i));
			else {
				subTokenList.add(tokenList.get(i));
				this.groupTokens.add(subTokenList);
				subTokenList = new ArrayList<Token>();
			}
		}
	}
	
	
}
