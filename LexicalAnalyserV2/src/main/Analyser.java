package main;

import java.util.Arrays;
import java.util.List;

public class Analyser {
	
	private List<String> listCom; //Comandos
	
	public Analyser() {
		String[] arrCom = {"if", "else", "for", "while", "print"};
		this.listCom = Arrays.asList(arrCom);
	}
	
	
	
	public List<String> getListCom() {
		return listCom;
	}
	
	public void setListCom(List<String> listCom) {
		this.listCom = listCom;
	}



	public void Analyse (String str) {
		Lexer lexer = new Lexer(this.listCom);		
		lexer.Analyse(str);
		
		Parser parser = new Parser(lexer.getTokenList());
	}
}
