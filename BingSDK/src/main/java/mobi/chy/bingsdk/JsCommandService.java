package mobi.chy.bingsdk;

import android.util.Log;

import mobi.chy.bingsdk.backend.base.BaseCallback;
import mobi.chy.bingsdk.serialization.ISerializable;
import mobi.chy.bingsdk.serialization.SerializationManager;
import mobi.chy.bingsdk.serialization.entry.CameraUpdate;
import mobi.chy.bingsdk.serialization.entry.Infobox;
import mobi.chy.bingsdk.serialization.entry.Location;
import mobi.chy.bingsdk.serialization.entry.MapOption;
import mobi.chy.bingsdk.serialization.entry.Pushpin;
import mobi.chy.bingsdk.serialization.entry.ViewOptions;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Leonid Veremchuk on 11/4/16.
 */

class JsCommandService {
    private String TAG = getClass().getSimpleName();

    private static JsCommandService sInstance;

    private IBindMapJsCommandCallback mIBindMapJsCommandCallback;
    private JsCommandServiceObserver mObserver;

    static JsCommandService getInstance() {
        if (sInstance == null) {
            sInstance = new JsCommandService();
        }
        return sInstance;
    }

    private JsCommandService() {
        mObserver = new JsCommandServiceObserver();
    }

    void init(IBindMapJsCommandCallback callback) {
        mIBindMapJsCommandCallback = callback;
    }

    void loadAssets() {
        List<Object> jsInterfaceObjects = mObserver.applyInterfaces();
        mIBindMapJsCommandCallback.setInterfaceList(jsInterfaceObjects, "file:///android_asset/api/bingmap_v1_0.html");
    }

    void loadMapScenario(String key) {
        mIBindMapJsCommandCallback.loadFunction("javascript:loadMapScenario('" + key + "');");
    }

    void loadPushpin(Pushpin pushpin) {
            mIBindMapJsCommandCallback.loadFunction("javascript:addPushpin(" + new SerializationManager<>().serialize(pushpin) + ")");
    }

    void loadPushpins(List<ISerializable> pushpinList) {
        mIBindMapJsCommandCallback.loadFunction("javascript:addPushpinsArray(" + Arrays.toString(new SerializationManager<>().serializeToStringArray(pushpinList)) + ")");
    }

    void addPushpinClickListener() {
        mIBindMapJsCommandCallback.loadFunction("javascript:addPushpinClickListener()");
    }

    void moveCamera(CameraUpdate cameraUpdate) {
        mIBindMapJsCommandCallback.loadFunction("javascript:moveCamera(" + new SerializationManager<>().serialize(cameraUpdate) + ")");
    }

    void updateViewOptions(ViewOptions viewOptions) {
        mIBindMapJsCommandCallback.loadFunction("javascript:updateViewOptions(" + new SerializationManager<>().serialize(viewOptions) + ")");
    }

    void updateMapOptions(MapOption mapOption) {
        mIBindMapJsCommandCallback.loadFunction("javascript:updateMapOptions(" + new SerializationManager<>().serialize(mapOption) + ")");
    }

    void getScreenLocation(Location marker) {
        mIBindMapJsCommandCallback.loadFunction("javascript:getScreenLocation(" + new SerializationManager<>().serialize(marker) + ")");
    }

    void showInfobox(Infobox infobox) {
        mIBindMapJsCommandCallback.loadFunction("javascript:showInfobox(" + new SerializationManager<>().serialize(infobox) + ")");
    }

    void clearPushpin() {
        mIBindMapJsCommandCallback.loadFunction("javascript:clearPushpins()");
    }

    void observe(BaseCallback baseCallback, Class clazz) {
        Log.d(TAG, "observe() called with: callback = [" + baseCallback + "]");
        mObserver.observe(baseCallback, clazz);
    }
}
