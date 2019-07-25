package MAA.ConstraintLayoutParser.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * View representant aus Android
 */
public class View {
	/**
	 * Map enthält die XML - Java mappings
	 */
	protected Map<String, String> codeMatcher = new HashMap<>();

	/** Erzeugt bei der Initialisierung die Map mit den entsprechenden einträgen */
	public View() {
		fillCodeMatcher();
	}

	/**
	 * beinhaltet alle möglichen XML Attribute die sich aus android.view.View
	 * ergeben
	 */
	private void fillCodeMatcher() {
		codeMatcher.put("android:accessibilityHeading", ".setAccessibilityHeading(%s);\n(%s);\n");
		codeMatcher.put("android:accessibilityLiveRegion", ".setAccessibilityLiveRegion(%s);\n");
		codeMatcher.put("android:accessibilityTraversalAfter", ".setAccessibilityTraversalAfter(%s);\n");
		codeMatcher.put("android:accessibilityTraversalBefore", ".setAccessibilityTraversalBefore(%s);\n");
		codeMatcher.put("android:alpha", ".setAlpha(%s);\n");
		codeMatcher.put("android:autofillHints", ".setAutofillHints(%s);\n");
		codeMatcher.put("android:autofilledHighlight", "autofill(%s);\n");
		codeMatcher.put("android:background", ".setBackgroundResource(%s);\n");
		codeMatcher.put("android:backgroundTint", ".setBackgroundTintList(%s);\n");
		codeMatcher.put("android:backgroundTintMode", ".setBackgroundTintMode(%s);\n");
		codeMatcher.put("android:clickable", ".setClickable(%s);\n");
		codeMatcher.put("android:contentDescription", ".setContentDescription(%s);\n");
		codeMatcher.put("android:contextClickable", ".setContextClickable(%s);\n");
		codeMatcher.put("android:defaultFocusHighlightEnabled", ".setDefaultFocusHighlightEnabled(%s);\n");
		codeMatcher.put("android:drawingCacheQuality", ".setDrawingCacheQuality(%s);\n");
		codeMatcher.put("android:duplicateParentState", "(%s);\n");
		codeMatcher.put("android:elevation", ".setElevation(%s);\n");
		codeMatcher.put("android:fadeScrollbars", ".setScrollbarFadingEnabled(%s);\n");
		codeMatcher.put("android:fadingEdgeLength", "getVerticalFadingEdgeLength(%s);\n");
		codeMatcher.put("android:filterTouchesWhenObscured", ".setFilterTouchesWhenObscured(%s);\n");
		codeMatcher.put("android:fitsSystemWindows", ".setFitsSystemWindows(%s);\n");
		codeMatcher.put("android:focusable", ".setFocusable(%s);\n");
		codeMatcher.put("android:focusableInTouchMode", ".setFocusableInTouchMode(%s);\n");
		codeMatcher.put("android:focusedByDefault", ".setFocusedByDefault(%s);\n");
		codeMatcher.put("android:forceHasOverlappingRendering", "forceHasOverlappingRendering(%s);\n");
		codeMatcher.put("android:foreground", ".setForeground(%s);\n");
		codeMatcher.put("android:foregroundGravity", ".setForegroundGravity(%s);\n");
		codeMatcher.put("android:foregroundTint", ".setForegroundTintList(%s);\n");
		codeMatcher.put("android:foregroundTintMode", ".setForegroundTintMode(%s);\n");
		codeMatcher.put("android:hapticFeedbackEnabled", ".setHapticFeedbackEnabled(%s);\n");
		codeMatcher.put("android:id", ".setId(%s);\n");
		codeMatcher.put("android:importantForAccessibility", ".setImportantForAccessibility(%s);\n");
		codeMatcher.put("android:importantForAutofill", ".setImportantForAutofill(%s);\n");
		codeMatcher.put("android:isScrollContainer", ".setScrollContainer(%s);\n");
		codeMatcher.put("android:keepScreenOn", ".setKeepScreenOn(%s);\n");
		codeMatcher.put("android:keyboardNavigationCluster", ".setKeyboardNavigationCluster(%s);\n");
		codeMatcher.put("android:layerType", ".setLayerType(%s);\n");
		codeMatcher.put("android:layoutDirection", ".setLayoutDirection(%s);\n");
		codeMatcher.put("android:longClickable", ".setLongClickable(%s);\n");
		codeMatcher.put("android:minHeight", ".setMinimumHeight(%s);\n");
		codeMatcher.put("android:minWidth", ".setMinimumWidth(%s);\n");
		codeMatcher.put("android:nextClusterForward", ".setNextClusterForwardId(%s);\n");
		codeMatcher.put("android:nextFocusDown", ".setNextFocusDownId(%s);\n");
		codeMatcher.put("android:nextFocusForward", ".setNextFocusForwardId(%s);\n");
		codeMatcher.put("android:nextFocusLeft", ".setNextFocusLeftId(%s);\n");
		codeMatcher.put("android:nextFocusRight", ".setNextFocusRightId(%s);\n");
		codeMatcher.put("android:nextFocusUp", ".setNextFocusUpId(%s);\n");
		codeMatcher.put("android:onClick", "(%s);\n");
		codeMatcher.put("android:outlineAmbientShadowColor", ".setOutlineAmbientShadowColor(%s);\n");
		codeMatcher.put("android:outlineSpotShadowColor", ".setOutlineSpotShadowColor(%s);\n");
		codeMatcher.put("android:padding", ".setPaddingRelative(%s);\n");
		codeMatcher.put("android:paddingBottom", ".setPaddingRelative(%s);\n");
		codeMatcher.put("android:paddingEnd", ".setPaddingRelative(%s);\n");
		codeMatcher.put("android:paddingHorizontal", "(%s);\n");
		codeMatcher.put("android:paddingLeft", ".setPadding(%s);\n");
		codeMatcher.put("android:paddingRight", ".setPadding(%s);\n");
		codeMatcher.put("android:paddingStart", ".setPaddingRelative(%s);\n");
		codeMatcher.put("android:paddingTop", ".setPaddingRelative(%s);\n");
		codeMatcher.put("android:paddingVertical", "(%s);\n");
		codeMatcher.put("android:requiresFadingEdge", ".setVerticalFadingEdgeEnabled(%s);\n");
		codeMatcher.put("android:rotation", ".setRotation(%s);\n");
		codeMatcher.put("android:rotationX", ".setRotationX(%s);\n");
		codeMatcher.put("android:rotationY", ".setRotationY(%s);\n");
		codeMatcher.put("android:saveEnabled", ".setSaveEnabled(%s);\n");
		codeMatcher.put("android:scaleX", ".setScaleX(%s);\n");
		codeMatcher.put("android:scaleY", ".setScaleY(%s);\n");
		codeMatcher.put("android:scrollIndicators", ".setScrollIndicators(%s);\n");
		codeMatcher.put("android:scrollX", "(%s);\n");
		codeMatcher.put("android:scrollY", "(%s);\n");
		codeMatcher.put("android:scrollbarAlwaysDrawHorizontalTrack", "(%s);\n");
		codeMatcher.put("android:scrollbarAlwaysDrawVerticalTrack", "(%s);\n");
		codeMatcher.put("android:scrollbarDefaultDelayBeforeFade", ".setScrollBarDefaultDelayBeforeFade(%s);\n");
		codeMatcher.put("android:scrollbarFadeDuration", ".setScrollBarFadeDuration(%s);\n");
		codeMatcher.put("android:scrollbarSize", ".setScrollBarSize(%s);\n");
		codeMatcher.put("android:scrollbarStyle", ".setScrollBarStyle(%s);\n");
		codeMatcher.put("android:scrollbarThumbHorizontal", ".setHorizontalScrollbarThumbDrawable(%s);\n");
		codeMatcher.put("android:scrollbarThumbVertical", ".setVerticalScrollbarThumbDrawable(%s);\n");
		codeMatcher.put("android:scrollbarTrackHorizontal", ".setHorizontalScrollbarTrackDrawable(%s);\n");
		codeMatcher.put("android:scrollbarTrackVertical", ".setVerticalScrollbarTrackDrawable(%s);\n");
		codeMatcher.put("android:scrollbars", "(%s);\n");
		codeMatcher.put("android:soundEffectsEnabled", ".setSoundEffectsEnabled(%s);\n");
		codeMatcher.put("android:stateListAnimator", "(%s);\n");
		codeMatcher.put("android:tag", "(%s);\n");
		codeMatcher.put("android:textAlignment", ".setTextAlignment(%s);\n");
		codeMatcher.put("android:textDirection", ".setTextDirection(%s);\n");
		codeMatcher.put("android:text", ".setText(\"%s\");\n");
		codeMatcher.put("android:theme", "(%s);\n");
		codeMatcher.put("android:tooltipText", ".setTooltipText(%s);\n");
		codeMatcher.put("android:transformPivotX", ".setPivotX(%s);\n");
		codeMatcher.put("android:transformPivotY", ".setPivotY(%s);\n");
		codeMatcher.put("android:transitionName", "(%s);\n");
		codeMatcher.put("android:translationX", ".setTranslationX(%s);\n");
		codeMatcher.put("android:translationY", ".setTranslationY(%s);\n");
		codeMatcher.put("android:translationZ", ".setTranslationZ(%s);\n");
		codeMatcher.put("android:visibility", ".setVisibility(%s);\n");

	}

}