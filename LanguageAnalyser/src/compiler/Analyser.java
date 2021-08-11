package compiler;

import java.util.Arrays;
import java.util.List;

public class Analyser {
	
	private List<String> keywordList; //Comandos
	
	public Analyser() {
		String[] arrCom = {"if", "else", "for", "while", "read", "print", "in", "and", "or"};
		this.keywordList = Arrays.asList(arrCom);
	}
	
	
	
	public List<String> getListCom() {
		return keywordList;
	}
	
	public void setListCom(List<String> listCom) {
		this.keywordList = listCom;
	}



	public void Analyse (String str) {
		Lexer lexer = new Lexer(this.keywordList);		
		lexer.Analyse(str);
		Parser parser = new Parser(lexer.getTokenList());
		parser.parse();
	}
}
