package com.nvmanh.haivl.data.api;

import com.nvmanh.haivl.utils.StringUtils;

public class ApiFactory {
    public static HaivlApi getApi() {
        return ApiConfig.getRetrofit(StringUtils.BASE_URL).create(HaivlApi.class);
    }
}
