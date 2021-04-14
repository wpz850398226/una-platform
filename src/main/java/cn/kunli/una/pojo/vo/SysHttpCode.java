package cn.kunli.una.pojo.vo;

public class SysHttpCode {
    public static final int CONTINUE = 100;
    //表示出的请求已经被服务器接收，游览器应当继续发送请求的其余部分（HTTP1.1）

    public static final int SWITCHING_POTOTCOLS = 101;
    //服务器将遵从客户的请求转换到另外一种协议（HTTP1.1）。

    public static final int OK = 200;
    //一切正常

    public static final int CREATED = 201;
    //服务器已经创建了文档，location 头给出了他的URL。

    public static final int ACCEPTED = 202;
    //已经接收请求，但是尚未处理完成。

    public static final int NON_AUTHORITATIVE_INFORMATION = 203;
    //文档已经正常的返回，但一些应答头可能不正确，因为使用的是的文档的拷贝(HTTP 1.1新)。

    public static final int NO_CONTENT = 204;
    //没有新文档，游览器应该继续显示原来的文档，这个跟下面的304非常相似。

    public static final int RESET_CONTENT = 205;
    //没有新的内容，到那时游览器应该重置它所显示的内容，用来强制清楚表单输入内容（HTTP1.1 新）

    public static final int PARTIAL_CONTENT = 206;
    //客户发送了一个带有range头的GET请求，服务器完成了它（HTTP1.1  新）。注意 通过Range 可以实现断点续传。

    public static final int MULTIPLE_CHOICES = 300;
    //客户请求的文档可以在多个位置找到，这些位置已经在返回的文档内列出，如果服务器要提出优先选择，则应该在location 应答头指明。

    public static final int MULITIPLE_PERMANENTLY = 301;
    //客户请求的文档在其他地方，新的url在location 头中给出，浏览器应该自动的访问新的URL。

    public static final int FOUND = 302;
    //类似301，但新的URL应该被视为临时性的替代，而不是永久性的，注意，在HTTP1.0中对应的状态信息moved Temporatily。出现该状态码，浏览器能够给自动访问新的URL，因此他是一个很有用的状态代码。
    //注意这个状态代码有时候可以和301替换使用，例如，如果浏览器错误的请求http：// host/~user（缺少了后面的斜杠，有的服务器返回301，有的返回302）。严格的说，我们只能假定原来的请求是GET时浏览器才会自动重定向。

    public static final int SEE_OTHER = 303;
    //类似于301/302，不同之处在于，如果原来的请求是post，location头指定的重定向目标文档应该通过get提取（http 1.1 新）。

    public static final int NOT_MODIFIED = 304;
    //客户端有缓冲的文档并发出了一个条件性的请求（一般是提供if -modified -since 头表示客户端执行比指定日期更新的文档）。服务器告诉客户，原来缓冲的文档还可以继续使用。

    public static final int USE_PROXY = 305;
    //客户请求的文档应该通过location 头所指明的代理服务器提取（HTTP 1.1新）。

    public static final int TEMPORARY_REDIRECT = 307;
    //和302（found）相同，许多浏览器会错误的相应302应该进行重定向，即使原来的请求是post，即使它实际上只在post请求的应答是303时，才能重定向。由于这个原因，HTTP1.1新增了307，以便更加清楚的区分几个状态代码，当出现303应答时，浏览器可以跟随重定向的get和post请求，如是307应答，则浏览器只能跟随对get的请求的重定向。

    public static final int BAD_REQUEST = 400;
    //请求出现语法错误。

    public static final int UNAUTHORIZED = 401;
    //客户试图未经授权访问受密码保护的页面。应答中会包含-WWW-Authenticate头，浏览器据此显示用户名字和密码对话框，然后再填写合适的authorization头后再次发送请求。

    public static final int FORBIDDEN = 403;
    //资源不可用。服务器理解客户的需求，但是拒绝处理他通常由于服务器上文件或目录的权限设置问题。

    public static final int NO_FOUND = 404;
    //无法找到指定位置的资源，也是一个常用的应答。

    public static final int METHOD_NOT_ALLOWED = 405;
    //请求方法（GET、POST、HEAD、Delete、put、trace等）对指定的资源不适用。（HTTP 1.1新）

    public static final int NOT_ACCEPTABLE = 406;
    //指定的资源已经找到，但是mime类型和客户在accpet头中所指定的不兼容（HTTP 1.1新）

    public static final int PROXY_AUTHENTICATION_REQIRED = 407;
    //类似于401 ，表示客户必须先经过代理服务器的授权。（HTTP 1.1新）

    public static final int REQUEST_TIMEOUT = 408;
    //在服务器许可的等待时间内，客户一直没有发出任何请求。客户可以在以后重复同一请求。（HTTP 1.1新）

    public static final int CONFLICT = 409;
    //通常和put 请求有关，由于请求和资源的当前状态相冲突，因此请求不能成功（HTTP 1.1新）

    public static final int GONE = 410;
    //所请求的文档已经不在可用，而且服务器不知道应该重新到哪一个地址，他和404的不同在于，返回407表示文档永久的离开了指定的位置，而404表示由于位置的原因文档不可用。（HTTP 1.1新）

    public static final int LENGTH_REQUIRED = 411;
    //服务器不能处理请求，除非客户发送一个contene-length头（HTTP 1.1新）

    public static final int PRECONFITION_FAILED = 412;
    //请求头中指定的一些前提条件失败（HTTP 1.1新）

    public static final int REQUEST_ENTITY_TOO_LARGE = 413;
    //目标文档的大小超过服务器当前原意处理的大小。如果服务器认为自己能够稍后再处理请求，则应该提供一个retry-After头（HTTP 1.1新）

    public static final int REQUEST_URL_TOO_LONG = 414;
    //URL太长（ HTTP 1.1新）

    public static final int REQUIRED_RANGE_NOT_SATISFIABLE = 416;
    //服务器不能满足客户在请求中的指定range 头（HTTP 1.1新）

    public static final int INTERNAL_SERVER_ERROR = 500;
    //服务器遇到了意料不到的情况，不能完成客户的请求

    public static final int NOT_LMPLEMENTED = 501;
    //服务器不支持请求所需要的功能。例如，客户发出来了一个服务器不支持的put请求。

    public static final int BAD_GATEWAY = 502;
    //服务器作为网关或者代理时，为了完成请求访问下一个服务器，但该服务器返回了非法的应答。

    public static final int SERVICE_UNAVILABLE = 503;
    //服务器由于维护或者负载过重未能应答。例如，servlet 可能在数据库连接池已满的情况下返回503.服务器返回503时可以提供一个retry-after头。

    public static final int GATEWAY_TIMEOUT = 504;
    //作为代理或网关服务器使用，表示不能及时的从远程服务器获得应答（HTTP 1.1新）

    public static final int HTTPVERSION_NOT_SUPPORTED = 505;
    //服务器不支持请求中所指明的HTTP版本。（HTTP 1.1新）

}
