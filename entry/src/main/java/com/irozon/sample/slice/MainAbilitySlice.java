/*
 * Copyright (C) 2020-21 Application Library Engineering Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.irozon.sample.slice;

import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import com.irozon.justbar.BarItem;
import com.irozon.justbar.JustBar;
import com.irozon.justbar.interfaces.OnBarItemClickListener;
import com.irozon.sample.ResourceTable;

/**
 * Sample app to test the JustBar library functionality.
 */
public class MainAbilitySlice extends AbilitySlice {
    JustBar justBar;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        justBar = (JustBar) findComponentById(ResourceTable.Id_bottomBar);

        justBar.setOnBarItemClickListener(new OnBarItemClickListener() {
            @Override
            public void onBarItemClick(BarItem barItem, int position) {
                //this function is empty
            }
        });
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
