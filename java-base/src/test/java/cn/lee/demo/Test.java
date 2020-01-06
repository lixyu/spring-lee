package cn.lee.demo;

import sun.net.www.http.HttpClient;

/**
 * @author Lee
 * @date 2020-01-03 17:18
 */
public class Test {
    public static void main(String[] args) {
        String
                SendUrl = "https://nnfpdev.jss.com.cn/shop/buyer/allow/cxfKp/cxfServerKpOrderSync.action";
        String order = "{\"identity\":\"93363DCC6064869708F1F3C72A0CE72A713A9D425CD50CDE\","
                +
                "\"order\":{\"orderno\":\"No.12016101300002\",\"saletaxnum\":\"339901999999142\","
                + "\"saleaddress\":\"&*杭州市中河中路 222 号平海国际商务大厦 5 楼 \",\"salephone\":\"0571-87022168\","
                + "\"saleaccount\":\"东亚银行杭州分行 131001088303400\",\"clerk\":\"袁牧庆\",\"payee\":\"络克\","
                + "\"checker\":\"朱燕\",\"invoicedate\":\"2016-06-15 01:51:41\","
                + "\"kptype\":\"1\",\"address\":\"\","
                +
                "\"phone\":\"13185029520\",\"taxnum\":\"110101TRDX8RQU1\",\"buyername\":\" 个 人\",\"account\":\"\","
                + "\"fpdm\":\"\",\"fphm\":\"\",\"message\":\"开票机号为02 请前往诺诺网(www.jss.com.cn) 查询发票详情\","
                + "\"qdbz\":\"1\",\"qdxmmc\":\"1111\","
                +
                "\"detail\":[{\"goodsname\":\"1\",\"spec\":\"1\",\"unit\":\"1\",\"hsbz\":\"1\",\"num\":\"1\","
                + "\"price\":\"19.99\","
                +
                "\"spbm\":\"1090511030000000000\",\"fphxz\":\"0\",\"yhzcbs\":\"0\",\"zzstsgl\":\"222222\",\"l  slbs\":\"\","
                + "\"taxrate\":\"0.16\"},"
                + "{\"goodsname\":\"2\",\"spec\":\"2\","
                + "\"unit\":\"2\",\"hsbz\":\"1\",\"num\":\"1\",\"price\":\"20\","
                +
                "\"spbm\":\"1090511030000000000\",\"fphxz\":\"0\",\"yhzcbs\":\"0\",\"zzstsgl\":\"222222\",\"lslbs\":\"\","
                + "\"taxrate\":\"0.16\"}]}}";
        order = DESDZFP.encrypt(order);
        HttpClient httpclient = null;
        PostMethod post = null;
        try {
            httpclient = new HttpClient();
            post = new PostMethod(SendUrl);
//设置编码方式
            post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
//添加参数
            post.addParameter("order", order);
//执行
            httpclient.executeMethod(post);
//接口返回信息
            String info = new String(post.getResponseBody(), "UTF-8");
            System.out.println(info);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//关闭连接，释放资源
            post.releaseConnection();
            ((SimpleHttpConnectionManager) httpclient.getHttpConnectionManager()).shutdown();
        }
    }
}
