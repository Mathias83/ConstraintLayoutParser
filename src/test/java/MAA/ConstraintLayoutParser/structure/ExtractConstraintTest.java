package MAA.ConstraintLayoutParser.structure;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import MAA.ConstraintLayoutParser.ConstraintLayoutParser;

public class ExtractConstraintTest {

	ConstraintLayoutParser parser = new ConstraintLayoutParser("example/test_JUnit_extractConstraint.xml");;
	ConstraintLayout parseLayout = parser.parseLayout("testJUnit");;
	Element element = parseLayout.element;

	@Test
	void extractConstraintTopTest() {
		String[] expectedLayout = {
				"cs.connect(R.id.top, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);\n",
				"cs.connect(R.id.top, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);\n",
				"cs.connect(R.id.top, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);\n",
				"cs.connect(R.id.top, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);\n" };
		String[] parsedlayout = new String[4];
		NamedNodeMap attributes = element.getElementsByTagName("CTop").item(0).getAttributes();
		Node node = null;
		int j = 0;
		for (int i = 0; i < attributes.getLength(); i++) {
			node = attributes.item(i);

			if (node.getNodeName().startsWith("app:layout_constraint")) {

				parsedlayout[j++] = parseLayout.extractConstraint(node, attributes);
			}

		}
		assertArrayEquals(expectedLayout, parsedlayout);
	}

	@Test
	void extractConstraintLeftTest() {
		String[] expectedLayout = {
				"cs.connect(R.id.left, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);\n",
				"cs.connect(R.id.left, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);\n",
				"cs.connect(R.id.left, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);\n",
				"cs.connect(R.id.left, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.TOP);\n" };
		String[] parsedlayout = new String[4];
		NamedNodeMap attributes = element.getElementsByTagName("CLeft").item(0).getAttributes();
		Node node = null;
		int j = 0;
		for (int i = 0; i < attributes.getLength(); i++) {
			node = attributes.item(i);

			if (node.getNodeName().startsWith("app:layout_constraint")) {

				parsedlayout[j++] = parseLayout.extractConstraint(node, attributes);
			}

		}
		assertArrayEquals(expectedLayout, parsedlayout);
	}

	@Test
	void extractConstraintBottomTest() {
		String[] expectedLayout = {
				"cs.connect(R.id.bottom, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);\n",
				"cs.connect(R.id.bottom, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);\n",
				"cs.connect(R.id.bottom, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);\n",
				"cs.connect(R.id.bottom, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.TOP);\n" };
		String[] parsedlayout = new String[4];
		NamedNodeMap attributes = element.getElementsByTagName("CBottom").item(0).getAttributes();
		Node node = null;
		int j = 0;
		for (int i = 0; i < attributes.getLength(); i++) {
			node = attributes.item(i);

			if (node.getNodeName().startsWith("app:layout_constraint")) {

				parsedlayout[j++] = parseLayout.extractConstraint(node, attributes);
			}

		}
		assertArrayEquals(expectedLayout, parsedlayout);
	}

	@Test
	void extractConstraintRightTest() {
		String[] expectedLayout = {
				"cs.connect(R.id.right, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);\n",
				"cs.connect(R.id.right, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);\n",
				"cs.connect(R.id.right, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);\n",
				"cs.connect(R.id.right, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.TOP);\n" };
		String[] parsedlayout = new String[4];
		NamedNodeMap attributes = element.getElementsByTagName("CRight").item(0).getAttributes();
		Node node = null;
		int j = 0;
		for (int i = 0; i < attributes.getLength(); i++) {
			node = attributes.item(i);

			if (node.getNodeName().startsWith("app:layout_constraint")) {

				parsedlayout[j++] = parseLayout.extractConstraint(node, attributes);
			}

		}
		assertArrayEquals(expectedLayout, parsedlayout);
	}

	@Test
	void extractConstraintTopMarginTest() {
		String[] expectedLayout = {
				"cs.connect(R.id.top, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM,8);\n",
				"cs.connect(R.id.top, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.LEFT,8);\n",
				"cs.connect(R.id.top, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT,8);\n",
				"cs.connect(R.id.top, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP,8);\n" };
		String[] parsedlayout = new String[4];
		NamedNodeMap attributes = element.getElementsByTagName("CTopMargin").item(0).getAttributes();
		Node node = null;
		int j = 0;
		for (int i = 0; i < attributes.getLength(); i++) {
			node = attributes.item(i);

			if (node.getNodeName().startsWith("app:layout_constraint")) {

				parsedlayout[j++] = parseLayout.extractConstraint(node, attributes);
			}

		}
		assertArrayEquals(expectedLayout, parsedlayout);
	}

	@Test
	void extractConstraintLeftMarginTest() {
		String[] expectedLayout = {
				"cs.connect(R.id.left, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM,8);\n",
				"cs.connect(R.id.left, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT,8);\n",
				"cs.connect(R.id.left, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT,8);\n",
				"cs.connect(R.id.left, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.TOP,8);\n" };
		String[] parsedlayout = new String[4];
		NamedNodeMap attributes = element.getElementsByTagName("CLeftMargin").item(0).getAttributes();
		Node node = null;
		int j = 0;
		for (int i = 0; i < attributes.getLength(); i++) {
			node = attributes.item(i);

			if (node.getNodeName().startsWith("app:layout_constraint")) {

				parsedlayout[j++] = parseLayout.extractConstraint(node, attributes);
			}

		}

		assertArrayEquals(expectedLayout, parsedlayout);
	}

	@Test
	void extractConstraintBottomMarginTest() {
		String[] expectedLayout = {
				"cs.connect(R.id.bottom, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM,8);\n",
				"cs.connect(R.id.bottom, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.LEFT,8);\n",
				"cs.connect(R.id.bottom, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT,8);\n",
				"cs.connect(R.id.bottom, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.TOP,8);\n" };
		String[] parsedlayout = new String[4];
		NamedNodeMap attributes = element.getElementsByTagName("CBottomMargin").item(0).getAttributes();
		Node node = null;
		int j = 0;
		for (int i = 0; i < attributes.getLength(); i++) {
			node = attributes.item(i);

			if (node.getNodeName().startsWith("app:layout_constraint")) {

				parsedlayout[j++] = parseLayout.extractConstraint(node, attributes);
			}

		}
		assertArrayEquals(expectedLayout, parsedlayout);
	}

	@Test
	void extractConstraintRightMarginTest() {
		String[] expectedLayout = {
				"cs.connect(R.id.right, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM,8);\n",
				"cs.connect(R.id.right, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT,8);\n",
				"cs.connect(R.id.right, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT,8);\n",
				"cs.connect(R.id.right, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.TOP,8);\n" };
		String[] parsedlayout = new String[4];
		NamedNodeMap attributes = element.getElementsByTagName("CRightMargin").item(0).getAttributes();
		Node node = null;
		int j = 0;
		for (int i = 0; i < attributes.getLength(); i++) {
			node = attributes.item(i);

			if (node.getNodeName().startsWith("app:layout_constraint")) {

				parsedlayout[j++] = parseLayout.extractConstraint(node, attributes);
			}

		}
		assertArrayEquals(expectedLayout, parsedlayout);
	}
}
