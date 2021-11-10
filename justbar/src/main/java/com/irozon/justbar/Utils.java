package com.irozon.justbar;

import ohos.agp.components.AttrHelper;
import ohos.app.Context;

class Utils {
    public static int dpToPixel(Context context, float dp) {
        return AttrHelper.vp2px(dp, context);
    }
}