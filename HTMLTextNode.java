public class HTMLTextNode extends HTMLNode {
	private String text;
	
	public HTMLTextNode(String text) {
		this.setText(text);
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String toString() {
		return text;
	}
}