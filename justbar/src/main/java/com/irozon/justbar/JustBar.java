package com.irozon.justbar;

import ohos.agp.components.AttrSet;
import ohos.agp.components.Component;
import ohos.agp.components.Component.EstimateSizeListener;
import ohos.agp.components.ComponentTransition;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.utils.LayoutAlignment;
import ohos.app.Context;
import com.irozon.justbar.interfaces.OnBarItemClickListener;

/**
 * This is JustBar class.
 */
public class JustBar extends DirectionalLayout implements Component.ClickedListener, EstimateSizeListener {
    private final Context context;
    private boolean initialSetup;
    private OnBarItemClickListener onBarItemClickListener;

    /**
     * This is a constructor of JustBar.

     * @param context Context
     */
    public JustBar(Context context) {
        super(context);
        this.context = context;
        init();
        setEstimateSizeListener(this::onEstimateSize);
    }

    /**
     * This is a constructor of JustBar.

     * @param context Context
     * @param attrs AttrSet
     */
    public JustBar(Context context, AttrSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
        setEstimateSizeListener(this::onEstimateSize);
    }

    /**
     * This is a constructor of JustBar.

     * @param context Context
     * @param attrs AttrSet
     * @param defStyleAttr String
     */
    public JustBar(Context context, AttrSet attrs, java.lang.String defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
        setEstimateSizeListener(this::onEstimateSize);
    }

    private void init() {
        ComponentTransition lt = new ComponentTransition();
            {
                lt.removeTransitionType(3);
            }
        setComponentTransition(lt);
        setOrientation(HORIZONTAL);
        setAlignment(LayoutAlignment.VERTICAL_CENTER);
    }

    @Override
    public boolean onEstimateSize(int widthMeasureSpec, int heightMeasureSpec) {
        if (!initialSetup) {
            // Click listeners for items
            for (int i = 0; i < getChildCount(); i++) {
                getComponentAt(i).setTag(i);
                getComponentAt(i).setClickedListener(this);
            }
            // Add spaces between all the items
            addEmptySpacesBetweenEveryItem();
            initialSetup = true;
        }
        return false;
    }

    private void addEmptySpacesBetweenEveryItem() {
        // Get Child count
        int childs = getChildCount();
        if (childs == 0) {
            return;
        }
        for (int i = 0; i <= childs * 2; i = i + 2) {
            addComponent(new EmptySpace(context), i);
        }
        invalidate();
    }

    @Override
    public void onClick(Component view) {
        // Get clicked position from tag
        int position = Integer.parseInt(view.getTag().toString());
        if (onBarItemClickListener != null) {
            onBarItemClickListener.onBarItemClick((BarItem) view, position);
        }
        // Unselect previous item
        Component selectedView = getSelected();
        if (selectedView != null) {
            selectedView.setSelected(false);
        }
        // Set new item
        view.setSelected(true);
    }

    /**
     * On BarItem click listener.

     * @param onBarItemClickListener OnBarItemClickListener
     */
    public void setOnBarItemClickListener(OnBarItemClickListener onBarItemClickListener) {
        this.onBarItemClickListener = onBarItemClickListener;
    }

    /**
     * Get item from the bar by its position.

     * @param position Position of the BarItem
     * @return  BarItem
     */
    public BarItem getItemAt(int position) {
        for (int i = 0; i < getChildCount(); i++) {
            Component child = getComponentAt(i);
            if (child instanceof BarItem) {
                if (Integer.parseInt(String.valueOf(child.getTag())) == position) {
                    return (BarItem) child;
                }
            }
        }
        return null;
    }

    /**
     * Get selected item from the bar.

     * @return BarItem - Selected item
     */
    public BarItem getSelected() {
        for (int i = 0; i < getChildCount(); i++) {
            Component child = getComponentAt(i);
            if (child instanceof BarItem) {
                if (child.isSelected()) {
                    return (BarItem) child;
                }
            }
        }
        return null;
    }

    /**
     * Set the item to be selected by its position.

     * @param position position of the item
     */
    public void setSelected(int position) {
        BarItem shouldSelected = getItemAt(position);
        if (shouldSelected == null) {
            return;
        }
        shouldSelected.callOnClick();
    }
}
