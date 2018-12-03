package com.yhz.webmagic.app;

public class App {
	private String appName;
	private String appSize;
	private String updateTime;
	private String downloadCount;
	private String score;
	private String commentTotalNumber;
	private String goodComment;
	private String badComment;
	private String middleComment;
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppSize() {
		return appSize;
	}
	public void setAppSize(String appSize) {
		this.appSize = appSize;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getDownloadCount() {
		return downloadCount;
	}
	public void setDownloadCount(String downloadCount) {
		this.downloadCount = downloadCount;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getCommentTotalNumber() {
		return commentTotalNumber;
	}
	public void setCommentTotalNumber(String commentTotalNumber) {
		this.commentTotalNumber = commentTotalNumber;
	}
	public String getGoodComment() {
		return goodComment;
	}
	public void setGoodComment(String goodComment) {
		this.goodComment = goodComment;
	}
	public String getBadComment() {
		return badComment;
	}
	public void setBadComment(String badComment) {
		this.badComment = badComment;
	}
	public String getMiddleComment() {
		return middleComment;
	}
	public void setMiddleComment(String middleComment) {
		this.middleComment = middleComment;
	}
	
	@Override
    public String toString() {
        return "App [appname=" + appName + ", appSize=" + appSize + ", updateTime=" + updateTime + ", downloadCount=" + downloadCount + ", score="
                + score + ", commentTotalNumber=" + commentTotalNumber + ", goodComment=" + goodComment + ", badComment=" + badComment + ", middleComment=" + middleComment +"]";
    }
	
}
