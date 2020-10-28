package main;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

import dowmload.downloadWithThunder;
import spider.spider;
import weibo.Weibo;

public class run {

	public static void main(String[] args) throws InterruptedException, IOException {
		boolean useThunder=false;//是否使用迅雷下载
		String Cookie = "";
		String itemId = "";
		String uid = "";
		System.out.println("开始下载--");
		spider spider = new spider(Cookie, itemId, uid);
		int max =spider.getPicPageMaxPage();
		for (int i = 1; i <= max; i++) {
			List<Weibo> list=spider.getuserAllweiboid(i);
			for (Weibo weibo : list) {
				if (weibo.isIsvideo()) {
					System.out.println("准备开始下载");
					try {
							spider.downloadVideoByUrl(weibo.getItemid());
					}catch (Exception e){
							continue;
					}
				}else {
					try {
						spider.getpicurl(weibo.getItemid(),weibo.getContent());
					} catch (NullPointerException e) {
						continue;
					} catch(SocketTimeoutException e){
						continue;
					}
				}
			}	
			System.out.println("第"+i+"页ok,剩余"+(max-i)+"页");
		}
	}

}
