package mobi.chy.bingsdk.backend;


import mobi.chy.bingsdk.backend.base.BaseCallback;
import mobi.chy.bingsdk.serialization.entry.Infobox;
import mobi.chy.bingsdk.serialization.entry.Pushpin;

/**
 * Created by Leonid Veremchuk on 11/7/16.
 */

public interface IPushpinClickCallback extends BaseCallback {
    Infobox onPushPinClick(Pushpin pushpin);
}
