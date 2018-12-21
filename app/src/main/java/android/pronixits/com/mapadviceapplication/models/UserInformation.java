package android.pronixits.com.mapadviceapplication.models;

public class UserInformation {

    private String userid;
    private String username;
    private String email;
    private String imageurl;

    public UserInformation() {
    }

    public UserInformation(String userid, String username, String email, String imageurl) {
        this.userid = userid;
        this.username = username;
        this.email = email;
        this.imageurl = imageurl;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
