package com.yhz.webmagic.app;

import org.apache.http.HttpHost;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class AppPageProcessor implements PageProcessor{
	private static int size = 0;// 共抓取到的app数量
	
	// 抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000)
    							 //设置代理
    							 .setHttpProxy(new HttpHost("117.71.18.6", 80))
    							 //添加请求头，有些网站会根据请求头判断该请求是由浏览器发起还是由爬虫发起的
    							 .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.110 Safari/537.36")
    							 .addHeader("Accept",  "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
    							 .addHeader("Accept-Encoding", "gzip, deflate, sdch")
    							 .addHeader("Accept-Language", "zh-CN,zh;q=0.8")
    							 .addHeader("Connection", "keep-alive")
    							 .addHeader("Referer", "http://zhushou.360.cn/");

	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {
		//app列表页
		if(!page.getUrl().regex("http://zhushou.360.cn/detail/index/soft_id/\\d+").match()){
			page.addTargetRequests(page.getHtml().xpath("//*[@id=\"iconList\"]").links()// 限定app列表获取区域
                    .regex("/detail/index/soft_id/\\d+")
                    .replace("/detail/index/", "http://zhushou.360.cn/detail/index/")// 巧用替换给把相对url转换成绝对url
                    .all());
			
			// 添加其他列表页s
            /*page.addTargetRequests(page.getHtml().xpath("//*[@id=\"pages_pg_2\"]").links()// 限定其他列表页获取区域
                    .regex("/" + username + "/article/list/\\d+")
                    .replace("/" + username + "/", "http://blog.csdn.net/" + username + "/")// 巧用替换给把相对url转换成绝对url
                    .all());*/
		}else{
		//app详情页
			size++;
			App app = new App();
			app.setAppName(page.getHtml().xpath("//*[@id=\"app-name\"]/span/text()").get());
			app.setAppSize(page.getHtml().xpath("//*[@id=\"app-info-panel\"]/div/dl/dd/div/span[4]/text()").get());
			app.setBadComment(page.getHtml().xpath("//*[@id=\"tab-review-bad\"]/span/text()").get().replace("(", "").replace(")", ""));
			app.setCommentTotalNumber(page.getHtml().xpath("//*[@id=\"tab-review-all\"]/span/text()").toString());
			app.setDownloadCount(page.getHtml().xpath("//*[@id=\"app-info-panel\"]/div/dl/dd/div/span[3]/text()").get().replace("下载：", ""));
			app.setGoodComment(page.getHtml().xpath("//*[@id=\"tab-review-best\"]/span/text()").get().replace("(", "").replace(")", ""));
			app.setMiddleComment(page.getHtml().xpath("//*[@id=\"tab-review-good\"]/span/text()").get().replace("(", "").replace(")", ""));
			app.setScore(page.getHtml().xpath("//*[@id=\"app-info-panel\"]/div/dl/dd/div/span[1]/text()").get());
			app.setUpdateTime(page.getHtml().xpath("//*[@id=\"sdesc\"]/div/div/table/tbody/tr[1]/td[2]/text()").get());
			
			// 把对象存入数据库
            new AppDao().add(app);
            
            System.out.println(app);
		}
	}
	
	public static void main(String[] args) {
		long startTime, endTime;
        System.out.println("【爬虫开始】请耐心等待一大波数据到你碗里来...");
        startTime = System.currentTimeMillis();
        // 从用户博客首页开始抓，开启5个线程，启动爬虫
        Spider spider = Spider.create(new AppPageProcessor());
        //spider.setDownloader(new SeleniumDownloader("E:\\xfyun\\chromedriver_win32\\chromedriver.exe"));
        spider.addUrl("http://zhushou.360.cn/list/index/cid/1/").thread(5).run();
        
        endTime = System.currentTimeMillis();
        System.out.println("【爬虫结束】共抓取" + size + "APP，耗时约" + ((endTime - startTime) / 1000) + "秒，已保存到数据库，请查收！");
	}
}
