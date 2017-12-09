package mobi.chy.bingsdk.backend;


import mobi.chy.bingsdk.backend.base.BaseCallback;
import mobi.chy.bingsdk.serialization.entry.Point;

/**
 * Created by Leonid Veremchuk on 11/10/16.
 */

public interface IBingScreenLocationCallback extends BaseCallback {
    void onPoint(Point point);
}
