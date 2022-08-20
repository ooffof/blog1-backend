//package top.cuizilin.website;
//
//import com.aliyun.oss.ClientException;
//import com.aliyun.oss.OSS;
//import com.aliyun.oss.OSSClientBuilder;
//import com.aliyun.oss.OSSException;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.baomidou.mybatisplus.generator.FastAutoGenerator;
//import com.baomidou.mybatisplus.generator.config.OutputFile;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//import com.mysql.cj.jdbc.Blob;
//import org.commonmark.Extension;
//import org.commonmark.ext.gfm.tables.TablesExtension;
//import org.commonmark.node.Node;
//import org.commonmark.parser.Parser;
//import org.commonmark.renderer.html.HtmlRenderer;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.util.DigestUtils;
//import top.cuizilin.website.config.URLConfig;
//import top.cuizilin.website.mapper.ArticleMapper;
//import top.cuizilin.website.mapper.TagMapper;
//import top.cuizilin.website.mapper.TypeMapper;
//import top.cuizilin.website.pojo.Article;
//import top.cuizilin.website.pojo.Tag;
//import top.cuizilin.website.pojo.Type;
//import top.cuizilin.website.pojo.User;
//import top.cuizilin.website.service.ITypeService;
//import top.cuizilin.website.utils.Constants;
//import top.cuizilin.website.utils.MyHashMap;
//import top.cuizilin.website.utils.ObjectUtils;
//import top.cuizilin.website.vo.ArticlePost;
//import top.cuizilin.website.vo.UserRegistered;
//
//import java.io.*;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.lang.reflect.Modifier;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URI;
//import java.net.URL;
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.util.*;
//
//@SpringBootTest
//class WebsiteApplicationTests {
//
//    @Autowired
//    private TypeMapper typeMapper;
//
//    @Autowired
//    private ArticleMapper articleMapper;
//
//    @Autowired
//    private TagMapper tagMapper;
//
//    @Autowired
//    private ITypeService service;
//
//
//    @Test
//    void contextLoads() {
//        FastAutoGenerator.create("jdbc:mysql://localhost:3306/mywebsite", "root", "333333")
//                .globalConfig(builder -> {
//                    builder.author("shardemachael") // 设置作者
//                            .outputDir("D:\\SOFT\\program\\java\\mywebsite\\website\\src\\main\\java"); // 指定输出目录
//                })
//                .packageConfig(builder -> {
//                    builder.parent("top.cuizilin.website") .entity("pojo")// 设置父包名
//                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\SOFT\\program\\java\\mywebsite\\website\\src\\main\\resources\\mappers")); // 设置mapperXml生成路径
//                })
//                .strategyConfig(builder -> {
//                    builder.addInclude("t_article_tag") // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
//                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .execute();
//    }
//    @Test
//    public void test2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        User user =  new User();
//        Class<?> c = user.getClass();
//        user.setUserId("sdgasdg");
//        for(Field f : c.getDeclaredFields()){
//            int modifiers = f.getModifiers();
//            if(!Modifier.isStatic(modifiers)){
//                String getMethodName = "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
//                System.out.println(getMethodName);
//                Method m = c.getMethod(getMethodName);
//                Object o = m.invoke(user);
//                System.out.println(o == null);
//            }
//        }
//    }
//    @Test
//    public void test3() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
//        UserRegistered userRegistered = new UserRegistered();
//        User user = new User();
//        userRegistered.setUsername("test");
//        userRegistered.setEmail("testemail");
//        userRegistered.setNickname("testNickname");
//        userRegistered.setPassword("testPassword");
//        ObjectUtils.mergeObj(userRegistered, user);
//        System.out.println(user);
//    }
//    @Test
//    public void test4(){
//        LocalDateTime dateTime = LocalDateTime.now();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//        String format1 = format.format(dateTime);
//        System.out.println(format1);
//    }
//    @Test
//    public void test5() throws IOException {
//        URL url = new URL("https://picsum.photos/200/200");
//        BufferedInputStream in = new BufferedInputStream(url.openStream());
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//        byte[] buffer = new byte[1024];
//        int length;
//
//        while ((length = in.read(buffer)) > 0) {
//            out.write(buffer, 0, length);
//        }
//        FileOutputStream fout = new FileOutputStream(new File("D:/test.jpg"));
//        byte[] context=out.toByteArray();
//        fout.write(context);
//        in.close();
//        out.close();
//        Blob blob = new Blob(context, null);
//    }
//    @Test
//    public void test6(){
//        String password = DigestUtils.md5DigestAsHex("asdfdsfdgdfg43t2y4y  43t3wg".getBytes());
//        System.out.println(password + " " + password.length());
//    }
//    @Test
//    public void test7(){
//        System.out.println(typeMapper.selectOne(new QueryWrapper<Type>().eq("type_id", "sdgds")));
//    }
//    @Test
//    public void test8(){
//        System.out.println(articleMapper.selectOne(new QueryWrapper<Article>().eq("article_id", "1")));
//    }
//    @Test
//    public void test9(){
//        System.out.println(tagMapper.selectOne(new QueryWrapper<Tag>().eq("tag_id", "1")));
//    }
//    @Test
//    public void test10(){
//        service.save(new Type());
//    }
//    @Test
//    public void test11(){
//        Page<Type> page = new Page<>(1, 2);
//        IPage<Type> typeIPage = typeMapper.selectPage(page, null);
//        System.out.println(typeIPage.getRecords());
//    }
//    @Test
//    public void test12(){
//        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
//        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
//        String accessKeyId = "LTAI5tLXcEoox9KeZysjJB2Z";
//        String accessKeySecret = "sQ2ccnqmSJDiZ99Fm4ZblF7caNr1v8";
//        // 填写Bucket名称，例如examplebucket。
//        String bucketName = "cuizilin-bucket-1";
//        // 填写Object完整路径，例如exampledir/exampleobject.txt。Object完整路径中不能包含Bucket名称。
//        String objectName = "exampledir/exampleobject.txt";
//
//        // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//
//        try {
//            String content = "Hello OSS";
//            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content.getBytes()));
//        } catch (OSSException oe) {
//            System.out.println("Caught an OSSException, which means your request made it to OSS, "
//                    + "but was rejected with an error response for some reason.");
//            System.out.println("Error Message:" + oe.getErrorMessage());
//            System.out.println("Error Code:" + oe.getErrorCode());
//            System.out.println("Request ID:" + oe.getRequestId());
//            System.out.println("Host ID:" + oe.getHostId());
//        } catch (ClientException ce) {
//            System.out.println("Caught an ClientException, which means the client encountered "
//                    + "a serious internal problem while trying to communicate with OSS, "
//                    + "such as not being able to access the network.");
//            System.out.println("Error Message:" + ce.getMessage());
//        } finally {
//            if (ossClient != null) {
//                ossClient.shutdown();
//            }
//        }
//    }
//
//    @Test
//    public void test13(){
//        Map<String, String> map = new HashMap<>();
//        map.put("1", "132");
//        System.out.println(map);
//    }
//
//    @Test
//    public void test14(){
//        articleMapper.saveTagAndArticle("04b360bdc4879f40d24fdd8124c05819", "e2743685408a1c9e8a921553d95de103");
//    }
//
//    @Test
//    public void test15(){
//        List<Article> articles = articleMapper.selectList(null);
//        System.out.println(articles);
//    }
//    @Test
//    public void test16(){
//        List<String> list = new ArrayList<>();
//        list.add("821d0146f1641928fc5ec0fd5dc7f09e");
//        IPage<Article> ipage = new Page<>(1, 2);
//
//       System.out.println(articleMapper.searchAllByTagList(list, ipage, 0, true).getRecords());
//    }
//    @Test
//    public void test17(){
//        String tagId = "821d0146f1641928fc5ec0fd5dc7f09e";
//        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
//        queryWrapper.orderBy(true, true, "create_time").eq("tag_id", tagId);
//        System.out.println(tagMapper.selectOne(queryWrapper));
//    }
//
//    @Test
//    public void test18(){
//        List<Extension> extensions = Arrays.asList(TablesExtension.create());
//        Parser parser = Parser.builder()
//                .extensions(extensions)
//                .build();
//        HtmlRenderer renderer = HtmlRenderer.builder()
//                .extensions(extensions)
//                .build();
//        Node document = parser.parse("#### 前言：jQuery对ajax操作进行了封装，在jQuery中$.ajax()是最底层的方法，第二层是load(), $.get(), $.post(), 第三层是$.getScript()和$.getJSon(), 首先介绍第二层方法，因为其使用频率更高\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "#### 1、load()方法 (最常用)\n" +
//                "\n" +
//                "load()方法是jQuery中最简单和常用的ajax方法，能载入远程html代码并插入到dom中，它的结构为\n" +
//                "\n" +
//                "###### load(url, [data], [callback])\n" +
//                "\n" +
//                "| 参数名称       | 类型     | 说明                  |\n" +
//                "| -------------- | -------- | --------------------- |\n" +
//                "| url            | String   | 请求html页面的url地址 |\n" +
//                "| data(可选)     | Object   | 发送给服务器的数据    |\n" +
//                "| callback(可选) | Function | 请求完成时的回调函数  |\n" +
//                "\n" +
//                "先来一个简单实例\n" +
//                "\n" +
//                "```html\n" +
//                "<!--这是test.html的内容-->\n" +
//                "<div>\n" +
//                "    <p>这是一段文字</p>\n" +
//                "</div>\n" +
//                "\n" +
//                "<!--这是index.html的内容, 注意二者必须在同一文件夹下，否则必须加路径-->\n" +
//                "<button type=\"button\" id=\"send\">Ajax按钮</button>\n" +
//                "<div id=\"result\"></div>\n" +
//                "\n" +
//                "<script type=\"text/javascript\">\n" +
//                "    $(function(){\n" +
//                "        $(\"#send\").click(function(){\n" +
//                "            $(\"#result\").load(\"test.html\");\n" +
//                "        })\n" +
//                "    })\n" +
//                "</script>\n" +
//                "```\n" +
//                "\n" +
//                "![image-20210825212054987](http://www.image.cuizilin.top/photo/image-20210825212054987.png)\n" +
//                "\n" +
//                "显然,load()方法完成了原本很繁琐的工作，开发人员只需要使用jQuery选择器为HTML片段指定目标位置，然后将要加载的文件的URL传递给load()方法即可。点击按钮之后，test.html的内容就会加载并插入到index.html中id为result的元素中\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "##### 筛选载入的HTML文档\n" +
//                "\n" +
//                "load()方法参数的语法结构为load(\"url selector\") 注意中间有空格\n" +
//                "\n" +
//                "##### 传递方式\n" +
//                "\n" +
//                "load()方法的传递方式根据参数data来决定，如果没有参数，则采用GET方式来进行传递。反之，则会自动转换为POST方法传递。\n" +
//                "\n" +
//                "##### 回调函数\n" +
//                "\n" +
//                "```javascript\n" +
//                "$(\"#result\").load(\"test.html\", function(responseText, textStatus, XMLHttpRequest){\n" +
//                "    // responseText: 请求返回的内容\n" +
//                "    // textStatus: 请求状态 success error notmodified timeout四种\n" +
//                "    // XMLHttpRequest: XMLHttpRequest对象\n" +
//                "})\n" +
//                "```\n" +
//                "\n" +
//                "#### 2.$.get()和$.post()方法\n" +
//                "\n" +
//                "##### \t1.$.get()方法\n" +
//                "\n" +
//                "| 参数名称          | 类型     | 说明                                                         |\n" +
//                "| ----------------- | -------- | ------------------------------------------------------------ |\n" +
//                "| url               | String   | 请求的url地址                                                |\n" +
//                "| data（可选）      | Object   | 数据                                                         |\n" +
//                "| callback （可选） | Function | 载入成功时的回调函数（只有当Response的返回success状态时才执行） |\n" +
//                "| type （可选）     | String   | 服务器端返回内容的格式，包括xml, html, script, json, text, _default |\n" +
//                "\n" +
//                "##### \t2.$.post()方法 （与$.get()语法相同，但实质有区别）\t\n" +
//                "\n" +
//                "##### 3.$.getScript()和$.getJson()方法\n" +
//                "\n" +
//                "\u200B\t1.$.getScript()方法\n" +
//                "\n" +
//                "```javascript\n" +
//                "$(function(){\n" +
//                "    //动态加载myJs.js这个js文件，加载完成后执行回调函数\n" +
//                "    $.getScript(\"myJs.js\", function(){\n" +
//                "        \n" +
//                "    })\n" +
//                "})\n" +
//                "```\n" +
//                "\n" +
//                "\u200B\t2.$.getJson()方法 （动态加载Json文件）\n" +
//                "\n" +
//                "```javascript\n" +
//                "$(function(){\n" +
//                "    //动态加载myJs.js这个json文件，加载完成后执行回调函数循环遍历\n" +
//                "    $.getScript(\"myJs.json\", function(data){\n" +
//                "        $.each(data, function(elementIndex, element){\n" +
//                "            //elementIndex: data的每一项数据的索引\n" +
//                "            //element: data的每一项数据\n" +
//                "        })\n" +
//                "    })\n" +
//                "})\n" +
//                "```\n" +
//                "\n" +
//                "##### 4、$.ajax()方法\n" +
//                "\n" +
//                "```javascript\n" +
//                "$.ajax({\n" +
//                "    type:\"POST\",                    //String 默认为GET\n" +
//                "    timeout:2000,                   //Number 设置超时时间（毫秒）\n" +
//                "    url:\"{:U('testOAuthAPI')}\",     //String 发送请求的地址\n" +
//                "    dataType:\"json\",                //String 预期服务器返回的数据类型 xml、html、script、json、jsonp、jQuery、text\n" +
//                "    data:{'key':value},               //GET请求中将附在URL后；对象必须为key/value形式。如果是数组，jQuery将自动为不同值对应同一名称例如：{foo:[\"bar1\",\"bar2\"]}转换为&foo=bar1&foo=bar2\n" +
//                "    //提交前回调函数（发送请求前可以修改XMLHttpRequest对象的函数）\n" +
//                "    beforeSend:function(XMLHttpRequest){\n" +
//                "        this;   //调用本次Ajax请求时传递的options参数\n" +
//                "    },\n" +
//                "    //请求成功后处理（data可能是xmlDoc、jsonObj、html、text；textStatus（请求状态）：success、error、notimodified、timeout）\n" +
//                "    success:function(data,textStatus){\n" +
//                "        this;   //调用本次Ajax请求时传递的options参数\n" +
//                "        //window.location.href = data.getCodeUrl;\n" +
//                "        /*location.reload();*/\n" +
//                "    },\n" +
//                "    //请求失败后处理（通常情况下textStatus和errorThrown只有其中一个包含信息）\n" +
//                "    error: function (XMLHttpRequest,textStatus,errorThrown) {\n" +
//                "        console.log(\"error-----------\");\n" +
//                "    },\n" +
//                "    //请求完成后处理（请求成功或失败时均调用）\n" +
//                "    complete:function(XMLHttpRequest,textStatus){\n" +
//                "        this;   //调用本次Ajax请求时传递的options参数\n" +
//                "    }\n" +
//                "});\n" +
//                "```\n" +
//                "\n" +
//                "#### 以load()方法为例，演示一个综合的ajax局部刷新机制\n" +
//                "\n" +
//                "<video src=\"https://cuizilin-blog-photo.oss-cn-beijing.aliyuncs.com/video/2021-08-26-19-39-10.mp4\"></video>\n" +
//                "\n" +
//                "##### UI界面\n" +
//                "\n" +
//                "![image-20210827002202028](http://www.image.cuizilin.top/photo/image-20210827002202028.png)\n" +
//                "\n" +
//                "##### js代码\n" +
//                "\n" +
//                "```javascript\n" +
//                " //get方式获取commentList\n" +
//                "    $(\"#comment-container\").load(\"/comment\");\n" +
//                "\n" +
//                "    //以post方式提交数据\n" +
//                "    $(function (){\n" +
//                "        $(\"#my_btn\").click(function(){\n" +
//                "            $(\"#comment-container\").load(\"/comment\", {\n" +
//                "                \"username\" : $(\"[name='username']\").val(),\n" +
//                "                \"content\" : $(\"[name='content']\").val()\n" +
//                "            }, function(){\n" +
//                "                /*//清空\n" +
//                "                $(\"[name='username']\").val(\"\");\n" +
//                "                $(\"[name='content']\").val(\"\");*/\n" +
//                "            })\n" +
//                "        })\n" +
//                "    });\n" +
//                "```\n" +
//                "\n" +
//                "##### java代码\n" +
//                "\n" +
//                "```java\n" +
//                "@GetMapping(\"/comment\")\n" +
//                "    public String comment(Model model){\n" +
//                "        model.addAttribute(\"commentList\", commentMapper.getCommentList());\n" +
//                "        return \"index :: commentList\";\n" +
//                "    }\n" +
//                "\n" +
//                "    @PostMapping(\"/comment\")\n" +
//                "    public String addComment(Comment comment, Model model){\n" +
//                "        System.out.println(\"========\");\n" +
//                "        commentMapper.addComment(comment);\n" +
//                "        model.addAttribute(\"commentList\", commentMapper.getCommentList());\n" +
//                "        return \"index :: commentList\";\n" +
//                "    }\n" +
//                "\n" +
//                "```\n" +
//                "\n");
//        String render = renderer.render(document);// "<p>This is <em>Sparta</em></p>\n"
//        System.out.println(render);
//    }
//    @Autowired
//    private URLConfig urlConfig;
//    @Test
//    public void test19(){
//        System.out.println(urlConfig.getFrontEndUrl());
//    }
//}
