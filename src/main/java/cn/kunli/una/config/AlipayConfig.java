package cn.kunli.una.config;


import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "alipay")
public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    private String app_id;

    // 商户私钥，您的PKCS8格式RSA2私钥
    private String merchant_private_key;

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    private String alipay_public_key;

    // 签名方式
    private String sign_type;

    // 字符编码格式
    private String charset;

    // 支付宝网关
    public String gatewayUrl;

    // 支付宝网关
    public String log_path;

    @Bean
    public AlipayClient getAlipayClient() {
        return new DefaultAlipayClient(gatewayUrl, app_id, merchant_private_key, "json", charset, alipay_public_key, sign_type);
    }
}
