import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HTMLElement extends HTMLNode {
	private List<HTMLNode> childElements;
	private Map<String, String> attributes;
	private String name;

	public HTMLElement(String name) {
		this.name = name;
		this.childElements = new ArrayList<HTMLNode>();
		this.attributes = new HashMap<String, String>();
	}
	
	public HTMLElement addChildElement(HTMLNode element) {
		this.childElements.add(element);
		return this;
	}
	
	public HTMLElement setAttribute(String attribute, String value) {
		/*this.attributes.putIfAbsent(attribute, value);
		this.attributes.computeIfPresent(attribute, (a, v) -> value);*/
		this.attributes.put(attribute, value);
		return this;
	}
	
	public void removeAttribute(String attribute) {
		this.attributes.remove(attribute);
	}
	
	public String getAttribute(String attribute) {
		return this.attributes.get(attribute);
	}
	
	@Override
	public String toString() {
		String s = "";
		s += "<" + this.name;
		for(Entry<String, String> entry : this.attributes.entrySet()) {
		    s += " " + entry.getKey() + "=\"" + entry.getValue() + "\"";
		}
		s += ">";
		for(HTMLNode element : this.childElements) {
		    s += element;
		}
		s += "</" + this.name + ">";
		return s;
	}
}