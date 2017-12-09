package mobi.chy.bingsdk.backend;


import mobi.chy.bingsdk.backend.base.BaseCallback;
import mobi.chy.bingsdk.serialization.entry.Location;

/**
 * Created by Leonid Veremchuk on 11/3/16.
 */

public interface IBindMapClickCallback extends BaseCallback {
    void onMapClick(Location location);
}
