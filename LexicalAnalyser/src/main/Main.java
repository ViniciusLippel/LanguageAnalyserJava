package main;

public class Main {

	public static void main(String[] args) {
		
		//Exemplo de programa
		String str = "var1 = 30;"
				+ "var2 = 25.5;"
				+ "var_String = \'Hello World!\';"
				+ "if var1 >= var2 "
				+ 	"print varString;";

		
		//Realizando análise léxica
		Analyser a = new Analyser();
		a.Analyse(str);
		
		
		//Mostrando tokens
		System.out.println("Tokens:\n");
		
		for(int i=0; i<a.getTokenList().size(); i++) {
			
			Token t = a.getTokenList().get(i);
			System.out.println(t.getData() + " [" + t.getType() + "]");
			
			if(t.getType() == ETypes.FINAL)
				System.out.println();
		}
	}

}
