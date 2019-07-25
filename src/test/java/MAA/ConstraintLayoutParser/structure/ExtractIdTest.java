package MAA.ConstraintLayoutParser.structure;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Element;

import MAA.ConstraintLayoutParser.ConstraintLayoutParser;

public class ExtractIdTest {

	ConstraintLayoutParser parser = new ConstraintLayoutParser("example/test_JUnit_extractId.xml");;
	ConstraintLayout parseLayout = parser.parseLayout("testJUnit");;
	Element element = parseLayout.element;

	@Test
	void extractIdBasic1Test() {
		String expectedLayout = "basicId";
		String parsedlayout = parseLayout.extractId(element.getElementsByTagName("IdBasic1").item(0).getAttributes());
		assertTrue(expectedLayout.contentEquals(parsedlayout));
	}

	@Test
	void extractIdBasic1XMLTest() {
		String expectedLayout = "R.id.basicId";
		String parsedlayout = parseLayout.extractId(element.getElementsByTagName("IdBasic1").item(0).getAttributes()
				.getNamedItem("android:id").getNodeValue());
		assertTrue(expectedLayout.contentEquals(parsedlayout));
	}

	@Test
	void extractIdEmptyIDTest() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			parseLayout.extractId(element.getElementsByTagName("IdEmptyId").item(0).getAttributes());
		});
	}

	@Test
	void extractIdEmptyIDXMLTest() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			parseLayout.extractId(element.getElementsByTagName("IdEmptyId").item(0).getAttributes()
					.getNamedItem("android:id").getNodeValue());
		});
	}

	@Test
	void extractIdNullIDTest() {
		String expectedLayout = "contentLayoutAuto";
		String parsedlayout = parseLayout.extractId(element.getElementsByTagName("IdNullId").item(0).getAttributes());
		assertTrue(expectedLayout.contentEquals(parsedlayout));
	}

	@Test
	void extractIdParentIDXMLTest() {
		String expectedLayout = "ConstraintSet.PARENT_ID";
		String parsedlayout = parseLayout.extractId("parent");
		assertTrue(expectedLayout.contentEquals(parsedlayout));
	}
}
