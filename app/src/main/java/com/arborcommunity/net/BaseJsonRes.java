package com.arborcommunity.net;

import com.arborcommunity.App;
import com.arborcommunity.Constants;
import com.arborcommunity.common.Utils;
import com.juns.health.net.loopj.android.http.JsonHttpResponseHandler;


import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseJsonRes extends JsonHttpResponseHandler {

	@Override
	public void onSuccess(JSONObject response) {
		try {
			String result = response.getString(Constants.Result);
			// System.out.println("返回的值" + response);
			if (result == null) {
				Utils.showLongToast(App.getInstance(), Constants.NET_ERROR);
			} else if (result.equals("success")) {
				String str_data = response.getString(Constants.Value);
				onMySuccess(str_data);
			} else if (result.equals("error")){
				String str = response.getString(Constants.Value);
				//Utils.showLongToast(App.getInstance(), str);
				onMyFailure(str);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			onMyFailure("error");
		}
	}

	public abstract void onMySuccess(String data);

	public abstract void onMyFailure(String data);

}
