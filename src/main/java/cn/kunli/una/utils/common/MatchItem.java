package cn.kunli.una.utils.common;
/**
 * 用来保存关键字信息
 */
public class MatchItem {

    //页数
    private Integer pageNum;
    //x坐标
    private Float x;
    //y坐标
    private Float y;
    //页宽
    private Float pageWidth;
    //页高
    private Float pageHeight;
    //匹配字符
    private String content;
    //字体宽
    private float fontWidth;
    //字体高
    private float fontHeight = 12;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getPageWidth() {
        return pageWidth;
    }

    public void setPageWidth(Float pageWidth) {
        this.pageWidth = pageWidth;
    }

    public Float getPageHeight() {
        return pageHeight;
    }

    public void setPageHeight(Float pageHeight) {
        this.pageHeight = pageHeight;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getFontWidth() {
        return fontWidth;
    }

    public void setFontWidth(float fontWidth) {
        this.fontWidth = fontWidth;
    }

    public float getFontHeight() {
        return fontHeight;
    }

    public void setFontHeight(float fontHeight) {
        this.fontHeight = fontHeight;
    }

    @Override
    public String toString() {
        return "MatchItem{" +
                "pageNum=" + pageNum +
                ", x=" + x +
                ", y=" + y +
                ", pageWidth=" + pageWidth +
                ", pageHeight=" + pageHeight +
                ", content='" + content + '\'' +
                '}';
    }
}
