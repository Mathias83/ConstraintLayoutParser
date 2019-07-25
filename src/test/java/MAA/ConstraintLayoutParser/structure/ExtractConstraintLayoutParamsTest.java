package MAA.ConstraintLayoutParser.structure;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Element;

import MAA.ConstraintLayoutParser.ConstraintLayoutParser;

public class ExtractConstraintLayoutParamsTest {

	ConstraintLayoutParser parser = new ConstraintLayoutParser("example/test_JUnit_extractConstraintLayoutParams.xml");;
	ConstraintLayout parseLayout = parser.parseLayout("testJUnit");;
	Element element = parseLayout.element;

	@Test
	void extractConstraintLayoutParamsBasic1Test() {
		String expectedLayout = "cs.constrainHeight(R.id.textView1,ConstraintSet.WRAP_CONTENT);\ncs.constrainWidth(R.id.textView1,ConstraintSet.WRAP_CONTENT);\n";
		String parsedlayout = parseLayout
				.extractConstraintLayoutParams(element.getElementsByTagName("TextViewBasic1").item(0).getAttributes());
		assertTrue(expectedLayout.contentEquals(parsedlayout));
	}

	@Test
	void extractConstraintLayoutParamsBasic3Test() {
		String expectedLayout = "cs.constrainHeight(R.id.textView1,0);\ncs.constrainWidth(R.id.textView1,0);\n";
		String parsedlayout = parseLayout
				.extractConstraintLayoutParams(element.getElementsByTagName("TextViewBasic2").item(0).getAttributes());
		assertTrue(expectedLayout.contentEquals(parsedlayout));
	}

	@Test
	void extractConstraintLayoutParamsNoIDTest() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			parseLayout.extractConstraintLayoutParams(
					element.getElementsByTagName("TextViewNoID").item(0).getAttributes());
		});
	}

	@Test
	void extractConstraintLayoutParamsEmptyIDTest() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			parseLayout.extractConstraintLayoutParams(
					element.getElementsByTagName("TextViewEmptyID").item(0).getAttributes());
		});
	}

	@Test
	void extractConstraintLayoutParamsNoConstraintWidthTest() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			parseLayout.extractConstraintLayoutParams(
					element.getElementsByTagName("TextViewNoConstraintWidth").item(0).getAttributes());
		});

	}

	@Test
	void extractConstraintLayoutParamsNoConstraintHeightTest() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			parseLayout.extractConstraintLayoutParams(
					element.getElementsByTagName("TextViewNoConstraintHeight").item(0).getAttributes());
		});

	}

}
