package main;

import java.util.Arrays;
import java.util.List;

public class Commands {
	
	private List<String> listCom;
	
	public Commands() {
		String[] arrCom = {"if", "else", "for", "while", "print"};
		this.listCom = Arrays.asList(arrCom);
	}

	public List<String> getListCom() {
		return listCom;
	}

	public void setListCom(List<String> listCom) {
		this.listCom = listCom;
	}
	
	
}
