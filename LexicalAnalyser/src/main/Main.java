package main;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String str = "This is a text; \"And this is a quotation\"";
		//String str = "This is a text;";
		
		//System.out.println(str.length());
		
		int i = 5;
		//System.out.println(str.substring(str.indexOf("\"")+1, str.indexOf("\"", str.indexOf("\"")+1)));
		
		Analyser a = new Analyser();
		a.Analyse(str);
		System.out.println(a.getTokenList().toString());
	}

}
