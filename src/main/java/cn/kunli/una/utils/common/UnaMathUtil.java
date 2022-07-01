package cn.kunli.una.utils.common;

/**
 * @author Ponzio
 * @version 2022年7月1日15:51:16
 * 数学工具类
 */
public class UnaMathUtil {

    //方差s^2=[(x1-x)^2 +...(xn-x)^2]/n 或者s^2=[(x1-x)^2 +...(xn-x)^2]/(n-1)
    public static double Variance(Double[] x) {
        int m=x.length;
        double sum=0;
        for(int i=0;i<m;i++){//求和
            sum+=x[i];
        }
        double dAve=sum/m;//求平均值
        double dVar=0;
        for(int i=0;i<m;i++){//求方差
            dVar+=(x[i]-dAve)*(x[i]-dAve);
        }
        return dVar/m;
    }

    //标准差σ=sqrt(s^2)
    public static double StandardDiviation(Double[] x) {
        int m=x.length;
        double sum=0;
        for(int i=0;i<m;i++){//求和
            sum+=x[i];
        }
        double dAve=sum/m;//求平均值
        double dVar=0;
        for(int i=0;i<m;i++){//求方差
            dVar+=(x[i]-dAve)*(x[i]-dAve);
        }
        //reture Math.sqrt(dVar/(m-1));
        return Math.sqrt(dVar/m);
    }


}
