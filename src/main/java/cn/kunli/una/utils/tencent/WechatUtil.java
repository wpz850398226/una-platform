package cn.kunli.una.utils.tencent;

import cn.kunli.una.pojo.system.SysConfiguration;
import cn.kunli.una.service.system.SysConfigurationService;
import cn.kunli.una.utils.common.HttpUtil;
import cn.kunli.una.utils.common.ListUtil;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.DateUtil;
import cn.kunli.una.utils.service.SpringContextUtil;
import com.alibaba.fastjson.JSON;
import cn.hutool.core.util.StrUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 作者 : Ponzio
 * @version 创建时间：2020年3月11日15:59:21
 * 类说明 :腾讯微信工具类
 */
public class WechatUtil {

	private static SysConfigurationService sysConfigurationService = (SysConfigurationService) SpringContextUtil.getBean("sysConfigurationService");


	/**
	 * 获取微信openid
	 * @param code	页面获取code（动态）
	 * @param appid	微信公众号appid（系统配置获取）
	 * @param secret	微信公众阿訇密钥（系统配置获取）
	 * @return
	 */
	/*public static String getOpenid(String code, String appid, String secret){
		String result = HttpUtil.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token","appid="+appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code");
		Map<String,Object> map = (Map<String, Object>) JSON.parse(result);
		String openid=(String) map.get("openid");
		return openid;
	}*/

	/**
	 * 获取微信access_token
	 * @param appid	微信公众号appid（系统配置获取）
	 * @param secret	微信公众阿訇密钥（系统配置获取）
	 * @return
	 */
	public static String getAccessToken(String appid, String secret){
//		String result = HttpUtil.sendGet("https://api.weixin.qq.com/cgi-bin/token","grant_type=client_credential&appid="+appid+"&secret="+secret);
		String result = "";
		Map<String,Object> map = (Map<String, Object>) JSON.parse(result);
		String errmsg=(String) map.get("errmsg");
		String access_token=(String) map.get("access_token");
		if (StrUtil.isNotBlank(errmsg)){
			return "error";
		}else {
			return access_token;
		}
	}

	/**
	 * 获取微信appid
	 * @return
	 */
	public static String getAppId(){
		List<SysConfiguration> appidConfigurationList = sysConfigurationService.selectList(MapUtil.getMap("code","wechatAppid"));
		if (ListUtil.isNotNull(appidConfigurationList)){
			return appidConfigurationList.get(0).getValue();
		}else {
			return null;
		}
	}

	/**
	 * 获取微信密钥
	 * @return
	 */
	public static String getSecret(){
		List<SysConfiguration> secretConfigurationList = sysConfigurationService.selectList(MapUtil.getMap("code","wechatSecret"));
		if (ListUtil.isNotNull(secretConfigurationList)){
			return secretConfigurationList.get(0).getValue();
		}else {
			return null;
		}
	}



	public static String sendWechatMsg (String openid,String name,boolean status) throws Exception {
		//审核模板ID
		String modelID="IhAmGOIgVTT3t5wk9R3DOVWeb6nS_CrCRF3pORsse6k";
		//openid
		String OpenID=openid;

		//信息title
		String MsgTitle="";
		//信息审核状态 通过/驳回
		String MsgStatues="";
		//信息备注  您的认证已成功，请查看认证信息/您的认证已失败，请您重新注册
		String MsgRemark="";

		//用户名
		String MsgPerson=name;
		//时间
		String MsgTime= DateUtil.getStrOfTime(new Date());

		if(status){
			//审核通过
			MsgTitle="您好，您的注册审核已通过";
			MsgStatues="通过";
			MsgRemark="您的认证已成功，请查看认证信息";
		}else{
			//审核驳回
			MsgTitle="您好，您的注册审核已驳回";
			MsgStatues="驳回";
			MsgRemark="您的认证已失败，请您重新注册";
		}

		String access_token= WechatUtil.getAccessToken(getAppId(),getSecret());
		if (access_token!=null&&access_token == "error"){
			return "error";
		}else{
			String postData="{\n" +
					"           \"access_token\":\""+access_token+"\",\n" +
					"           \"touser\":\""+OpenID+"\",\n" +
					"           \"template_id\":\""+modelID+"\",\n" +
					"           \"url\":\"\",  \n" +
					"                     \n" +
					"           \"data\":{\n" +
					"                   \"first\": {\n" +
					"                       \"value\":\""+MsgTitle+"\",\n" +
					"                       \"color\":\"#173177\"\n" +
					"                   },\n" +
					"                   \"keyword1\":{\n" +
					"                       \"value\":\""+MsgPerson+"\",\n" +
					"                       \"color\":\"#173177\"\n" +
					"                   },\n" +
					"                   \"keyword2\": {\n" +
					"                       \"value\":\""+MsgStatues+"\",\n" +
					"                       \"color\":\"#173177\"\n" +
					"                   },\n" +
					"                   \"keyword3\": {\n" +
					"                       \"value\":\""+MsgTime+"\",\n" +
					"                       \"color\":\"#173177\"\n" +
					"                   },\n" +
					"                   \"remark\":{\n" +
					"                       \"value\":\""+MsgRemark+"\",\n" +
					"                       \"color\":\"#173177\"\n" +
					"                   }\n" +
					"           }\n" +
					"       }";
//			String openidResult=HttpUtil.sendPostJson("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token,postData);
			String openidResult="";

			Map<String,Object> mapCode = (Map<String, Object>) JSON.parse(openidResult);
			String ResultCode=(String) mapCode.get("errmsg");
			if (ResultCode == "ok")
			{
				return openidResult;
			}else
			{
				return ResultCode;
			}

		}


	}


	public static String sendWechatBacklog (String openid,String name) throws Exception {
		//审核模板ID
		String modelID="5S7U-mXqBJ8u3vRE6BO-H4r9Ckrkv73Pp0WXqvX-Do8";
		//openid
		String OpenID=openid;
		//信息title
		String MsgTitle="注册时间："+DateUtil.getStrOfTime(new Date());
		//信息审核人
		String MsgPerson="系统管理员";
		//信息审核进度
		String MsgStatus="待审核";
		//信息备注
		String MsgRemark="您有新的用户认证待办";

		String access_token= WechatUtil.getAccessToken(getAppId(),getSecret());
		if (access_token!=null&&access_token == "error")
		{
			return "error";
		}else
		{
			String postData="{\n" +
					"           \"access_token\":\""+access_token+"\",\n" +
					"           \"touser\":\""+OpenID+"\",\n" +
					"           \"template_id\":\""+modelID+"\",\n" +
					"           \"url\":\"\",  \n" +
					"                     \n" +
					"           \"data\":{\n" +
					"                   \"first\": {\n" +
					"                       \"value\":\""+MsgTitle+"\",\n" +
					"                       \"color\":\"#173177\"\n" +
					"                   },\n" +
					"                   \"keyword1\":{\n" +
					"                       \"value\":\""+MsgStatus+"\",\n" +
					"                       \"color\":\"#173177\"\n" +
					"                   },\n" +
					"                   \"keyword2\": {\n" +
					"                       \"value\":\""+MsgPerson+"\",\n" +
					"                       \"color\":\"#173177\"\n" +
					"                   },\n" +
					"                   \"remark\":{\n" +
					"                       \"value\":\""+MsgRemark+"\",\n" +
					"                       \"color\":\"#173177\"\n" +
					"                   }\n" +
					"           }\n" +
					"       }";
//			String openidResult=HttpUtil.sendPostJson("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token,postData);
			String openidResult="";

			Map<String,Object> mapCode = (Map<String, Object>) JSON.parse(openidResult);
			String ResultCode=(String) mapCode.get("errmsg");
			if (ResultCode == "ok")
			{
				return openidResult;
			}else
			{
				return ResultCode;
			}

		}


	}



	public static String sendwechatProPlan (String openid,String name) throws Exception {
		//审核模板ID
		String modelID="iCRFuXywAkn8A_chnwUww2Oss3eOd-Uy-1jAwukYlWg";
		//openid
		String OpenID=openid;
		//信息title
		String MsgTitle="项目进度变更";
		//项目名称
		String MsgName=name;
		//项目进度
		String MsgStatus="扦样已完成";
		//信息备注
		String MsgRemark="您有新的待办";

		String access_token= WechatUtil.getAccessToken(getAppId(),getSecret());
		if (access_token!=null && access_token == "error")
		{
			return "error";
		}else
		{
			String postData="{\n" +
					"           \"access_token\":\""+access_token+"\",\n" +
					"           \"touser\":\""+OpenID+"\",\n" +
					"           \"template_id\":\""+modelID+"\",\n" +
					"           \"url\":\"\",  \n" +
					"                     \n" +
					"           \"data\":{\n" +
					"                   \"first\": {\n" +
					"                       \"value\":\""+MsgTitle+"\",\n" +
					"                       \"color\":\"#173177\"\n" +
					"                   },\n" +
					"                   \"keyword1\":{\n" +
					"                       \"value\":\""+MsgName+"\",\n" +
					"                       \"color\":\"#173177\"\n" +
					"                   },\n" +
					"                   \"keyword2\": {\n" +
					"                       \"value\":\""+MsgStatus+"\",\n" +
					"                       \"color\":\"#173177\"\n" +
					"                   },\n" +
					"                   \"remark\":{\n" +
					"                       \"value\":\""+MsgRemark+"\",\n" +
					"                       \"color\":\"#173177\"\n" +
					"                   }\n" +
					"           }\n" +
					"       }";
//			String openidResult=HttpUtil.sendPostJson("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token,postData);
			String openidResult="";

			Map<String,Object> mapCode = (Map<String, Object>) JSON.parse(openidResult);
			String ResultCode=(String) mapCode.get("errmsg");
			if (ResultCode == "ok")
			{
				return openidResult;
			}else
			{
				return ResultCode;
			}

		}


	}


	public static String sendWechatActivitiMsg (String openid
			,String first
			,String keyword1
			,String keyword2
			,String remark) throws Exception {
		//审核模板ID
		String modelID="iCRFuXywAkn8A_chnwUww2Oss3eOd-Uy-1jAwukYlWg";
		//openid
		String OpenID=openid;
		/*//信息title
		String MsgTitle="项目进度变更";
		//项目名称
		String MsgName=name;
		//项目进度
		String MsgStatus="扦样已完成";
		//信息备注
		String MsgRemark="您有新的待办";*/

		String access_token= WechatUtil.getAccessToken(getAppId(),getSecret());
		if (access_token != null && access_token == "error") {
			return access_token;
		} else
		{
			String postData="{\n" +
					"           \"access_token\":\""+access_token+"\",\n" +
					"           \"touser\":\""+OpenID+"\",\n" +
					"           \"template_id\":\""+modelID+"\",\n" +
					"           \"url\":\"\",  \n" +
					"                     \n" +
					"           \"data\":{\n" +
					"                   \"first\": {\n" +
					"                       \"value\":\""+first+"\",\n" +
					"                       \"color\":\"#173177\"\n" +
					"                   },\n" +
					"                   \"keyword1\":{\n" +
					"                       \"value\":\""+keyword1+"\",\n" +
					"                       \"color\":\"#173177\"\n" +
					"                   },\n" +
					"                   \"keyword2\": {\n" +
					"                       \"value\":\""+keyword2+"\",\n" +
					"                       \"color\":\"#173177\"\n" +
					"                   },\n" +
					"                   \"remark\":{\n" +
					"                       \"value\":\""+remark+"\",\n" +
					"                       \"color\":\"#173177\"\n" +
					"                   }\n" +
					"           }\n" +
					"       }";
//			String openidResult=HttpUtil.sendPostJson("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token,postData);
			String openidResult="";

			Map<String,Object> mapCode = (Map<String, Object>) JSON.parse(openidResult);
			String ResultCode=(String) mapCode.get("errmsg");
			if (ResultCode == "ok")
			{
				return openidResult;
			}else
			{
				return ResultCode;
			}

		}


	}




}
