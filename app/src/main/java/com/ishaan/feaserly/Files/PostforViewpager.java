package com.ishaan.feaserly.Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostforViewpager {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("data")
    @Expose
    private Data data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    public class Data {

        @SerializedName("accessToken")
        @Expose
        private String accessToken;
        @SerializedName("isSocialLogin")
        @Expose
        private Boolean isSocialLogin;
        @SerializedName("placeApiKey")
        @Expose
        private String placeApiKey;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public Boolean getIsSocialLogin() {
            return isSocialLogin;
        }

        public void setIsSocialLogin(Boolean isSocialLogin) {
            this.isSocialLogin = isSocialLogin;
        }

        public String getPlaceApiKey() {
            return placeApiKey;
        }

        public void setPlaceApiKey(String placeApiKey) {
            this.placeApiKey = placeApiKey;
        }

    }
}
