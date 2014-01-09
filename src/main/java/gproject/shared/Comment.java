package gproject.shared;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class Comment implements Serializable {
    private String itemType;
    private String itemTitle;
    private String comment;
    private String username;
    private Date date;

    public static Comment parseMap(Map map) {
        Comment comment = new Comment();
        comment.comment = map.get("_content").toString();
        comment.itemTitle = map.get("itemTitle").toString();
        comment.username = map.get("username").toString();
        comment.itemType = map.get("itemType").toString();
        comment.date = new Date(Long.parseLong(map.get("dateadded").toString() + "000"));
        return comment;
    }


    public String getItemType() {
        return itemType;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public String getComment() {
        return comment;
    }

    public String getUsername() {
        return username;
    }

    public Date getDate() {
        return date;
    }
}
