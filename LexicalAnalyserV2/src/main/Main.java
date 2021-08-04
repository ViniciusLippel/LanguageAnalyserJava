package main;


public class Main {

	public static void main(String[] args) {
		
		//Exemplo de programa
		String str = "var1 = 30;"
				+ "var2 = 25.5;"
				+ "var_String = \'Hello World!\';"
				+ "if (var1 >= var2) "
				+ 	"print varString;";

		
		//Realizando análise léxica
		Analyser a = new Analyser();
		Lexer l = new Lexer(a.getListCom());
		l.Analyse(str);
		Parser p = new Parser(l.getTokenList());
		p.groupTokens();
		
		//Mostrando tokens
		/*System.out.println("Tokens:\n");
		
		for(int i=0; i<l.getTokenList().size(); i++) {
			
			Token t = l.getTokenList().get(i);
			System.out.println(" [" + t.getType().toString() + ": " + t.getData() + "]");
			
			if(t.getType() == ETypes.SEMICOLON)
				System.out.println();
		}*/
		
		for(int i=0; i<p.getGroupTokens().size(); i++) {
			
			System.out.println(p.getGroupTokens().get(i));
		}
	}

}
