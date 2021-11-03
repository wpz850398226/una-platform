package cn.kunli.una.utils.common;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;

/**
 * @author lcj
 * @version 1.0
 * @description 生成缩略图和水印非常好用, 具体帮助文档 https://github.com/coobird/thumbnailator/wiki/Maven
 * 缩略图
 * 水印
 * 选择
 * 格式转换
 * @Create 2017-08-03
 */
public class ThumbnailatorUtil {


//    public static void main(String[] args) {
//        //使用给定的图片生成指定大小的图片
//        generateFixedSizeImage();
//
//        //对原图加水印,然后顺时针旋转90度,最后压缩为80%保存
//        //generateRotationWatermark();
//
//        //转换图片格式,将流写入到输出流
//        //generateOutputstream();
//
//        //按比例缩放图片
//        generateScale();
//
//        //生成缩略图到指定的目录
//        //generateThumbnail2Directory();
//
//        //将指定目录下所有图片生成缩略图
//        generateDirectoryThumbnail();
//    }

    /**
     * 使用给定的图片生成指定大小的图片
     */
    public static void generateFixedSizeImage(String srcImg, String purposeImg) {
        try {
//            srcImg = srcImg.replaceAll("/file", "D:/una/fileUpload");
//            int i = srcImg.lastIndexOf(".");
//            String purposeImg = srcImg.replaceAll("fileUpload", "fileUpload/thumbnailfile");
            //创建文件路径
            File purposeFile = new File(purposeImg);
            File fileParent = purposeFile.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            Thumbnails.of(srcImg).size(200, 200).toFile(purposeImg);
        } catch (IOException e) {
            System.out.println("原因: " + e.getMessage());
        }
    }

//    /**
//     * 对原图加水印,然后顺时针旋转90度,最后压缩为80%保存
//     */
//    private static void generateRotationWatermark(){
//        try {
//            Thumbnails.of("data/2016010208.jpg").
//                    size(160,160). // 缩放大小
//                    rotate(90). // 顺时针旋转90度
//                    watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("data/newmeinv.jpg")),0.5f). //水印位于右下角,半透明
//                    outputQuality(0.8). // 图片压缩80%质量
//                    toFile("data/2016010208_new.jpg");
//        } catch (IOException e) {
//            System.out.println("原因: " + e.getMessage());
//        }
//    }
//
//    /**
//     * 转换图片格式,将流写入到输出流
//     */
//    private static void generateOutputstream(){
//        try(OutputStream outputStream = new FileOutputStream("data/2016010208_outputstream.png")) { //自动关闭流
//            Thumbnails.of("data/2016010208.jpg").
//                    size(500,500).
//                    outputFormat("png"). // 转换格式
//                    toOutputStream(outputStream); // 写入输出流
//        } catch (IOException e) {
//            System.out.println("原因: " + e.getMessage());
//        }
//    }
//
//    /**
//     * 按比例缩放图片
//     */
//    private static void generateScale(){
//        try {
//            Thumbnails.of("D:\\faceImg\\2.jpg").
//                    //scalingMode(ScalingMode.BICUBIC).
//                            scale(0.1). // 图片缩放80%, 不能和size()一起使用
//                    outputQuality(0.1). // 图片质量压缩80%
//                    toFile("D:\\faceImg\\2_scale.jpg");
//        } catch (IOException e) {
//            System.out.println("原因: " + e.getMessage());
//        }
//    }
//
//    /**
//     * 生成缩略图到指定的目录
//     */
//    private static void generateThumbnail2Directory(){
//        try {
//            Thumbnails.of("data/2016010208.jpg","data/meinv.jpg").
//                    //scalingMode(ScalingMode.BICUBIC).
//                            scale(0.8). // 图片缩放80%, 不能和size()一起使用
//                    toFiles(new File("data/new/"), Rename.NO_CHANGE);//指定的目录一定要存在,否则报错
//        } catch (IOException e) {
//            System.out.println("原因: " + e.getMessage());
//        }
//    }
//
//    /**
//     * 将指定目录下所有图片生成缩略图
//     */
//    private static void generateDirectoryThumbnail(){
//        try {
//            Thumbnails.of(new File("D:\\faceImg\\faceLog").listFiles()).
//                    //scalingMode(ScalingMode.BICUBIC).
//                            size(50,50).
////                            scale(0.8). // 图片缩放80%, 不能和size()一起使用
//                    toFiles(new File("D:\\faceImg\\faceLog\\new"), Rename.SUFFIX_HYPHEN_THUMBNAIL);//指定的目录一定要存在,否则报错
//        } catch (IOException e) {
//            System.out.println("原因: " + e.getMessage());
//        }
//    }
}
