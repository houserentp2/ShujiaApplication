package example.com.shujiaapplication.ui;

import com.google.gson.annotations.SerializedName;

public class DiscountListData extends Data {
    @SerializedName("userid")
    private String userid;
    @SerializedName("discounted")
    private String discounted;
    @SerializedName("reduce")
    private String reduce;
    @SerializedName("type")
    private String type;
    @SerializedName("description")
    private String description;
    @SerializedName("useable")
    private String useable;
    @SerializedName("outdate")
    private String outdate;

    public String getOutdate() {
        return outdate;
    }
    public void setOutdate(String outdate) {
        this.outdate = outdate;
    }
    public String getUserid() {
        return userid;
    }

    public String getDescription() {
        return description;
    }

    public String getDiscounted() {
        return discounted;
    }

    public String getReduce() {
        return reduce;
    }

    public String getType() {
        return type;
    }

    public String getUseable() {
        return useable;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDiscounted(String discounted) {
        this.discounted = discounted;
    }

    public void setReduce(String reduce) {
        this.reduce = reduce;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUseable(String useable) {
        this.useable = useable;
    }
}
