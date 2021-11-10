package com.irozon.justbar;

import static com.irozon.justbar.Utils.dpToPixel;
import ohos.agp.animation.Animator;
import ohos.agp.animation.AnimatorProperty;
import ohos.agp.animation.AnimatorValue;
import  ohos.agp.components.AttrSet;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.DependentLayout;
import ohos.agp.components.Image;
import ohos.agp.components.element.Element;
import ohos.agp.components.element.ElementScatter;
import ohos.agp.components.element.VectorElement;
import ohos.agp.render.BlendMode;
import ohos.agp.render.Canvas;
import ohos.agp.render.ColorMatrix;
import ohos.agp.utils.Color;
import ohos.app.Context;

/**
 * This is a BarItem class.
 */
public class BarItem extends DependentLayout implements Component.EstimateSizeListener, Component.DrawTask {
    private static final String default_unselected_color = "#FF0000";
    private static final String default_selected_color = "#0000FF";
    private static final String default_unselected_icon_color = "#0000FF";
    private static final String default_selected_icon_color = "#FF0000";
    private int defaultRadius = 0;
    private final Context context;
    private Image imageView;
    private boolean selected;
    private int selectedColor;
    private int unSelectedColor;
    private int selectedIconColor;
    private int unSelectedIconColor;
    private int diameter;

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
        if (selected) {
            makeSelected();
        } else {
            makeUnSelected();
        }

    }

    /**
     * This is a constructor of BarItem.

     * @param context Context
     */
    public BarItem(Context context) {
        super(context);

        this.context = context;
        setEstimateSizeListener(this::onEstimateSize);
        init(null);
    }

    /**
     * This is a constructor of BarItem.

     * @param context Context
     * @param attrs AttrSet
     */
    public BarItem(Context context, AttrSet attrs) {
        super(context, attrs);

        this.context = context;
        setEstimateSizeListener(this::onEstimateSize);
        init(attrs);
    }

    /**
     * This is a constructor of BarItem.

     * @param context Context
     * @param attrs AttrSet
     * @param defStyleAttr String
     */
    public BarItem(Context context, AttrSet attrs, String defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.context = context;
        setEstimateSizeListener(this::onEstimateSize);
        init(attrs);
    }

    @Override
    public boolean onEstimateSize(int widthMeasureSpec, int heightMeasureSpec) {
        getLayoutConfig().width = diameter;
        getLayoutConfig().height = diameter;
        addDrawTask(this);
        return false;
    }

    @Override
    public void onDraw(Component component, Canvas canvas) {
        setLayoutConfig(getLayoutConfig());
    }

    private void init(AttrSet attrs) {
        // Get Radius
        diameter = getRadius(attrs) * 2;
        defaultRadius = (int) dpToPixel(getContext(), 25);
        // Get Selected Status
        selected = getSelectedStatus(attrs);

        // Get icon from attributes
        final Element icon = getIcon(attrs);
        // Get selected/unselected color
        unSelectedColor = getUnSelectedColor(attrs);
        selectedColor = getSelectedColor(attrs);

        // Get selected/unselected color for icon
        unSelectedIconColor = getUnSelectedIconColor(attrs);
        selectedIconColor = getSelectedIconColor(attrs);

        // Add background to item
        setBackground(ElementScatter.getInstance(getContext()).parse(ResourceTable.Graphic_round_background));

        // Add imageView
        DependentLayout.LayoutConfig layoutParams = new DependentLayout
                .LayoutConfig(ComponentContainer.LayoutConfig.MATCH_CONTENT,
                ComponentContainer.LayoutConfig.MATCH_CONTENT);
        layoutParams.addRule(LayoutConfig.CENTER_IN_PARENT); // A position in layout.
        imageView = new Image(context);
        imageView.setLayoutConfig(layoutParams);
        if (icon != null) {
            imageView.setImageElement(icon);
        }
        addComponent(imageView);
        if (selected) {
            makeSelected();
        }
        setInitialColors();
    }

    /**
     * Set initial color of the BarItem according to the attributes.
     */
    private void setInitialColors() {
        if (selected) {
            getBackgroundElement().setColorMatrix(createColorMatrix(selectedColor));
            getBackgroundElement().setStateColorMode(BlendMode.SRC_IN);
            imageView.getImageElement().setColorMatrix(createColorMatrix(selectedIconColor));
        } else {
            getBackgroundElement().setColorMatrix(createColorMatrix(unSelectedColor));
            getBackgroundElement().setStateColorMode(BlendMode.SRC_IN);
            imageView.getImageElement().setColorMatrix(createColorMatrix(unSelectedIconColor));
        }
    }

    /**
     * Get initial state of the BarItem (selected/unselected).
     *
     * @param attrs AttributeSet
     * @return selected or unselected
     */
    private boolean getSelectedStatus(AttrSet attrs) {
        try {
            return attrs.getAttr("selected").isPresent() ? attrs.getAttr("selected").get().getBoolValue() : false;
        } catch (Exception e) {
            return false;
        } finally {
            //do nothing
        }
    }

    /**
     * Get radius from the attributes.
     *
     * @param attrs AttributeSet
     * @return radius of the BarItem
     */
    private int getRadius(AttrSet attrs) {
        try {
            return attrs.getAttr("radius").isPresent() ? attrs
                    .getAttr("radius").get().getDimensionValue() : defaultRadius;
        } catch (Exception e) {
            return defaultRadius;
        } finally {
            // do nothing
        }
    }

    /**
     * Get unselected color for BarItem from the attribute.
     *
     * @param attrs AttributeSet
     * @return Color for unselected state for BarItem
     */
    private int getUnSelectedColor(AttrSet attrs) {
        try {
            return attrs.getAttr("unSelectedColor").isPresent()
                    ? attrs.getAttr("unSelectedColor").get().getColorValue().getValue()
                    : Color.getIntColor(default_unselected_color);

        } catch (Exception e) {
            return Color.getIntColor(default_unselected_color);
        } finally {
            // do nothing
        }
    }

    /**
     * Get selected color for BarItem from the attribute.
     *
     * @param attrs AttributeSet
     * @return Color for selected state for BarItem
     */
    private int getSelectedColor(AttrSet attrs) {
        try {
            return attrs.getAttr("selectedColor").isPresent()
                    ? attrs.getAttr("selectedColor").get().getColorValue().getValue()
                        : Color.getIntColor(default_selected_color);
        } catch (Exception e) {
            return Color.getIntColor(default_selected_color);
        } finally {
            //do nothing
        }
    }

    /**
     * Get unselected color for icon from the attribute.
     *
     * @param attrs AttributeSet
     * @return Color for unselected state for icon
     */
    private int getUnSelectedIconColor(AttrSet attrs) {
        try {
            return attrs.getAttr("unSelectedIconColor").isPresent() ? attrs
                    .getAttr("unSelectedIconColor").get().getColorValue()
                    .getValue() : Color.getIntColor(default_unselected_icon_color);
        } catch (Exception e) {
            return Color.getIntColor(default_unselected_icon_color);
        } finally {
            //do nothing
        }
    }

    /**
     * Get Selected color for icon from the attribute.
     *
     * @param attrs AttributeSet
     * @return Color for selected state for icon
     */
    private int getSelectedIconColor(AttrSet attrs) {
        try {
            return attrs.getAttr("selectedIconColor").isPresent() ? attrs
                    .getAttr("selectedIconColor").get().getColorValue()
                    .getValue() : Color.getIntColor(default_selected_icon_color);
        } catch (Exception e) {
            return Color.getIntColor(default_selected_icon_color);
        }
    }

    /**
     * Get Icon from the attributes.
     *
     * @param attrs AttributeSet
     * @return Icon from the attributes provided
     */
    private Element getIcon(AttrSet attrs) {
        try {
            return attrs.getAttr("icon").isPresent() ? attrs
                    .getAttr("icon").get().getElement() : new VectorElement(getContext(),
                    ResourceTable.Graphic_round_background);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Make BarItem unselected.
     */
    private void makeSelected() {
        AnimatorProperty animatorProperty = this.createAnimatorProperty();
        animatorProperty.setCurveType(Animator.CurveType.ACCELERATE);
        animatorProperty.scaleX(1.5f).scaleXFrom(1);
        animatorProperty.start();
        invalidate();
        animateColor(this, unSelectedColor, selectedColor);
        animateColor(imageView, unSelectedIconColor, selectedIconColor);
    }

    /**
     * Make BarItem unselected.
     */
    private void makeUnSelected() {
        AnimatorProperty animatorProperty = this.createAnimatorProperty();
        animatorProperty.setCurveType(Animator.CurveType.ACCELERATE);
        animatorProperty.scaleX(1f).scaleXFrom(1.5f);
        animatorProperty.start();
        animateColor(this, selectedColor, unSelectedColor);
        animateColor(imageView, selectedIconColor, unSelectedIconColor);
    }

    /**
     * Animate Color on the view.
     *
     * @param view      The view that's color going to change
     * @param fromColor Start color
     * @param toColor   End color
     */
    public void animateColor(final Component view, int fromColor, int toColor) {
        AnimatorValue valueAnimator;
        valueAnimator = new AnimatorValue();
        if (view instanceof Image) {
            ((Image) view).getImageElement().setColorMatrix(createColorMatrix(toColor));
        } else {
            getBackgroundElement().setColorMatrix(createColorMatrix(toColor));
        }
    }

    private ColorMatrix createColorMatrix(int colorCode) {
        final ColorMatrix colorMatrix = new ColorMatrix();
        float[] colorTransform = new float[20];
        if (colorCode == Color.RED.getValue()) {
            colorTransform = new float[]{0, 1f, 0, 0, 0, 0, 0, 0f, 0, 0, 0, 0, 0, 0f, 0, 0, 0, 0, 1f, 0};
        } else if (colorCode == Color.GREEN.getValue()) {
            colorTransform = new float[]{0, 0f, 0, 0, 0, 0, 0, 1f, 0, 0, 0, 0, 0, 0f, 0, 0, 0, 0, 1f, 0};
        } else if (colorCode == Color.BLUE.getValue()) {
            colorTransform = new float[]{0, 0f, 0, 0, 0, 0, 0, 0f, 0, 0, 0, 0, 0, 1f, 0, 0, 0, 0, 1f, 0};
        }
        colorMatrix.setSaturation(0f);
        colorMatrix.setMatrix(colorTransform);
        return colorMatrix;
    }
}