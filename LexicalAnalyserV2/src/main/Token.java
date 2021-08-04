package main;

public class Token {
	String data;
	ETypes type;
	
	public Token(String data, ETypes type) {
		super();
		this.data = data;
		this.type = type;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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
		builder.append(data);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}
	
}
