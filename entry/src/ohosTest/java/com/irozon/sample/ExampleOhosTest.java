package com.irozon.sample;

import com.irozon.justbar.BarItem;
import com.irozon.justbar.JustBar;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import ohos.agp.animation.Animator;
import ohos.agp.animation.AnimatorProperty;
import ohos.agp.components.Attr;
import ohos.agp.components.Component;
import ohos.agp.render.ColorMatrix;
import org.junit.Before;
import org.junit.Test;
import ohos.agp.components.AttrSet;
import ohos.app.Context;

import java.util.Optional;

import static org.junit.Assert.*;

public class ExampleOhosTest {
    private Context context;
    private AttrSet attrSet;

    @Before
    public void setUp() {
        context = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        attrSet = new AttrSet() {
            @Override
            public Optional<String> getStyle() {
                return Optional.empty();
            }

            @Override
            public int getLength() {
                return 0;
            }

            @Override
            public Optional<Attr> getAttr(int i) {
                return Optional.empty();
            }

            @Override
            public Optional<Attr> getAttr(String s) {
                return Optional.empty();
            }
        };

    }

    @Test
    public void testgetSelected() {
        JustBar justBar = new JustBar(context, attrSet);
        justBar.setSelected(5);
        assertNull(justBar.getSelected());
    }

    @Test
    public void testBundleName() {
        final String actualBundleName = AbilityDelegatorRegistry.getArguments().getTestBundleName();
        assertEquals("com.irozon.sample", actualBundleName);
    }
}