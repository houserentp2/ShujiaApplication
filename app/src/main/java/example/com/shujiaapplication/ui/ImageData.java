package example.com.shujiaapplication.ui;

import com.google.gson.annotations.SerializedName;

public class ImageData extends Data {
    @SerializedName("width")
    private int width;
    @SerializedName("height")
    private int height;
    @SerializedName("filename")
    private String filename;
    @SerializedName("userid")
    private String userid;
    @SerializedName("storename")
    private String storename;
    @SerializedName("size")
    private int size;
    @SerializedName("path")
    private String path;
    @SerializedName("hash")
    private String hash;
    @SerializedName("timestamp")
    private String timestamp;
    @SerializedName("url")
    private String url;
    @SerializedName("delete")
    private String delete;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getHeight() {
        return height;
    }

    public int getSize() {
        return size;
    }

    public int getWidth() {
        return width;
    }

    public String getDelete() {
        return delete;
    }

    public String getFilename() {
        return filename;
    }

    public String getHash() {
        return hash;
    }

    public String getPath() {
        return path;
    }

    public String getStorename() {
        return storename;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getUrl() {
        return url;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
