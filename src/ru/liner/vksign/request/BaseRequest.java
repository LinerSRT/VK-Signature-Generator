package ru.liner.vksign.request;


import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;

public abstract class BaseRequest {
    protected static final Charset defaultCharset = StandardCharsets.UTF_8;
    protected static final Locale defaultLocale = Locale.US;
    protected static final String applicationKey = "Pnxo7xcRjuXSldIh"; // Collected from official VK android app at res/values/strings.xml -> libverify_application_key
    protected String method;

    public BaseRequest(String method) {
        this.method = method;
    }

    public final String buildRequestUrl() {
        ApiRequestParams methodParams = getMethodParams();
        if (methodParams.isEmpty())
            return String.format(defaultLocale, "%s%s", getApiHost(), getApiPath());
        StringBuilder stringBuilder = new StringBuilder(methodParams.getRequestLength());
        for (Map.Entry<String, String> entry : methodParams.entrySet()) {
            if (!isValid(entry.getValue()))
                throw new IllegalArgumentException("Request arguments can't be empty!");
            if (!isValid(stringBuilder))
                stringBuilder.append("&");
            stringBuilder.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), defaultCharset));
        }
        return String.format(defaultLocale, "%s%s?%s&signature=%s", getApiHost(), getApiPath(), stringBuilder, getSignature());
    }

    public final String getSignature(ApiRequestParams apiRequestParams) {
        TreeSet<String> treeSet = new TreeSet<>();
        StringBuilder stringBuilder = new StringBuilder(apiRequestParams.getRequestLength());
        for (Map.Entry<String, String> entry : apiRequestParams.entrySet())
            treeSet.add(entry.getKey() + URLEncoder.encode(entry.getValue(), defaultCharset));
        for (String set : treeSet) {
            stringBuilder.append(set);
        }
        return URLEncoder.encode(toMD5(method + stringBuilder + String.format("%x", new BigInteger(1, applicationKey.getBytes(defaultCharset)))), defaultCharset);
    }

    public final String getSignature(){
        return getSignature(getMethodParams());
    }

    public abstract ApiRequestParams getMethodParams();

    public abstract String getApiHost();

    public abstract String getApiPath();

    private String toMD5(String value) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(value.getBytes(defaultCharset));
            return toHex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return value;
    }

    private String toHex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder(bytes.length * 2);
        for(byte b: bytes)
            stringBuilder.append(String.format("%02x", b));
        return stringBuilder.toString();
    }

    private boolean isValid(String value){
        return value != null && value.length() > 0;
    }

    private boolean isValid(StringBuilder stringBuilder){
        return isValid(stringBuilder.toString());
    }
}
