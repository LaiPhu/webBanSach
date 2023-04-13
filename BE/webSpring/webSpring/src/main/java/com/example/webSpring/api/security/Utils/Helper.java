/*
 * Copyright (c) 2021. Pitagon., JSC. All rights reserved.
 */

package com.example.webSpring.api.security.Utils;

import com.example.webSpring.api.core.exception.BusinessException;
import com.example.webSpring.api.core.exception.ExceptionType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Helper {

    public static final int OTP_LENGTH = 4;
    public static final String[] CLIENT_IP_HEADERS = {
        "X-Forwarded-For",
        "Proxy-Client-IP",
        "WL-Proxy-Client-IP",
        "HTTP_X_FORWARDED_FOR",
        "HTTP_X_FORWARDED",
        "HTTP_X_CLUSTER_CLIENT_IP",
        "HTTP_CLIENT_IP",
        "HTTP_FORWARDED_FOR",
        "HTTP_FORWARDED",
        "HTTP_VIA",
        "REMOTE_ADDR",
    };
    private static final Gson gson = new GsonBuilder().serializeNulls().create();
    private static final JsonParser jsonParser = new JsonParser();
    private static final Map<String, String[]> MAP_SIGNED_CHAR = new HashMap<>();
    private static Set<String> KEY_SET_MAP_SIGNED_CHAR;

    static {
        MAP_SIGNED_CHAR.put("a", new String[]{"à", "á", "ạ", "ả", "ã", "â", "ầ", "ấ", "ậ", "ẩ", "ẫ", "ă", "ằ", "ắ", "ặ", "ẳ", "ẵ"});
        MAP_SIGNED_CHAR.put("e", new String[]{"è", "é", "ẹ", "ẻ", "ẽ", "ê", "ề", "ế", "ệ", "ể", "ễ"});
        MAP_SIGNED_CHAR.put("i", new String[]{"ì", "í", "ị", "ỉ", "ĩ"});
        MAP_SIGNED_CHAR.put("o", new String[]{"ò", "ó", "ọ", "ỏ", "õ", "ô", "ồ", "ố", "ộ", "ổ", "ỗ", "ơ", "ờ", "ớ", "ợ", "ở", "ỡ"});
        MAP_SIGNED_CHAR.put("u", new String[]{"ù", "ú", "ụ", "ủ", "ũ", "ư", "ừ", "ứ", "ự", "ử", "ữ"});
        MAP_SIGNED_CHAR.put("y", new String[]{"ỳ", "ý", "ỵ", "ỷ", "ỹ"});
        MAP_SIGNED_CHAR.put("d", new String[]{"đ"});
        MAP_SIGNED_CHAR.put("A", new String[]{"À", "Á", "Ạ", "Ả", "Ã", "Â", "Ầ", "Ấ", "Ậ", "Ẩ", "Ẫ", "Ă", "Ằ", "Ắ", "Ặ", "Ẳ", "Ẵ"});
        MAP_SIGNED_CHAR.put("E", new String[]{"È", "É", "Ẹ", "Ẻ", "Ẽ", "Ê", "Ề", "Ế", "Ệ", "Ể", "Ễ"});
        MAP_SIGNED_CHAR.put("I", new String[]{"Ì", "Í", "Ị", "Ỉ", "Ĩ"});
        MAP_SIGNED_CHAR.put("O", new String[]{"Ò", "Ó", "Ọ", "Ỏ", "Õ", "Ô", "Ồ", "Ố", "Ộ", "Ổ", "Ỗ", "Ơ", "Ờ", "Ớ", "Ợ", "Ở", "Ỡ"});
        MAP_SIGNED_CHAR.put("U", new String[]{"Ù", "Ú", "Ụ", "Ủ", "Ũ", "Ư", "Ừ", "Ứ", "Ự", "Ử", "Ữ"});
        MAP_SIGNED_CHAR.put("Y", new String[]{"Ỳ", "Ý", "Ỵ", "Ỷ", "Ỹ"});
        MAP_SIGNED_CHAR.put("D", new String[]{"Đ"});
        KEY_SET_MAP_SIGNED_CHAR = MAP_SIGNED_CHAR.keySet();
    }

    public static String getMapSignedCharKey(char c) {
        String[] values;
        for (String key : KEY_SET_MAP_SIGNED_CHAR) {
            values = MAP_SIGNED_CHAR.get(key);
            for (String value : values) {
                if (value.equals(c)) return key;
            }
        }
        return null;
    }

    public static String toUnsignedChar(String s) {
        if (s == null) return null;
        if (s.length() == 0) return "";
        String[] values;
        for (String key : KEY_SET_MAP_SIGNED_CHAR) {
            values = MAP_SIGNED_CHAR.get(key);
            for (String value : values) {
                s = s.replace(value, key);
            }
        }
        return s;
    }

    public static byte[] toByteArray(Object o) {
        if (o == null) return null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(o);
            byte[] buf = baos.toByteArray();
            return buf;
        } catch (Exception e) {
            log.error(null, e);
            return null;
        }
    }

    public static Object toObject(byte[] b) {
        if (b == null) {
            return null;
        }
        try {
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(b));
            Object object = ois.readObject();
            ois.close();
            return object;
        } catch (Exception e) {
            log.error(null, e);
            return null;
        }
    }

    public static boolean isEmail(String s) {
        if (StringUtils.isBlank(s)) {
            return false;
        }
        try {
            Pattern pattern = Pattern.compile("(^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$)");
            Matcher matcher = pattern.matcher(s.trim());
            if (matcher.find()) {
                return true;
            }
        } catch (Exception e) {
            log.error(null, e);
        }
        return false;
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            boolean isValid = false;
            String expression = "([+]?[-]?[0-9\\-]?){9,11}[0-9]$";
            Pattern pattern = Pattern.compile(expression);
            Matcher matcher = pattern.matcher(phoneNumber);
            if (phoneNumber.length() >= 9 && phoneNumber.length() <= 11) {
                if (matcher.matches()) {
                    isValid = true;
                }
            }
            return isValid;
        }
        return false;
    }

    /**
     * Generate a CRON expression is a string comprising 6 or 7 fields separated by white space.
     *
     * @param seconds    mandatory = yes. allowed values = {@code  0-59    * / , -}
     * @param minutes    mandatory = yes. allowed values = {@code  0-59    * / , -}
     * @param hours      mandatory = yes. allowed values = {@code 0-23   * / , -}
     * @param dayOfMonth mandatory = yes. allowed values = {@code 1-31  * / , - ? L W}
     * @param month      mandatory = yes. allowed values = {@code 1-12 or JAN-DEC    * / , -}
     * @param dayOfWeek  mandatory = yes. allowed values = {@code 0-6 or SUN-SAT * / , - ? L #}
     * @param year       mandatory = no. allowed values = {@code 1970–2099    * / , -}
     * @return a CRON Formatted String.
     */
    public static String generateCronExpression(
        String seconds,
        String minutes,
        String hours,
        String dayOfMonth,
        String month,
        String dayOfWeek,
        String year
    ) {
        return String.format("%1$s %2$s %3$s %4$s %5$s %6$s %7$s", seconds, minutes, hours, dayOfMonth, month, dayOfWeek, year);
    }

    public static String formatPhone(String phone) {
        if (phone.charAt(0) == '0') {
            phone = phone.substring(1);
        }
        return phone;
    }

    public static String generateRandomNumber(int length) {
        return RandomStringUtils.randomNumeric(length);
    }

    public static String generateOtp() {
        return generateRandomNumber(OTP_LENGTH);
    }

    public static String getClientIpAddress(HttpServletRequest request) {
        for (String header : CLIENT_IP_HEADERS) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

    public static String formatSortOrder(String val, Integer lengthSortOrder) {
        int length = lengthSortOrder - val.length();
        if (length < 0) {
            throw new BusinessException(ExceptionType.SORT_ORDER_MAX);
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < length; i++) {
            res.append("0");
        }
        return res + val;
    }

    public static String replacePath(String pathChild, String pathParent, String value) {
        String[] temp = pathParent.split("/");
        temp[temp.length - 1] = value;
        if (value != null && !value.equals("")) {
            temp[temp.length - 1] = value;
            return String.join("/", temp) + "/" + pathChild.replace(pathParent, "");
        }
        return "/" + pathChild.replace(pathParent, "");
    }

    public static String toJsonString(Map<String, String> map) {
        String json = "";
        if (map == null) return json;
        try {
            json = new ObjectMapper().writeValueAsString(map);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return json;
    }

    public static Map<String, String> toMap(String json) {
        Map<String, String> map = new HashMap<String, String>();
        if (json == null) return map;
        try {
            map = new ObjectMapper().readValue(json, new TypeReference<HashMap<String, String>>() {
            });
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return map;
    }

    public static String toJsonString(Object obj) {
        String json = "";
        if (obj == null) return json;
        return gson.toJson(obj);
    }

    public static String toJsonString(List<Object> objs) {
        String json = "";
        if (objs == null) return json;
        return gson.toJson(objs);
    }

    public static <T> T fromJSON(Class<T> c, String json) {
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.setLenient(true);
        return gson.fromJson(reader, c);
    }

    public static JsonObject fromJSON(String json) {
        return jsonParser.parse(json).getAsJsonObject();
    }
}
