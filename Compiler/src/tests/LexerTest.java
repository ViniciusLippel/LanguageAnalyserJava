package tests;

import compiler.*;

public class LexerTest {
	
	public static void main(String[] args) {
		
		//Example Program
		String str1 = "var1 = 30;"
				+ "var2 = 25.5 + 23;"
				+ "var_String = \'Hello World!\';"
				+ "if (var1 >= var2): "
				+ 	"read varString;"
				+ "for(x in y):"
				+ 	"print x;";
		
		
		//Lexical Analysis
		Analyser a = new Analyser();
		Lexer l = new Lexer(a.getListCom());
		l.Analyse(str1);
		
		
		//Printing found tokens
		System.out.println("Tokens:\n");
		
		for(int i=0; i<l.getTokenList().size(); i++) {
			
			Token t = l.getTokenList().get(i);
			System.out.println(" [" + t.getType().toString() + ": " + t.getData() + "]");
			
			if(t.getType() == ETypes.SEMICOLON)
				System.out.println();
		}
		
	}
}
