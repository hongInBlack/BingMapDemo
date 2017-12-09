package mobi.chy.bingmapdemo;


import java.util.List;

/**
 * https://api.foursquare.com/v2/venues/search?ll=40.722885,-74.0029261&radius=3000&client_id=TESBVHX5NCPPLUO51RZ15IVXZITDSFTXXEWHQ2UGTEP5VGB3&client_secret=VK1PN0LUSLXTQSHSPEYX4222P0SBEXWQIGOETBHQTBXWEOHQ&v=20161016
 * <p>
 * Created by chengyong on 2017/12/8.
 */

public class Venue {

    /**
     * id : 50958c9a582feb7353e844ba
     * name : PaintNite NYC at The Cupping Room Cafe
     * contact : {"phone":"6463897480","formattedPhone":"(646) 389-7480","twitter":"paintnitenyc","facebook":"276892912411875"}
     * location : {"address":"359 W Broadway","crossStreet":"\"btwn Broome & Grand St\"","lat":40.72297381933082,"lng":-74.00315523147583,"labeledLatLngs":[{"label":"display","lat":40.72297381933082,"lng":-74.00315523147583}],"distance":21,"postalCode":"10013","cc":"US","city":"纽约","state":"NY","country":"美国","formattedAddress":["359 W Broadway (\"btwn Broome & Grand St\")","纽约, NY 10013","美国"]}
     * categories : [{"id":"4bf58dd8d48988d11a941735","name":"Other Nightlife","pluralName":"Other Nightlife","shortName":"Nightlife","icon":{"prefix":"https://ss3.4sqi.net/img/categories_v2/nightlife/default_","suffix":".png"},"primary":true}]
     * verified : true
     * stats : {"checkinsCount":27,"usersCount":26,"tipCount":0}
     * url : http://www.paintnite.com/newyork
     * allowMenuUrlEdit : true
     * beenHere : {"lastCheckinExpiredAt":0}
     * specials : {"count":0,"items":[]}
     * storeId : cuppingroomcafe
     * hereNow : {"count":0,"summary":"Nobody here","groups":[]}
     * referralId : v-1512731665
     * venueChains : [{"id":"556e5e12bd6a82902e29b3fc"}]
     * hasPerk : false
     */

    private String id;
    private String name;
    private ContactBean contact;
    private LocationBean location;
    private boolean verified;
    private StatsBean stats;
    private String url;
    private boolean allowMenuUrlEdit;
    private BeenHereBean beenHere;
    private SpecialsBean specials;
    private String storeId;
    private HereNowBean hereNow;
    private String referralId;
    private boolean hasPerk;
    private List<CategoriesBean> categories;
    private List<VenueChainsBean> venueChains;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContactBean getContact() {
        return contact;
    }

    public void setContact(ContactBean contact) {
        this.contact = contact;
    }

    public LocationBean getLocation() {
        return location;
    }

    public void setLocation(LocationBean location) {
        this.location = location;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public StatsBean getStats() {
        return stats;
    }

    public void setStats(StatsBean stats) {
        this.stats = stats;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isAllowMenuUrlEdit() {
        return allowMenuUrlEdit;
    }

    public void setAllowMenuUrlEdit(boolean allowMenuUrlEdit) {
        this.allowMenuUrlEdit = allowMenuUrlEdit;
    }

    public BeenHereBean getBeenHere() {
        return beenHere;
    }

    public void setBeenHere(BeenHereBean beenHere) {
        this.beenHere = beenHere;
    }

    public SpecialsBean getSpecials() {
        return specials;
    }

    public void setSpecials(SpecialsBean specials) {
        this.specials = specials;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public HereNowBean getHereNow() {
        return hereNow;
    }

    public void setHereNow(HereNowBean hereNow) {
        this.hereNow = hereNow;
    }

    public String getReferralId() {
        return referralId;
    }

    public void setReferralId(String referralId) {
        this.referralId = referralId;
    }

    public boolean isHasPerk() {
        return hasPerk;
    }

    public void setHasPerk(boolean hasPerk) {
        this.hasPerk = hasPerk;
    }

    public List<CategoriesBean> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesBean> categories) {
        this.categories = categories;
    }

    public List<VenueChainsBean> getVenueChains() {
        return venueChains;
    }

    public void setVenueChains(List<VenueChainsBean> venueChains) {
        this.venueChains = venueChains;
    }

    public static class ContactBean {
        /**
         * phone : 6463897480
         * formattedPhone : (646) 389-7480
         * twitter : paintnitenyc
         * facebook : 276892912411875
         */

        private String phone;
        private String formattedPhone;
        private String twitter;
        private String facebook;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getFormattedPhone() {
            return formattedPhone;
        }

        public void setFormattedPhone(String formattedPhone) {
            this.formattedPhone = formattedPhone;
        }

        public String getTwitter() {
            return twitter;
        }

        public void setTwitter(String twitter) {
            this.twitter = twitter;
        }

        public String getFacebook() {
            return facebook;
        }

        public void setFacebook(String facebook) {
            this.facebook = facebook;
        }
    }

    public static class LocationBean {
        /**
         * address : 359 W Broadway
         * crossStreet : "btwn Broome & Grand St"
         * lat : 40.72297381933082
         * lng : -74.00315523147583
         * labeledLatLngs : [{"label":"display","lat":40.72297381933082,"lng":-74.00315523147583}]
         * distance : 21
         * postalCode : 10013
         * cc : US
         * city : 纽约
         * state : NY
         * country : 美国
         * formattedAddress : ["359 W Broadway (\"btwn Broome & Grand St\")","纽约, NY 10013","美国"]
         */

        private String address;
        private String crossStreet;
        private double lat;
        private double lng;
        private int distance;
        private String postalCode;
        private String cc;
        private String city;
        private String state;
        private String country;
        private List<LabeledLatLngsBean> labeledLatLngs;
        private List<String> formattedAddress;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCrossStreet() {
            return crossStreet;
        }

        public void setCrossStreet(String crossStreet) {
            this.crossStreet = crossStreet;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public String getCc() {
            return cc;
        }

        public void setCc(String cc) {
            this.cc = cc;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public List<LabeledLatLngsBean> getLabeledLatLngs() {
            return labeledLatLngs;
        }

        public void setLabeledLatLngs(List<LabeledLatLngsBean> labeledLatLngs) {
            this.labeledLatLngs = labeledLatLngs;
        }

        public List<String> getFormattedAddress() {
            return formattedAddress;
        }

        public void setFormattedAddress(List<String> formattedAddress) {
            this.formattedAddress = formattedAddress;
        }

        public static class LabeledLatLngsBean {
            /**
             * label : display
             * lat : 40.72297381933082
             * lng : -74.00315523147583
             */

            private String label;
            private double lat;
            private double lng;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }
        }
    }

    public static class StatsBean {
        /**
         * checkinsCount : 27
         * usersCount : 26
         * tipCount : 0
         */

        private int checkinsCount;
        private int usersCount;
        private int tipCount;

        public int getCheckinsCount() {
            return checkinsCount;
        }

        public void setCheckinsCount(int checkinsCount) {
            this.checkinsCount = checkinsCount;
        }

        public int getUsersCount() {
            return usersCount;
        }

        public void setUsersCount(int usersCount) {
            this.usersCount = usersCount;
        }

        public int getTipCount() {
            return tipCount;
        }

        public void setTipCount(int tipCount) {
            this.tipCount = tipCount;
        }
    }

    public static class BeenHereBean {
        /**
         * lastCheckinExpiredAt : 0
         */

        private int lastCheckinExpiredAt;

        public int getLastCheckinExpiredAt() {
            return lastCheckinExpiredAt;
        }

        public void setLastCheckinExpiredAt(int lastCheckinExpiredAt) {
            this.lastCheckinExpiredAt = lastCheckinExpiredAt;
        }
    }

    public static class SpecialsBean {
        /**
         * count : 0
         * items : []
         */

        private int count;
        private List<?> items;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<?> getItems() {
            return items;
        }

        public void setItems(List<?> items) {
            this.items = items;
        }
    }

    public static class HereNowBean {
        /**
         * count : 0
         * summary : Nobody here
         * groups : []
         */

        private int count;
        private String summary;
        private List<?> groups;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public List<?> getGroups() {
            return groups;
        }

        public void setGroups(List<?> groups) {
            this.groups = groups;
        }
    }

    public static class CategoriesBean {
        /**
         * id : 4bf58dd8d48988d11a941735
         * name : Other Nightlife
         * pluralName : Other Nightlife
         * shortName : Nightlife
         * icon : {"prefix":"https://ss3.4sqi.net/img/categories_v2/nightlife/default_","suffix":".png"}
         * primary : true
         */

        private String id;
        private String name;
        private String pluralName;
        private String shortName;
        private IconBean icon;
        private boolean primary;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPluralName() {
            return pluralName;
        }

        public void setPluralName(String pluralName) {
            this.pluralName = pluralName;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public IconBean getIcon() {
            return icon;
        }

        public void setIcon(IconBean icon) {
            this.icon = icon;
        }

        public boolean isPrimary() {
            return primary;
        }

        public void setPrimary(boolean primary) {
            this.primary = primary;
        }

        public static class IconBean {
            /**
             * prefix : https://ss3.4sqi.net/img/categories_v2/nightlife/default_
             * suffix : .png
             */

            private String prefix;
            private String suffix;

            public String getPrefix() {
                return prefix;
            }

            public void setPrefix(String prefix) {
                this.prefix = prefix;
            }

            public String getSuffix() {
                return suffix;
            }

            public void setSuffix(String suffix) {
                this.suffix = suffix;
            }
        }
    }

    public static class VenueChainsBean {
        /**
         * id : 556e5e12bd6a82902e29b3fc
         */

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
