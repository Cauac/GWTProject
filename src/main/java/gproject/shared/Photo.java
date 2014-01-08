package gproject.shared;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class Photo implements Serializable {
    private String title;
    private String ownerName;
    private String views;
    private Date dateupload;
    private Date lastupdate;
    private String url;
    private String id;
    private String largeSizeUrl;

    public static Photo parse(Map object) {
        Photo result = new Photo();
        result.title = object.get("title").toString();
        result.ownerName = object.get("ownername").toString();
        result.views = object.get("views").toString();
        result.dateupload = new Date(Long.parseLong(object.get("dateupload").toString() + "000"));
        result.lastupdate = new Date(Long.parseLong(object.get("lastupdate").toString() + "000"));
        result.url = object.get("url_sq").toString();
        result.id = object.get("_id").toString();
        result.largeSizeUrl = object.get("url_n").toString();
        return result;
    }

    public String getTitle() {
        return title;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getViews() {
        return views;
    }

    public Date getDateupload() {
        return dateupload;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }

    public String getLargeSizeUrl() {
        return largeSizeUrl;
    }
}
