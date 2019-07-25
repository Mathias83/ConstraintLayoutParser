package MAA.ConstraintLayoutParser.structure;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Element;

import MAA.ConstraintLayoutParser.ConstraintLayoutParser;

public class ExtractLayoutTest {

	ConstraintLayoutParser parser = new ConstraintLayoutParser("example/test_JUnit_extractLayout.xml");;
	ConstraintLayout parseLayout = parser.parseLayout("testJUnit");;
	Element element = parseLayout.element;

	@Test
	void extractLayoutParamsBasic1Test() {
		String expectedLayout = "constraint.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));\n";
		String parsedlayout = parseLayout
				.extractLayoutParams(element.getElementsByTagName("ConstraintBasic1").item(0).getAttributes());
		assertTrue(expectedLayout.contentEquals(parsedlayout));
	}

	@Test
	void extractConstraintLayoutParamsBasic2Test() {
		String expectedLayout = "constraint.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));\n";
		String parsedlayout = parseLayout
				.extractLayoutParams(element.getElementsByTagName("ConstraintBasic2").item(0).getAttributes());
		assertTrue(expectedLayout.contentEquals(parsedlayout));
	}

	@Test
	void extractConstraintLayoutParamsNoIDTest() {
		String expectedLayout = "contentLayoutAuto.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));\n";
		String parsedlayout = parseLayout
				.extractLayoutParams(element.getElementsByTagName("ConstraintNoID").item(0).getAttributes());
		assertTrue(expectedLayout.contentEquals(parsedlayout));
	}

	@Test
	void extractConstraintLayoutParamsEmptyIDTest() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			parseLayout.extractLayoutParams(element.getElementsByTagName("ConstraintEmptyID").item(0).getAttributes());
		});
	}

	@Test
	void extractConstraintLayoutParamsNoConstraintWidthTest() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			parseLayout.extractLayoutParams(
					element.getElementsByTagName("ConstraintNoConstraintWidth").item(0).getAttributes());
		});

	}

	@Test
	void extractConstraintLayoutParamsNoConstraintHeightTest() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			parseLayout.extractLayoutParams(
					element.getElementsByTagName("ConstraintNoConstraintHeight").item(0).getAttributes());
		});

	}
}
