package util;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.Comment;
import model.Tag;
import org.apache.commons.beanutils.BeanUtils;

import model.Article;

public class Form2Bean {

	public static Comment commentForm2Bean(HttpServletRequest request) throws FailException {

		//从article.jsp中取得文章id
		int id = Integer.valueOf(request.getParameter("id"));

		String nickname = request.getParameter("w_nickname");
		String content = request.getParameter("w_content");

		Comment bean = new Comment();
		//向comment对象中放入字段
		bean.setArticle_id(id);
		bean.setNickname(nickname);
		bean.setContent(content);
		if (vilidate(bean)) {
			bean.setDiss(0);
			bean.setStar(0);
			bean.setTime(DateUtils.getFormatDate(new Date()));
			return bean;
		}
		throw new FailException("Create Fail!");

	}

	private static boolean vilidate(Comment c) {
		boolean result = true;

		if (c.getArticle_id() == 0 || StringUtils.isEmpty(c.getContent())) {
			result = false;
		}
		return result;
	}

	public static Article articleForm2Bean(HttpServletRequest request) throws FailException {

		//将界面取得数据放到value中
		Map value = new HashMap();

		//处理时间---提交文章，生成的时间传入数据库
		Date now_date = new Date();
		String time = DateUtils.getFormatDate(now_date);

		//测试--打印文章
		System.out.println(request.getParameter("editormd-markdown-doc"));

		value.put("title", request.getParameter("title"));
		value.put("time", time);
		//测试作者---登录注册完成要更改
		value.put("author", "ganyurou");
		value.put("sort", request.getParameter("sort"));
		value.put("content", request.getParameter("editormd-markdown-doc"));
		value.put("star", 0);
		value.put("comment", 0);
		value.put("visit", 0);

		Article bean = new Article();

		try {
			BeanUtils.populate(bean, value);
		} catch (Exception e) {
			e.printStackTrace();

		}
		if (vilidate(bean)) {
			return bean;
		}
		throw new FailException("Create Fail!");

	}



	private static boolean vilidate(Article a) {
		boolean result = true;

		if (a.getSort() == null || a.getTitle() == null || a.getAuthor() == null || a.getTime() == null) {
			result = false;
		}

		return result;
	}

}
