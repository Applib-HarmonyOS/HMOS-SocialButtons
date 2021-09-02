package in.championswimmer.libsocialbuttons;

import ohos.agp.components.AttrSet;
import ohos.app.Context;
import com.github.clans.fab.FloatingActionButton;

/**
 * SocialFab class.
 * Created by talat on 08-07-2016
 */
public class SocialFab extends FloatingActionButton {

    /**
     * SocialFab Constructor.
     *
     * @param context context
     */
    public SocialFab(Context context) {
        super(context);
    }

    /**
     * SocialFab Constructor.
     *
     * @param context context
     * @param attrSet attrSet
     */
    public SocialFab(Context context, AttrSet attrSet) {
        super(context, attrSet);
    }

    /**
     * SocialFab Constructor.
     *
     * @param context context
     * @param attrSet attrSet
     * @param s s
     */
    public SocialFab(Context context, AttrSet attrSet, String s) {
        super(context, attrSet, s);
    }
}