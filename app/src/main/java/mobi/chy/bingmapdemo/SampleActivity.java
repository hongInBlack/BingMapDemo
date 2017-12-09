package mobi.chy.bingmapdemo;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.CoordinateConverter;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mobi.chy.bingsdk.BingMap;
import mobi.chy.bingsdk.MapView;
import mobi.chy.bingsdk.OnMapReadyCallback;
import mobi.chy.bingsdk.serialization.entry.Color;
import mobi.chy.bingsdk.serialization.entry.Location;
import mobi.chy.bingsdk.serialization.entry.ViewOptions;
import mobi.chy.bingsdk.util.PushpinCreator;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SampleActivity extends AppCompatActivity implements OnMapReadyCallback, BingMap.OnMapClickListener, View.OnClickListener, PoiSearch.OnPoiSearchListener, AdapterView.OnItemClickListener {

    private ImageView mIvLocation;
    private ListView mLvPoi;
    private BingMap mBingMap;
    private MapView mMapView;
    private AMapLocation mLocation;
    /**
     * 声明AMapLocationClient类对象
     */
    private AMapLocationClient mLocationClient = null;
    /**
     * 声明定位回调监听器
     */
    private AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            mLocationClient.stopLocation();
            if (aMapLocation != null) {
                mLocation = aMapLocation;
                // 更新当前经纬度
                Location latLng = new Location(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                createPushpin(latLng);
                queryPoi(latLng);
                //移动地图
                ViewOptions viewOptions = new ViewOptions();
                viewOptions.setZoom(16);
                viewOptions.setCenter(latLng);
                mBingMap.updateViewOptions(viewOptions);
            }
        }
    };
    /**
     * 声明AMapLocationClientOption对象
     */
    private AMapLocationClientOption mLocationOption = null;

    /**
     * POI搜索
     */
    private PoiSearch mPoiSearch = null;

    private String mBingKey;
    private String mClientId,mClientSecret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        try {
            ApplicationInfo appInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            mBingKey = appInfo.metaData.getString("BING_MAP_KEY");
            mClientId = appInfo.metaData.getString("FOURSQUARE_CLIENT_ID");
            mClientSecret = appInfo.metaData.getString("FOURSQUARE_CLIENT_SECRET");
        } catch (PackageManager.NameNotFoundException e) {
        }
        mIvLocation = findViewById(R.id.iv_location);
        mLvPoi = findViewById(R.id.lv_poi);
        mLvPoi.setOnItemClickListener(this);
    }

    private void initLocation() {
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationClient.setLocationListener(mLocationListener);
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setOnceLocation(true);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
    }


    private void initMap() {
        mMapView = findViewById(R.id.map_view);
        mMapView.getMapAsync(mBingKey, this);
    }

    @Override
    public void onMapReady(BingMap bingMap) {
        mBingMap = bingMap;
        mBingMap.setOnMapClickListener(this);
        mIvLocation.setOnClickListener(this);
        initLocation();
    }

    @Override
    public void onClick(View view) {
        mLocationClient.startLocation();
    }

    @Override
    public void onMapClick(Location location) {
        mLocation = null;
        createPushpin(location);
        queryPoi(location);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initMap();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    private void createPushpin(Location location) {
        PushpinCreator pushpinCreator = new PushpinCreator(String.valueOf(new Random().nextInt()));
        pushpinCreator.setLocation(location);
        pushpinCreator.setPushingColor(Color.Colors.RED);
        pushpinCreator.setPushpinDraggableMode(false);
        pushpinCreator.setIcon("file:///android_asset/images/map_pin.png");
        mBingMap.clear();
        mBingMap.addPushpin(pushpinCreator.create());
    }

    private void queryPoi(Location location) {
        //先判断点位于国内还是国外
        if (CoordinateConverter.isAMapDataAvailable(location.getLatitude(),location.getLongitude())) {
            if (mPoiSearch == null) {
                PoiSearch.Query query = new PoiSearch.Query("", "");
                query.setPageSize(50);
                query.setPageNum(1);
                mPoiSearch = new PoiSearch(this, query);
                mPoiSearch.setOnPoiSearchListener(this);
            }
            mPoiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(location.getLatitude(), location.getLongitude()), 5000));
            mPoiSearch.searchPOIAsyn();
        } else {
            getFoursquareVenues(location);
        }
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        ArrayList<PoiItem> poiItems = new ArrayList<>();
        if (mLocation != null) {
            PoiItem location = new PoiItem("location", new LatLonPoint(mLocation.getLatitude(), mLocation.getLongitude()), mLocation.getPoiName(), mLocation.getAddress());
            poiItems.add(location);
        }
        List<String> data = new ArrayList<>();
        if (poiResult != null && poiResult.getPois().size() > 0) {
            poiItems.addAll(poiResult.getPois());
        }
        for (PoiItem item : poiItems) {
            data.add(item.getTitle());
        }
        ListAdapter listAdapter = new ArrayAdapter<>(SampleActivity.this, android.R.layout.simple_list_item_1, data);
        mLvPoi.setAdapter(listAdapter);
        mLvPoi.setTag(poiItems);
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //先判断数据格式
        if (mLvPoi.getTag() != null && ((List)mLvPoi.getTag()).get(0) instanceof PoiItem) {
            //国内高德数据
            List<PoiItem> poiItems = (List<PoiItem>) mLvPoi.getTag();
            PoiItem poiItem = poiItems.get(i);
            Location location = new Location(poiItem.getLatLonPoint().getLatitude(), poiItem.getLatLonPoint().getLongitude());
            createPushpin(location);
            //移动地图
            ViewOptions viewOptions = new ViewOptions();
            viewOptions.setCenter(location);
            mBingMap.updateViewOptions(viewOptions);
        } else if (mLvPoi.getTag() != null && ((List)mLvPoi.getTag()).get(0) instanceof Venue) {
            //国外foursquare数据
            List<Venue> venues = (List<Venue>) mLvPoi.getTag();
            Venue venue = venues.get(i);
            Location location = new Location(venue.getLocation().getLat(), venue.getLocation().getLng());
            createPushpin(location);
            //移动地图
            ViewOptions viewOptions = new ViewOptions();
            viewOptions.setCenter(location);
            mBingMap.updateViewOptions(viewOptions);
        }
    }

    private void getFoursquareVenues(Location location){
        OkHttpClient okHttpClient = new OkHttpClient();
        String url = "https://api.foursquare.com/v2/venues/search?v=20161016&radius=3000";
        url += "&client_id=" + mClientId;
        url += "&client_secret=" + mClientSecret;
        url += "&ll="+location.getLatitude()+","+location.getLongitude();
        Request request = new Request.Builder().url(url).method("GET",null).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                try {
                    JSONObject jsonResult = new JSONObject(result);
                    int responseCode = 0;
                    if (jsonResult.has("meta")) {
                        JSONObject meta = jsonResult.getJSONObject("meta");
                        if (meta.has("code")) {
                            responseCode = meta.getInt("code");
                        }
                    }
                    //状态码 = 200 表示可用
                    if (responseCode == 200 && jsonResult.has("response")) {
                        JSONObject jsonResponse = jsonResult.getJSONObject("response");
                        Gson gson = new Gson();
                        List<Venue> venues = gson.fromJson(jsonResponse.optString("venues"),new TypeToken<List<Venue>>() {}.getType());
                        //装载数据到列表
                        final ArrayList<Venue> venueList = new ArrayList<>();
                        if (mLocation != null) {
                            Venue location = new Venue();
                            location.setName(mLocation.getPoiName());
                            Venue.LocationBean locationBean = new Venue.LocationBean();
                            locationBean.setLat(mLocation.getLatitude());
                            locationBean.setLng(mLocation.getLongitude());
                            location.setLocation(locationBean);
                            venueList.add(location);
                        }
                        if (venues != null && venues.size() > 0) {
                            venueList.addAll(venues);
                        }
                        List<String> data = new ArrayList<>();
                        for (Venue item : venueList) {
                            data.add(item.getName());
                        }
                        final List listData = data;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ListAdapter listAdapter = new ArrayAdapter<>(SampleActivity.this, android.R.layout.simple_list_item_1, listData);
                                mLvPoi.setAdapter(listAdapter);
                                mLvPoi.setTag(venueList);
                            }
                        });
                    }
                } catch (JSONException e) {
                }
            }
        });
    }
}
