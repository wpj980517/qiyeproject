package itcast.domain;

import java.util.List;

public class UserInfo {
    private String id;
    private String email;
    private String username;
    private String PASSWORD;
    private String phoneNum;
    private Integer STATUS;
    private String statusStr;
    private List<Role> roles;


    public UserInfo() {
    }

    public String getStatusStr() {
        if(STATUS==0)
            return "关闭";
        return "开启";
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(Integer STATUS) {
        this.STATUS = STATUS;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", PASSWORD='" + PASSWORD + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", STATUS=" + STATUS +
                ", statusStr='" + statusStr + '\'' +
                ", roles=" + roles +
                '}';
    }
}
