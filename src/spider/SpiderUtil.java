package spider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import com.ibm.icu.util.RangeValueIterator.Element;
import com.lowagie.text.Document;

import bean.Image;

/**
 * 抓去豆瓣网上的美女图片
 * @author ASUS
 *
 */
public class SpiderUtil {
	//保存要下载图片的网址
	private static Map<String,String> urls = new HashMap<String,String>();
	static {
		urls.put("fresh", "http://www.dbmeinv.com/dbgroup/show.htm?cid=4");
		urls.put("tui", "http://www.dbmeinv.com/dbgroup/show.htm?cid=3");
		//http://localhost:8080/spider/douban.html
	}
	/*
	 * 获取制定类型以及页码的图片对象
	 * category 你选择的参数
	 * pageNum页码
	 */
	public static List<Image> queryImageList(String category, String pageNum){
		List<Image> images = new ArrayList<Image>();
		try {
			org.jsoup.nodes.Document doc = Jsoup.connect(urls.get(category))
					.data("pager_offset",pageNum)
					.timeout(3000)//设置超时时间
					.get();
			Elements imgs = doc.getElementsByTag("img");
			Image image = null;
			for(org.jsoup.nodes.Element img : imgs){
				image = new Image();
				String shortUrl = img.attr("src");
				String title = img.attr("title");
				String oriUrl = img.attr("src");
				
				if(shortUrl !=null && !"".equals(shortUrl)&&
						title !=null && !"".equals(title)){
					image.setOriUrl(oriUrl);
					image.setShortUrl(shortUrl);
					image.setTitle(title);
					
					images.add(image);
				}
				
				
			}
		} catch (IOException e) {
			// 写日志
			e.printStackTrace();
		}
		return images;
	}
}
