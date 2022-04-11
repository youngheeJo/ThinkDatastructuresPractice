package crawling;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class WikiNodeExample {
	
	public static void main(String[] args) throws IOException {
		String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
		
		// download and parse the document
		Connection conn = Jsoup.connect(url);
		Document doc = conn.get();
		
		// select the content text and pull out the paragraphs.
		Element content = doc.getElementById("mw-content-text");
				
		// TODO: avoid selecting paragraphs from sidebars and boxouts
		Elements paras = content.select("p");
		Element firstPara = paras.get(0);

		for (Element node : paras) {
			recursiveDFS(node);
		}
		System.out.println();

		for (Element node : paras) {
			iterativeDFS(node);
		}
		System.out.println();

		Iterable<Node> iter = new WikiNodeIterable(firstPara);
		for (Node node: iter) {
			if (node instanceof TextNode) {
				System.out.print(node);
			}
		}
	}

	private static void iterativeDFS(Node root) {
		Deque<Node> stack = new ArrayDeque<>();
		stack.push(root);

		while (!stack.isEmpty()) {
			Node node = stack.pop();

			if (node instanceof TextNode) {
				System.out.print(node);
			}

			List<Node> childNodes = new ArrayList<>(node.childNodes());
			Collections.reverse(childNodes);

			for (Node childNode : childNodes) {
				stack.push(childNode);
			}
		}
	}

	private static void recursiveDFS(Node node) {
		if (node instanceof TextNode) {
			System.out.print(node);
		}
		for (Node childNode : node.childNodes()) {
			recursiveDFS(childNode);
		}
	}
}
