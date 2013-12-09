/**
 * 
 */
package com.xundian360.huaqiaotong.modle.b03;

import java.io.Serializable;
import java.util.List;

/**
 * 帖子对象
 * @author  TengTeng
 * @date      2013年10月28日
 * @version 1.0
 */
public class Posts implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// UUID,帖子唯一标识
	private String uid;
	// 标题
	private String tittle;
	// 作者
	private String author;
	// 内容缩略表示
	private String tDtail;
	// 评论数
	private String commentN;
	// ListView显示的图片路径
	private String img;
	// 帖子明细
	private List<PostsItem> dtailL;
	// 状态
	private String status;
	// 发帖时间
	private String time;
	// 置顶图片
	private List<PostsItem> topItems;
	
	public Posts() {
		super();
	}

	public Posts(String uid, String tittle, String author, String tDtail,
			String commentN, String img) {
		super();
		this.uid = uid;
		this.tittle = tittle;
		this.author = author;
		this.tDtail = tDtail;
		this.commentN = commentN;
		this.img = img;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String gettDtail() {
		return tDtail;
	}

	public void settDtail(String tDtail) {
		this.tDtail = tDtail;
	}

	public String getCommentN() {
		return commentN;
	}

	public void setCommentN(String commentN) {
		this.commentN = commentN;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public List<PostsItem> getDtailL() {
		return dtailL;
	}

	public void setDtailL(List<PostsItem> dtailL) {
		this.dtailL = dtailL;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public List<PostsItem> getTopItems() {
		return topItems;
	}

	public void setTopItems(List<PostsItem> topItems) {
		this.topItems = topItems;
	}
	
}
