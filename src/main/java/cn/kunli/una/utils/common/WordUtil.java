package cn.kunli.una.utils.common;
//
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import freemarker.template.TemplateException;
//import freemarker.template.Version;
//import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordUtil {

//
//
//
//    public static void main(String[] args) throws IOException, TemplateException {
//        List<HashMap<String,String>> objs = new ArrayList<>();
//        HashMap<String,String> obj = new HashMap<>();
//        obj.put("name","12");
//        obj.put("sex","12");
//        obj.put("age","12");
//        obj.put("birthday","12");
//        obj.put("remark","12");
//        objs.add(obj);
//
//        HashMap<String,Object> param = new HashMap<>();
//        param.put("dataList",objs);
//
//        String url = createCerti(null,"D:\\template","D:/template/result/测试文件.doc","demo.ftl",param);
//        PdfUtils.doc2pdf(url,"D:\\我是结果3.pdf");
//    }
//    /**
//     * 生成证书
//     * 支持图片替换、内容文字替换、集合列表替换
//     * @param response  不为空时输出到页面
//     * @param folder 证书模板所在文件夹
//     * @param outFileUrl  输出文件路径
//     * @param fileName 模板名称
//     * @param param 需要替换的参数
//     * @return
//     */
//    public static String createCerti(HttpServletResponse response, String folder,
//                                     String outFileUrl, String fileName, Map<String,Object> param) throws IOException {
//        //生成文件路径
//        String url = "";
//        Writer out = null;
//        try {
//            //Configuration 用于读取ftl文件
//            Configuration configuration = new Configuration(new Version("2.3.0"));
//            configuration.setDefaultEncoding("utf-8");
//
//            /**
//             * 以下是两种指定ftl文件所在目录路径的方式，注意这两种方式都是
//             * 指定ftl文件所在目录的路径，而不是ftl文件的路径
//             */
//            //指定路径的第一种方式（根据某个类的相对路径指定）
////                configuration.setClassForTemplateLoading(this.getClass(), "");
//
//            //指定路径的第二种方式，我的路径是C：/a.ftl
//            configuration.setDirectoryForTemplateLoading(new File(folder));
//
//            //输出文档路径及名称
//            File outFile = new File(outFileUrl);
//
//            //以utf-8的编码读取ftl文件
//            Template template = configuration.getTemplate(fileName, "utf-8");
//
//            //输出页面
//            if(response != null){
//                response.setContentType("application/msexcel;charset=UTF-8");
//                fileName = URLEncoder.encode(fileName,"UTF-8");
//                response.setHeader("Content-disposition", "attachment; filename="+fileName);
//                out = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"), 10240);
//            }else {
//                //输出文件
//                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"), 10240);
//                url = outFileUrl;
//            }
//            template.process(param, out);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            out.close();
//        }
//
//        return url;
//    }
//
//
//    /**
//     * 获取照片编码
//     * @param imgUrl
//     * @return
//     */
//    public static String getImageStr(String imgUrl) {
//        InputStream in = null;
//        byte[] data = null;
//        try {
//            in = new FileInputStream(imgUrl);
//            data = new byte[in.available()];
//            in.read(data);
//            in.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        BASE64Encoder encoder = new BASE64Encoder();
//        return encoder.encode(data);
//    }
//
//


}
