package cn.kunli.una.pojo.vo;


import org.springframework.stereotype.Component;

@Component
public class Constant {


    public static final String COMPANY_NAME = "机关";//特殊单位名称

    public static final int YES = 1;//是

    public static final int NO = 0; //否

    public static final String INPUT = "1";//入库


    public static final String OUT = "0"; //出库
    //高德地图地址围栏 请求url
    public static final String GEOFENCE_BASE_URL = "https://restapi.amap.com/v4/geofence/meta?key=";
    /**
     * 名    称：围栏设备监控
     * 请求方式：GET
     * 示    例：https://restapi.amap.com/v4/geofence/status?key=用户key&diu=设备imei&locations=116.472407,39.993322,1484816232
     * 返回结果：
     * {
     * "data": {//返回数据内容消息体
     * "fencing_event_list": [{//围栏事件列表
     * "client_action": "enter",//设备行为。对应3种与围栏的动态交互关系，进/出/停留： enter|leave|stay
     * "client_status": "in",//设备状态。当前与围栏的静态关系状态。是否在围栏里： in|out
     * "enter_time": "2017-01-19 16:57:12",//用户进入围栏时间。格式： yyyy-MM-dd HH:mm:ss
     * "fence_info": {//围栏信息
     * "fence_center": "116.47253,39.992912",
     * "fence_gid": "31c94518-4145-4a94-9e7d-34cb027b4c96",//全局围栏id
     * "fence_name": "测试大围栏"//围栏名称
     * }
     * }],
     * "status": 0//返回状态码
     * },
     * "errcode": 0,
     * "errmsg": "OK"
     * }
     */

    /**
     * 高德地图逆地址解析基础url
     */
    public static final String GEOCODE_REGEO_URL = "https://restapi.amap.com/v3/geocode/regeo?key=";

    public static final String FENCING_MONITOR = "https://restapi.amap.com/v4/geofence/status?key=";
    //摄像头接口地址
    public static String CAMERA_URL = "";
    public static String ELOGIN_URL = "";//摄像头登录接口
    //消息推送接口地址
    public static String PUSH_MSG_URL = "";
    //文件上传基础地址
    public static String UPLOAD_FILE_PATH = "";
    //人脸识别云平台基础地址
    public static String FACE_DISCERN_URL = "";
    public static String FACE_DISCERN_TOKEN = "";
    public static String EQUIPMENT_APPLY = "";//装备申领
    public static String MAINTENANCE_APPLY = "";//维修申请
    public static String TOKEN = "";
    public static String INVENTORY_KEEPER_ID = "";//库管id

    public static String EQUIPMENT_TECHNICIAN_ID = "";//装备技师id

    /**
     * 用户人脸识别图片信息 redis缓存 key
     */
    public static String USER_FACE_IMG_CACHE_KEY = "user_face_img";
    public static String USER_FACE_IMG_BASE64_CACHE_KEY = "user_face_img_base64";


    public static String VEHICLE_STATUS = "461ecc23cd8c44c0b2cccdda0b48e587,77e8c98e26fc4e9d82bb14c84ec02c09,d639738d3b8c4c449ee31fd85262abe3";//合并的车辆状态id

    public static String VEHICLE_STATE_MAINTENANCE = "f40e21a581574618a0e9cc0a6ccbbf2a";//车辆报停

    public static String VEHICLE_STATE_RESTRICTION = "461ecc23cd8c44c0b2cccdda0b48e587,77e8c98e26fc4e9d82bb14c84ec02c09,d639738d3b8c4c449ee31fd85262abe3,994573e824df4e2bbe10645251860689";//车辆限行

    public static String SERVICE_PORT = "";




}
