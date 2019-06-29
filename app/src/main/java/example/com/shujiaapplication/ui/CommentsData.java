package example.com.shujiaapplication.ui;

import com.google.gson.annotations.SerializedName;

public class CommentsData extends Data {
    @SerializedName("userid")
    private String userid;
    @SerializedName("token")
    private String token;
    @SerializedName("houseid")
    private String houseid;
    @SerializedName("comment")
    private String comment;

    public String getHouseid() {
        return houseid;
    }

    public String getUserid() {
        return userid;
    }

    public String getToken() {
        return token;
    }

    public String getComment() {
        return comment;
    }
}
