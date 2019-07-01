package example.com.shujiaapplication.ui;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CommentsData extends Data implements Parcelable {
    @SerializedName("userid")
    private String userid;
    @SerializedName("token")
    private String token;
    @SerializedName("houseid")
    private String houseid;
    @SerializedName("comment")
    private String comment;

    protected CommentsData(Parcel in) {
        userid = in.readString();
        token = in.readString();
        houseid = in.readString();
        comment = in.readString();
    }

    public static final Creator<CommentsData> CREATOR = new Creator<CommentsData>() {
        @Override
        public CommentsData createFromParcel(Parcel in) {
            return new CommentsData(in);
        }

        @Override
        public CommentsData[] newArray(int size) {
            return new CommentsData[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userid);
        parcel.writeString(token);
        parcel.writeString(houseid);
        parcel.writeString(comment);
    }
}
