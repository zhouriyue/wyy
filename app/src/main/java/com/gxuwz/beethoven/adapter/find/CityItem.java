package com.gxuwz.beethoven.adapter.find;

import com.gxuwz.beethoven.model.entity.SysUser;

public class CityItem {
    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public static class TrMessage {
        private SysUser sysUser;
        private String content;

        public TrMessage() {
        }

        public SysUser getSysUser() {
            return sysUser;
        }

        public void setSysUser(SysUser sysUser) {
            this.sysUser = sysUser;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
