package MAA.ConstraintLayoutParser.structure;

public class ViewGroup extends View {
	/**
	 * beinhaltet alle möglichen XML Attribute die sich aus android.view.ViewGroup
	 * ergeben
	 */
	public ViewGroup() {
		super();
		codeMatcher.put("android:addStatesFromChildren", "notDefined");
		codeMatcher.put("android:alwaysDrawnWithCache", "notDefined");
		codeMatcher.put("android:animateLayoutChanges", ".setLayoutTransition(%s);\n");
		codeMatcher.put("android:animationCache", "notDefined");
		codeMatcher.put("android:clipChildren", ".setClipChildren(%s);\n");
		codeMatcher.put("android:clipToPadding", ".setClipToPadding(%s);\n");
		codeMatcher.put("android:descendantFocusability", "notDefined");
		codeMatcher.put("android:layoutAnimation", "notDefined");
		codeMatcher.put("android:layoutMode", ".setLayoutMode(%s);\n");
		codeMatcher.put("android:persistentDrawingCache", "notDefined");
		codeMatcher.put("android:splitMotionEvents", ".setMotionEventSplittingEnabled(%s);\n");
	}
}