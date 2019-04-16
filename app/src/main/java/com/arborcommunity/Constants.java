package com.arborcommunity;

public interface Constants {

	// 聊天
	public static final String NEW_FRIENDS_USERNAME = "item_new_friends";
	String isFriend = "isFriend";
	String LoginState = "LoginState";
	String UserInfo = "UserInfo";
	String URL = "URL";
	String Title = "Title";
	String ID = "id";
	String NAME = "NAME";
	String AccessToken = "AccessToken";
	String PWD = "PWD";
	String ContactMsg = "ContactMsg";
	String VersionInfo = "VersionInfo";
	String SeeMe = "SeeMe";
	String LokeMe = "LokeMe";
	String IsMsg = "IsMsg";
	String IsVideo = "IsVideo";
	String IsZhen = "IsZhen";
	String User_ID = "User_ID";
	String GROUP_ID = "GROUP_ID";
	String TYPE = "TYPE";
	// JSON status
	String Info = "info";
	String Value = "data";
	String Result = "status";
	String DB_NAME = "community.db";
	String NET_ERROR = "网络错误，请稍后再试！";
	String BaiduPullKey = "Uvw5AMP15i9v1cUoS5aY7GR1";


	String Commmunity_Name = "";


	// 主机地址
	// public static String IP = "http://wechatjuns.sinaapp.com/";
	// String MAIN_ENGINE = "http://10.16.16.79/wechat/index.php/mobile/";
	//String MAIN_ENGINE = "http://wechatjuns.sinaapp.com/index.php/admin";

	//String MAIN_ENGINE = "http://103.26.76.116:8080/admin/App/";
	String MAIN_ENGINE = "http://192.168.2.104:8080/admin/App/";
	// 发送验证码 codeType 1注册 2修改密码
	String SendCodeURL = "";
	// 用户注册
	String RegistURL = MAIN_ENGINE + "user/regist";
	// 用户登录
	String Login_URL = MAIN_ENGINE + "user/login";
	// 更新用户信息
	String UpdateInfoURL = MAIN_ENGINE + "user/update_userinfo";
	// 获取用户信息
	String getUserInfoURL = MAIN_ENGINE + "user/get_user_list";

	//获取社区
	String getCommunityURL = MAIN_ENGINE +"communityMessage/list";

	//获取城市地区
	String getAreaURL = MAIN_ENGINE+"communityMessage/getCity";

	//上传文件、图片
	String upFile = MAIN_ENGINE+"sysFile/upfile";
	//发布信息
	String pushInfoByType =MAIN_ENGINE+ "/pushInfoByType";
	//验证用户是否已经通过身份信息验证
	String verify_Identity_Info = MAIN_ENGINE+ "/verifyIdentityInfo";
	//获取省份数据
	String getProvince = MAIN_ENGINE+"/provinceCity/getPrivateDate";
	//根据省份获取城市数据
	String getCityByProvince = MAIN_ENGINE +"/provinceCity/getCityDateByProvinceName";

	//增加社区信息
	String addCommunityInfo = MAIN_ENGINE +"/communityMessage/addCommunityInfo";

	//查询该社区管理员是否已经达到上限
	String findCommunityAdministrator = MAIN_ENGINE +"/communityMessage/findCommunityAdministrator";

	String addCommunityMember =  MAIN_ENGINE +"/communityMessage/addCommunityMember";
}
