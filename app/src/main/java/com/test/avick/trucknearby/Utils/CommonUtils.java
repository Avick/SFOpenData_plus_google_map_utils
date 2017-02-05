package com.test.avick.trucknearby.Utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by avickbiswas on 02/02/17.
 */

public class CommonUtils {

    public static String getJsonFromRaw(Context mContext, int id) {
        InputStream is = mContext.getResources().openRawResource(id);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }

            is.close();
        } catch (IOException e) {
            e.printStackTrace();

        }

        String jsonString = writer.toString();

        return jsonString;

    }
}


//    String jsonString = OrderDetailsFragment.getJsonFromRaw(getActivity(), R.raw.return_item_details_sample);
//    Gson gson = new Gson();
//    data = gson.fromJson(jsonString, ReturnItemVO.class);

//    Type listType = new TypeToken<List<String>>() {}.getType();
//    List<String> yourList = new Gson().fromJson(mapping.get("servers"), listType);