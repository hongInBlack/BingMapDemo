package mobi.chy.bingsdk;

import android.util.Log;

import mobi.chy.bingsdk.backend.IBingScreenLocationCallback;
import mobi.chy.bingsdk.serialization.entry.Location;
import mobi.chy.bingsdk.serialization.entry.Point;

/**
 * Created by Leonid Veremchuk on 11/10/16.
 */

public class Projection {
    MapController mMapController;
    private OnAsyncLoadPointCallback mCallback;


    Projection(MapController controller) {
        mMapController = controller;
    }

    public void toScreenLocation(Location marker, OnAsyncLoadPointCallback callback) {
        mMapController.toScreenLocation(marker, new IBingScreenLocationCallback() {
            @Override
            public void onPoint(Point point) {
                Log.d(Projection.class.getSimpleName(), "onPoint() called with: point = x [" + point.getX() + "]" + "   y [" + point.getY() + "]");
                mCallback.onPoint(point);
            }
        });
        mCallback = callback;

    }

    public interface OnAsyncLoadPointCallback {
        void onPoint(Point screenPoint);
    }
}
