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
		String Cookie = "SUB=_2A25ynJpMDeRhGedG6lAT9ibEzj6IHXVufiYErDV6PUJbktAfLRPfkW1NUWcmyDBwrr2TOP8V73Q7MWRamZUFpZzS; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WFieR0TFBa2Uo5-7fns3LXM5NHD95Qp1h2EeoqR1h-EWs4Dqc_oi--ci-z0i-zNi--NiKysi-27i--fi-2fi-zci--Ni-iFi-isi--fi-z7iK.pi--Ni-z0iK.ci--4i-20iKL8i--fiKnci-z7i--fiKyWi-27i--RiKyWiKyFi--fiKLhiKnc; SUHB=0mxVrbQGafO3cz; SSOLoginState=1603856924; _T_WM=25413971907; WEIBOCN_FROM=1110003030; MLOGIN=1; M_WEIBOCN_PARAMS=fid%3D1005052207519004%26uicode%3D10000011; XSRF-TOKEN=cec8b9";
		String itemId = "";
		String uid = "6418873693";
		System.out.println("开始下载--");
		spider spider = new spider(Cookie, itemId, uid);
		int max =spider.getPicPageMaxPage();
		for (int i = 1; i <= max; i++) {
			List<Weibo> list=spider.getuserAllweiboid(i);
			for (Weibo weibo : list) {
				if (weibo.isIsvideo()) {
					System.out.println("准备开始下载");
					if (useThunder) {//调用迅雷下载，传入ThunderStart.exe的路径
						downloadWithThunder.start("D:/Program Files (x86)/Thunder Network/Thunder/Program/ThunderStart.exe",
								spider.getViedoUrl(weibo.getItemid()));
					}else {
						try {
							spider.downloadVideoByUrl(weibo.getItemid());
						}catch (Exception e){
							continue;
						}
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
