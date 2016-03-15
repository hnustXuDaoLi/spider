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
 * ץȥ�������ϵ���ŮͼƬ
 * @author ASUS
 *
 */
public class SpiderUtil {
	//����Ҫ����ͼƬ����ַ
	private static Map<String,String> urls = new HashMap<String,String>();
	static {
		urls.put("fresh", "http://www.dbmeinv.com/dbgroup/show.htm?cid=4");
		urls.put("tui", "http://www.dbmeinv.com/dbgroup/show.htm?cid=3");
		//http://localhost:8080/spider/douban.html
	}
	/*
	 * ��ȡ�ƶ������Լ�ҳ���ͼƬ����
	 * category ��ѡ��Ĳ���
	 * pageNumҳ��
	 */
	public static List<Image> queryImageList(String category, String pageNum){
		List<Image> images = new ArrayList<Image>();
		try {
			org.jsoup.nodes.Document doc = Jsoup.connect(urls.get(category))
					.data("pager_offset",pageNum)
					.timeout(3000)//���ó�ʱʱ��
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
			// д��־
			e.printStackTrace();
		}
		return images;
	}
}
