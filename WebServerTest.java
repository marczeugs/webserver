import java.io.IOException;

public class WebServerTest {

	public static void main(String[] args) throws IOException {
		
		HTMLElement head = new HTMLElement("head");
		HTMLElement body = new HTMLElement("body");
		body.addChildElement(new HTMLElement("p").setAttribute("style", "color: red; background: aqua; font: 14px Comic Sans;").addChildElement(new HTMLTextNode("hi")));
		HTMLElement samplePage = new HTMLElement("html").addChildElement(head).addChildElement(body);
		
		WebServer server = new WebServer(80);
		server.addPage("/hi", samplePage);
		server.addPage("/", 
			new HTMLElement("html").addChildElement(
				new HTMLElement("head").addChildElement(
					new HTMLElement("title").addChildElement(
						new HTMLTextNode("Ein Titel")
					)
				)
			).addChildElement(
				new HTMLElement("body").addChildElement(
					new HTMLElement("a").addChildElement(
						new HTMLTextNode("Hallo")
					)
					.setAttribute("href", "hi")
					.setAttribute("style", "color: green; font: 72px Impact;")
				)
			)
		);
		server.start();
		
	}

}