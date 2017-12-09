package mobi.chy.bingsdk.serialization.entry;


import mobi.chy.bingsdk.serialization.ISerializable;

/**
 * Created by Leonid Veremchuk on 11/8/16.
 */

public class ViewOptions extends BaseBingEntry implements ISerializable {

    public void setCenter(Location location){
        mValues.put("center", location);
    }

    public void setZoom(int zoom) {
        mValues.put("zoom", zoom);
    }

    public void setMapTypeId(MapTypeId mapTypeId) {
        mValues.put("mapTypeId", mapTypeId);
    }

    public void setPadding(int paddingInPixel) {
        mValues.put("padding", paddingInPixel);
    }

    public int getZoom() {
        return (int) mValues.get("zoom");
    }

    @Override
    public String toJsObject() {
        return toBaseString(mValues);
    }
}
