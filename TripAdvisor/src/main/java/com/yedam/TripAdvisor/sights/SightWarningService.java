package com.yedam.TripAdvisor.sights;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonParser;

public class SightWarningService {
	private static SightWarningInfo WarningInfo = new SightWarningInfo();

	public static List<SightWarningInfo> getWarningInfo(String country) {
		List<PropertiesPair> params = new ArrayList<PropertiesPair>();
		String ServiceKeyDec = "OsS4TI2vOl9FfVJNHzHP0a5EnxoAQetcKbWtiOSBxcw8Povf0Ej+bGM+hJLem0IOwDfaqn4YFv19IM8VyeR5xw==";
		String serviceURL = "http://apis.data.go.kr/1262000/TravelAlarmService2/getTravelAlarmList2?";

		params.add(new PropertiesPair("ServiceKey", ServiceKeyDec));
		params.add(new PropertiesPair("returnType", "JSON"));
		params.add(new PropertiesPair("numOfRows", "10"));
		params.add(new PropertiesPair("cond[country_nm::EQ]", country));
		params.add(new PropertiesPair("pageNo", "1"));

		StringBuilder sb = new StringBuilder();

		try {
			String paramURL = PropertiesPair.getQuery(params);
			String requestURL = serviceURL + paramURL;
			URL url = new URL(requestURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				br.close();
			} else {
				System.out.println(con.getResponseMessage());
			}
			con.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String result = sb.toString();
		List<SightWarningInfo> warning = new ArrayList<SightWarningInfo>();
		warning = getWarningData(result);
		warning.toString();

		return null;

	}

	private static List<SightWarningInfo> getWarningData(String result) {
		List<SightWarningInfo> list = new ArrayList<SightWarningInfo>();
		JSONParser parser = new JSONParser();
		try {
			JSONObject warningDataResult = (JSONObject) parser.parse(result);
			JSONArray data = (JSONArray) warningDataResult.get("data");
			SightWarningInfo info = new SightWarningInfo();
			info.setCurrentCount((long) warningDataResult.get("currentCount"));
			if (info.getCurrentCount() == 1) {
				for (int i = 0; i < data.size(); i++) {
					JSONObject warningData = (JSONObject) data.get(i);
					//isnull
					if(warningData.get("alarm_lvl") == null) {
						break;
					}else {
						info.setAlarm_lvl(Integer.parseInt(warningData.get("alarm_lvl").toString()));
						info.setRegion_ty(warningData.get("region_ty").toString());
						info.setWritten_dt(warningData.get("written_dt").toString());
						list.add(info);
						
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();

		}

		return list;
	}

}
