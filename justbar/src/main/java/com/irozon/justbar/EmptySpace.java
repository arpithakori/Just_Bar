package com.irozon.justbar;

import ohos.agp.components.AttrSet;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.app.Context;

/**
 * This is a EmptySpace class.
 */
public class EmptySpace extends Component {

    public EmptySpace(Context context) {
        super(context);
        init();
    }

    public EmptySpace(Context context, AttrSet attrs) {
        super(context, attrs);
        init();
    }

    public EmptySpace(Context context, AttrSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        ohos.agp.components.DirectionalLayout.LayoutConfig layoutParams = new DirectionalLayout.LayoutConfig(0, 0);
        layoutParams.weight = 1;
        setLayoutConfig(layoutParams);
    }
}
