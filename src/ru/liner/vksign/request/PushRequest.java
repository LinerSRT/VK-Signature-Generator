package ru.liner.vksign.request;

import java.util.Locale;

public class PushRequest extends BaseRequest {
    public PushRequest() {
        super("pushstatus");
    }

    @Override
    public ApiRequestParams getMethodParams() {
        ApiRequestParams apiRequestParams = new ApiRequestParams();
        apiRequestParams.put("application", "VK");
        apiRequestParams.put("application_id", "*");
        apiRequestParams.put("capabilities", "*");
        apiRequestParams.put("confirm_action", "*");
        apiRequestParams.put("delivery_method", "*");
        apiRequestParams.put("device_id", "*");
        apiRequestParams.put("device_screen_active", "*");
        apiRequestParams.put("env", "*");
        apiRequestParams.put("os_version", "*");
        apiRequestParams.put("platform", "*");
        apiRequestParams.put("push_token_id", "*");
        apiRequestParams.put("route_type", "*");
        apiRequestParams.put("session_id", "*");
        apiRequestParams.put("status", "*");
        apiRequestParams.put("system_id", "*");
        apiRequestParams.put("version", "*");
        apiRequestParams.put("libverify_build", "231");
        apiRequestParams.put("libverify_version", "2.0");
        return apiRequestParams;
    }

    @Override
    public String getApiHost() {
        return "https://clientapi.mail.ru/".split(";")[0];
    }

    @Override
    public String getApiPath() {
        return String.format(Locale.US, "%s/%s", "fcgi-bin", method);
    }
}
