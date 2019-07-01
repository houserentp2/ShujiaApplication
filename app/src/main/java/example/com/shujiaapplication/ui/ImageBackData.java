package example.com.shujiaapplication.ui;

import com.google.gson.annotations.SerializedName;

public class ImageBackData extends Data{
    @SerializedName("code")
    private String code;
    @SerializedName("data")
    private ImageData data;

    public String getCode() {
        return code;
    }

    public ImageData getData() {
        return data;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setData(ImageData data) {
        this.data = data;
    }
}
