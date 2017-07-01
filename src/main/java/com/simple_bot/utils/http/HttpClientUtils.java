package com.simple_bot.utils.http;

import com.simple_bot.dto.BaseResponse;
import org.apache.http.NameValuePair;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * @author Иван
 * @version $Id$
 */
public class HttpClientUtils {
    public static void doPost(String url, List<NameValuePair> params) {

    }

    public static BaseResponse doGet(String url) {
        return new BaseResponse(EMPTY, 200);
    }
}
