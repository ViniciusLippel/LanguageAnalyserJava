package main;

import java.util.ArrayList;

public class Analyser {
	
	private ArrayList<Token> tokenList;
	private ArrayList<String> listCom;
	
	//Constructor
	public Analyser() {
		tokenList = new ArrayList<Token>();
		Commands com = new Commands();
		listCom = new ArrayList<>(com.getListCom().size());
		listCom.addAll(com.getListCom());
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
				ETypes type = ETypes.NOME;
				while(!isSpace(chr) && !isSemicolon(chr)) {
					data.append(String.valueOf(chr));
					i++;
					chr = str.charAt(i);
				}
				

				if(listCom.indexOf(data.toString()) != -1)
					type = ETypes.COMANDO;
				
				this.tokenList.add(new Token(data.toString(), type));
				data = new StringBuilder();
			}
			
			
			//Identificando número
			else if (isNum(chr)) {
				
				ETypes type = ETypes.INTEIRO;
				
				while(isNum(chr) || isPoint(chr) || isComma(chr)) {
					data.append(String.valueOf(chr));

					if((isPoint(chr) || isComma(chr)) && isNum(str.charAt(i+1)))
						type = ETypes.REAL;
					
					i++;
					chr = str.charAt(i);
					
					//Se possuir letras após os números será nome
					if(isLetter(chr)) {
						data.append(String.valueOf(chr));
						type = ETypes.NOME;
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
			
			
			//Identificando operação
			else if (isOperation(chr)) {
				
				data.append(chr);
				this.tokenList.add(new Token(data.toString(), ETypes.OPERACAO));
				
				data = new StringBuilder();
			}
			
			
			//Identificando comparação
			else if (isComparison(chr, str.charAt(i+1))) {
				
				data.append(chr);
				data.append(str.charAt(i+1));
				this.tokenList.add(new Token(data.toString(), ETypes.COMPARACAO));
				
				data = new StringBuilder();
				i++;
			}
			
			
			//
			if (isSemicolon(chr)) {
				
				data.append(chr);
				this.tokenList.add(new Token(data.toString(), ETypes.FINAL));
				
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
	
	public boolean isOperation(char c) {
		if(c=='=' || c=='+' || c=='-' || c=='*' || c=='/')
			return true;
		return false;
	}
	
	public boolean isComparison(char c1, char c2) {
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
