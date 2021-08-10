package compiler;

public class Token {
	String lexeme;
	ETypes type;
	
	public Token(String data, ETypes type) {
		super();
		this.lexeme = data;
		this.type = type;
	}

	public String getData() {
		return lexeme;
	}

	public void setData(String data) {
		this.lexeme = data;
	}

	public ETypes getType() {
		return type;
	}

	public void setType(ETypes type) {
		this.type = type;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Token [data=");
		builder.append(lexeme);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}
	
}
