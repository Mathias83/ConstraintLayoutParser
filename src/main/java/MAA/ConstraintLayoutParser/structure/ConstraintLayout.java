package MAA.ConstraintLayoutParser.structure;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ConstraintLayout extends ViewGroup {

	/** Importanweisungen */
	private List<String> imports = new ArrayList<>();
	/** onCreate-Methode */
	private StringBuilder onCreateMethod = new StringBuilder();
	/** onScreen-Methode */
	private StringBuilder onScreenMethode = new StringBuilder();
	/** Klassedeaklaration der Javaklasse */
	private StringBuilder classDeclaration = new StringBuilder();
	/** Klassen-Methoden */
	private StringBuilder elementMethods = new StringBuilder();
	/** IDs die später händisch hinzugefügt werden müssen */
	private StringBuilder ids = new StringBuilder();

	protected Element element;

	/**
	 * <h1>Konstruktor</h1> Hier wird das parsen direkt eingeleitet
	 * 
	 * @param element   geparstes XML Dokument
	 * @param className Klassenname da identisch mit Dateiname
	 */
	public ConstraintLayout(Element element, String className) {

		super();
		this.element = element;
		parseLayout(className);
	}

	/**
	 * <h1>Einstiegspunkt des Parsings</h1> Hier wird die eigentliche Java-Klasse
	 * erstellt mit dem initialen Import von AppCombatActivity Nächster Schritt ist
	 * die onCreateMethode
	 * 
	 * @param className Klassenname da dieser identisch sein muss mit dem Dateinamen
	 */
	private void parseLayout(String className) {
		if (classNameHasTestPattern(className))
			return;
		if (elementIsNotValid(element))
			throw new NullPointerException("Element ist null!");
		classDeclaration.append("\npublic class " + className + " extends AppCompatActivity {\n");
		imports.add("import androidx.appcompat.app.AppCompatActivity;\n");
		createonCreateMethod();

	}

	private boolean elementIsNotValid(Element element) {
		return element == null;
	}

	private boolean classNameHasTestPattern(String className) {
		return className.equals("testJUnit");
	}

	/**
	 * <h1>onCreate-Methode</h1> Beinhaltet alle Punkte die in der onCreate-Methode
	 * eine Rolle spielen Erzeugt zuerst die eigentliche Methode und füllt diese
	 * anschließend mit Inhalt Die Importanweisungen werden passend zu den einzelnen
	 * schritten hinzugefügt In der onCreate-Methode werden der Reihenfolge nach die
	 * Elemente des Layouts abgearbeitet. Dies wird ausgelagert in die
	 * onScreen-Methode. Geschlossen wird die onScreen-Methode hier da sonst die
	 * Constraints der einzelnen Kind-Elemente nicht hineinkommen.
	 * 
	 */
	private void createonCreateMethod() {
		onCreateMethod.append("\n@Override\n");
		onCreateMethod.append("protected void onCreate(Bundle savedInstanceState) {\n");
		imports.add("import android.os.Bundle;\n");
		onCreateMethod.append("super.onCreate(savedInstanceState);\n");
		parseparent(element.getAttributes());
		parseChildren(element.getChildNodes());
		onScreenMethode.append("cs.applyTo(" + extractId(element.getAttributes()) + ");\n");
		onScreenMethode.append("}\n");
		onCreateMethod.append("\n}\n");
	}

	/**
	 * <h1>Layout-parsing</h1> Parsed die Attribute des Layouts Hier geschehen alle
	 * dinge die das ConstrainLayout direkt betreffen Die einzelnen Constraints
	 * werden zu dem Zeitpunkt und in den Bereichen erzeigt wo sie gebraucht werden.
	 * 
	 * @param attributes Attributmap des Layouts
	 */
	private void parseparent(NamedNodeMap attributes) {
		onScreenMethode.append("\npublic void createScreen() {\n");
		onCreateMethod.append("createScreen();");
		onScreenMethode.append("ConstraintLayout " + extractId(attributes) + " = new ConstraintLayout(this);\n");
		imports.add("import androidx.constraintlayout.widget.ConstraintLayout;\n");
		imports.add("import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams;\n");
		onScreenMethode.append(extractLayoutParams(attributes));
		onScreenMethode.append("setContentView(" + extractId(attributes) + ");\n");
		imports.add("import androidx.constraintlayout.widget.ConstraintSet;\n");
		onScreenMethode.append("ConstraintSet cs = new ConstraintSet();\n");
	}

	/**
	 * <h1>Element-parsing</h1> Beinhaltet alle Kind-Attribute Hier werden alle
	 * Kind-Elemente in einer eigenen Methode abgebildet. Die Erzeugung dieser
	 * Methoden im Detail übernimmt dann die parseViews Methode. Hier werden nur die
	 * Methoden vorbereitet sowie deren aufruf in die onScreen Methode übernommen.
	 * Der Inhalt der Methoden wird in parseViews gemacht.
	 * 
	 * @param attributes Attributmap des Layouts
	 */
	private void parseChildren(NodeList childNodes) {
		Node node = null;
		String methodName, type, variableName, constraintLayoutName, importString;
		for (int i = 0; i < childNodes.getLength(); i++) {
			node = childNodes.item(i);
			if (nodeIsNotValid(node))
				continue;
			methodName = "create" + extractId(node.getAttributes()) + "()";
			type = node.getNodeName();
			variableName = extractId(node.getAttributes());
			constraintLayoutName = extractId(element.getAttributes());
			importString = "import android.widget." + type + ";\n";
			if (!imports.contains(importString))
				imports.add(importString);
			onScreenMethode.append(constraintLayoutName + ".addView(" + methodName + ");\n");
			elementMethods.append("\nprivate " + type + " " + methodName + "{\n");			
			elementMethods.append(type + " " + variableName + " = new " + type + "(this);\n");
			parseViews(node.getAttributes());
			elementMethods.append("return " + variableName + ";\n");
			elementMethods.append("}\n");
		}

	}

	private boolean nodeIsNotValid(Node node) {
		return node.getNodeName().equals("#text") || node == null;
	}

	/**
	 * <h1>Attribut-parsing</h1> Parsed views extrahiert die einzelnen attribute die
	 * zu einem element gehören Hier wird einmal der anzeil am ConstrainLayout
	 * extrahiert sowie die Elementeigenen attribute behandelt Ich habe dies nur
	 * exemplarisch mit der TextView gemacht da es sich Primär um das
	 * ConstraintLayout gedreht hat welches unter den elementen gleich sein sollte.
	 * 
	 * @param attributes Attributmap dieses elements
	 */
	private void parseViews(NamedNodeMap attributes) {
		Node node = null;
		String format, value;
		for (int i = 0; i < attributes.getLength(); i++) {
			node = attributes.item(i);
			if (containsConstraintLayout(node)) {
				onScreenMethode.append(extractConstraint(node, attributes));
			}

			value = node.getNodeValue();
			if (atttributeIsID(node))
				value = extractId(value);
			if (codeMatcherContainsFormat(node)) {
				elementMethods
						.append(extractId(attributes) + String.format(codeMatcher.get(node.getNodeName()), value));
			}
		}
		onScreenMethode.append(extractConstraintLayoutParams(attributes));
		ids.append("<item type=\"id\" name=\"" + extractId(attributes) + "\" />\n");
	}

	private boolean codeMatcherContainsFormat(Node node) {
		return codeMatcher.get(node.getNodeName()) != null;
	}

	private boolean atttributeIsID(Node node) {
		return node.getNodeName().equals("android:id"); 
	}

	private boolean containsConstraintLayout(Node node) {
		return node.getNodeName().startsWith("app:layout_constraint");
	}

	/**
	 * <h1>Margin-parsing</h1> Extrahiert den Margin anteil eines Elements. Hierfür
	 * gibt es zwar eine eigene Methode (setMargin) es schien mir aber einfacher
	 * dies bei bedarf an die connect methode anzuhängen. Hier wird passend zum
	 * jeweiligen Constraint ein mögliches Margin in diesem Element gesucht. Sollte
	 * es keins geben wird auch kein wert hinzugefügt. Wird nur LTR Screens
	 * unterstützt
	 * 
	 * @param nodeName   Constraintelement (Bsp:
	 *                   app:layout_constraintBottom_toBottomOf)
	 * @param attributes
	 * @return
	 */
	protected String extractMargin(String nodeName, NamedNodeMap attributes) {
		String[] nodeNameSlice = nodeName.split("_");
		// System.out.println(nodeNameSlice[1]);
		Node node = null;
		String returnString = "";
		switch (nodeNameSlice[1]) {
		case "constraintBottom":
			node = attributes.getNamedItem("android:layout_marginBottom");
			break;
		case "constraintLeft":
		case "constraintStart":
			node = attributes.getNamedItem("android:layout_marginStart");
			if (node == null)
				node = attributes.getNamedItem("android:layout_marginLeft");
			break;
		case "constraintRight":
		case "constraintEnd":
			node = attributes.getNamedItem("android:layout_marginRight");
			if (node == null)
				node = attributes.getNamedItem("android:layout_marginEnd");
			break;
		case "constraintTop":
			node = attributes.getNamedItem("android:layout_marginTop");
			break;
		default:
			return "";
		}
		if (node != null)
			returnString = extractDP(node.getNodeValue());

		return returnString;
	}

	/**
	 * <h1>Constraint-parsing</h1> Extrahiert jeweils ein Constraint. Hier wird ein
	 * Constraint zusammengebaut welches aus einer Quelle und einem Ziel besteht.
	 * Quelle und Ziel haben jeweils eine ID sowie ein Constraint Falls vorhanden
	 * wird auch noch das Margin ergänzt
	 * 
	 * @param constraint XML-String des Constraints
	 * @param sourceId   Quelle von der das Constraint ausgeht
	 * @param targetId   Ziel von dem das Constraint ausgeht
	 */

	protected String extractConstraint(Node node, NamedNodeMap attributes) {
		String margin = extractMargin(node.getNodeName(), attributes);
		String[] split = node.getNodeName().split("_");
		return "cs.connect(R.id." + extractId(attributes) + ", " + extractConstraintSet(split[1]) + ", "
				+ extractId(node.getNodeValue()) + ", " + extractConstraintSet(split[2])
				+ (margin.equals("") ? "" : ",") + margin + ");\n";

	}

	/**
	 * <h1>ConstraintXMLJava-matching</h1> Extrahiert die einzelnen Contraintformen
	 * Beispiel: aus <code>app:layout_constraintBottom_toBottomOf="parent"</code>
	 * soll
	 * <code>cs.connect(R.id.textView3, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);</code>
	 * werden
	 * 
	 * @param string teilstring für dieses Constraint
	 * @return Java representation dieses Constraints
	 */
	protected String extractConstraintSet(String string) {
		switch (string) {
		case "constraintBottom":
		case "toBottomOf":
			return "ConstraintSet.BOTTOM";
		case "constraintLeft":
		case "toLeftOf":
			return "ConstraintSet.LEFT";
		case "constraintRight":
		case "toRightOf":
			return "ConstraintSet.RIGHT";
		case "constraintTop":
		case "toTopOf":
			return "ConstraintSet.TOP";
		case "constraintEnd":
		case "toEndOf":
			return "ConstraintSet.END";
		case "constraintStart":
		case "toStartOf":
			return "ConstraintSet.START";
		default:
			return "";

		}

	}

	/**
	 * <h1>ID-Extraktion</h1> Extrahier die Id aus der NodeMap <br>
	 * <b>Beispiel:</b> aus <code>android:id="@+id/textView1"</code> wird
	 * <code>textView1</code> <br>
	 * <b>Ausnahme</b> Wenn die ID leer ist bzw nicht vorhanden ist im XML Layout
	 * muss ein Default zurück gegeben werden da die ID hier auch die Funktion des
	 * Variablennamen übernimmt.
	 * 
	 * @param attributes Map eines elements
	 * @return extrahierte ID
	 */
	protected String extractId(NamedNodeMap attributes) {

		if (attributes.getNamedItem("android:id") == null)
			return "contentLayoutAuto";
		if (attributes.getNamedItem("android:id").getNodeValue().split("/").length < 2)
			throw new IllegalArgumentException("ID hat falsches Format");
		return attributes.getNamedItem("android:id").getNodeValue().split("/")[1];
	}

	/**
	 * <h1>ID-Extraktion</h1> Extrahier die Id aus der XML schreibweise <br>
	 * <b>Beispiel:</b> aus <code>@+id/textView1</code> wird
	 * <code>R.id.textView1</code> <br>
	 * <b>Ausnahme:</b> Wenn es sich um die Parent ID
	 * <code>app:layout_constraintStart_toStartOf="parent"</code> handelt wird
	 * <code>ConstraintSet.PARENT_ID</code> zurück gegeben
	 * 
	 * @param XMLId
	 * @return extrahierte ID in R.id. schreibweise
	 */
	protected String extractId(String XMLId) {
		if (XMLId.equals("parent"))
			return "ConstraintSet.PARENT_ID";
		if (XMLId.split("/").length < 2)
			throw new IllegalArgumentException("ID hat falsches Format");
		return "R.id." + XMLId.split("/")[1];
	}

	/**
	 * <h1>LayoutParam-Extrahierung</h1> Extrahiert die LayoutParameter des
	 * ConstraintLayouts selbst. Dies erfolgt noch in der üblichen Schreibweise für
	 * Layouts
	 * 
	 * @param attributes Map eines elements
	 * @return extrahierte Layoutparameter
	 */
	protected String extractLayoutParams(NamedNodeMap attributes) {
		if (attributes.getNamedItem("android:layout_height") == null
				|| attributes.getNamedItem("android:layout_width") == null)
			throw new IllegalArgumentException("Fehlende Attribute");
		return (extractId(attributes) + ".setLayoutParams(new LayoutParams("
				+ getLayoutParameter(attributes, "android:layout_height") + ", "
				+ getLayoutParameter(attributes, "android:layout_width") + "));\n");

	}

	/**
	 * <h1>Generiert LayoutParams</h1> Hilfsmethode zum Layout Extrahieren macht aus
	 * <code>match_parent</code> = <code>LayoutParams.MATCH_PARENT</code>
	 * 
	 * @param attributes Attribute eines elements
	 * @return fertiger Layoutparameter
	 */
	protected String getLayoutParameter(NamedNodeMap attributes, String name) {
		String nodeValue = attributes.getNamedItem(name).getNodeValue();
		nodeValue = "LayoutParams." + nodeValue.toUpperCase();
		return nodeValue;
	}

	/**
	 * <h1>DP-Entferner</h1> Hilfsmethode zum entfernen des dp in der
	 * XML-Schreibweise
	 * 
	 * @param nodeValue Value eines LayoutParameters
	 * @return
	 */
	private String extractDP(String nodeValue) {
		return nodeValue.substring(0, nodeValue.indexOf("dp"));
	}

	/**
	 * <h1>LayoutConstraintParam-Extrahierung</h1> Extrahiert die LayoutParameter
	 * innerhalb des Constraintlayouts (andere schreibweise)
	 * 
	 * 
	 * @param attributes Map eines elements
	 * @return extrahierte Layoutparameter
	 */
	protected String extractConstraintLayoutParams(NamedNodeMap attributes) {
		if (attributes.getNamedItem("android:id") == null || attributes.getNamedItem("android:layout_height") == null
				|| attributes.getNamedItem("android:layout_width") == null)
			throw new IllegalArgumentException("Fehlende Attribute");
		StringBuilder constraintBuilder = new StringBuilder();
		constraintBuilder.append("cs.constrainHeight(R.id." + extractId(attributes) + ","
				+ getConstraintSet(attributes.getNamedItem("android:layout_height")) + ");\n");
		constraintBuilder.append("cs.constrainWidth(R.id." + extractId(attributes) + ","
				+ getConstraintSet(attributes.getNamedItem("android:layout_width")) + ");\n");
		return constraintBuilder.toString();
	}

	/**
	 * <h1>ConstraintLayoutExtrator</h1> Extrahiert die einzelnenLayoutparameter für
	 * die Constraintsets
	 * 
	 * @param node Node mit diesem Constraint
	 * @return fertiger Constraintset Parameter
	 */
	protected String getConstraintSet(Node node) {
		String nodeValue = node.getNodeValue();
		if (nodeValue.endsWith("dp"))
			return extractDP(nodeValue);
		return "ConstraintSet." + nodeValue.toUpperCase();
	}

	/**
	 * <h1>Classbuilder</h1> Baut die eigentliche Java-Klasse zusammen. Zu beginn
	 * werden die Ids als kommentar angefügt Diese muss der Anwender selbst
	 * übernehmen.
	 */

	public String export() {
		StringBuilder completeClass = new StringBuilder();
		completeClass.append("/*---- Add following IDS to XML -----\n");
		completeClass.append(ids);
		completeClass.append("---- Begin export -----*/\n");
		imports.forEach(completeClass::append);
		completeClass.append(classDeclaration);
		completeClass.append(onCreateMethod);
		completeClass.append(onScreenMethode);
		completeClass.append(elementMethods);
		completeClass.append("}");
		return completeClass.toString();
	}
}
