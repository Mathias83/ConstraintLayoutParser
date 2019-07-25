package MAA.ConstraintLayoutParser;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import MAA.ConstraintLayoutParser.structure.ConstraintLayout;

public class ConstraintLayoutParser {
	private Document doc;

	/**
	 * Konstruktor welches das Layout entgegen nimmt und dieses
	 * 
	 * <b>Parser</b> Als Parser habe ich mich für javax.xml.parsers entschieden.
	 * Dieser leifert ein XML-Dokument welches vorher eingelesen wurde innerhalb
	 * einer Baumstruktur. Hierbei werden Attibute und Elemente dargestellt. Im Fall
	 * von Android sind die Attribute die Eigenschaft eines Elements und die
	 * Elemente die Objekte.
	 * 
	 * 
	 * @param LayoutInXML eingelesenes XML-Layout
	 */
	public ConstraintLayoutParser(String LayoutInXML) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			doc = builder.parse(LayoutInXML);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Leitet das parsen mit dem ConstraintLayoutParser ein Falls die übergebene
	 * Datei Leer ist oder kein XML enthält gibt es einen fehler.
	 * 
	 * @param string
	 * 
	 * @return geparsten Layout
	 */
	public ConstraintLayout parseLayout(String className) {
		Element element = doc.getDocumentElement();
		return new ConstraintLayout(element, className);
	}

}
