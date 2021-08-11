package tests;

import compiler.*;

public class ParserTest {
	
	public static void main(String[] args) {
		
		//Example Programs
		String str1 = "var1 = 30;"
				+ "var2 = 25.5 + 23;"
				+ "var_String = \'Hello World!\';"
				+ "if (var1 >= var2): "
				+ 	"read varString;"
				+ "for(x in y):"
				+ 	"print x;";
		
		String str2 = "var1 30;"
				+ "var2 = 25.5 +;"
				+ "var_String = \'Hello World!\';"
				+ "if (var1 = var2): "
				+ 	"read 2;"
				+ "for(x in y):"
				+ 	"print var1;";
		
		
		//Lexical Analysis
		Analyser a = new Analyser();
		Lexer l = new Lexer(a.getListCom());
		l.Analyse(str2);
		
		
		//Syntax Analysis
		Parser p = new Parser(l.getTokenList());
		p.parse();
		
		
		//Printing tokens grouped by line
		for(int i=0; i<p.getGroupTokens().size(); i++) {
			System.out.println(p.getGroupTokens().get(i));
		}
		
		
		//Printing possible errors
		if(p.getErrorList().size() != 0) {
			System.out.println("\nError! ");	
			for(int i=0; i<p.getErrorList().size(); i++)
				System.out.println(p.getErrorList().get(i));
		}
		else
			System.out.println("\nRunning...");
	}
}
