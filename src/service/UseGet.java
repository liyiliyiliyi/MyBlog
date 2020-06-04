package service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.GetUtil;

import javax.servlet.http.HttpServletRequest;

public class UseGet {

    public static String ImgUrl(HttpServletRequest request) {
        String resUrl = "";
        try {
            //通过get访问方式，得到相对应的json数据
            String jsonstr = GetUtil.sendGet("https://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1");
            //java转json
            JSONObject jsonobj = JSONObject.fromObject(jsonstr);
            //获得第一个数组
            JSONArray images = jsonobj.getJSONArray("images");
            resUrl = images.getJSONObject(0).getString("url");
            //真正的图片地址
            resUrl = "https://www.bing.com" + resUrl;
        }catch (Exception e) {
            System.out.println("接口格式已经改变或接口无响应");
        }
        return resUrl;
    }

}
