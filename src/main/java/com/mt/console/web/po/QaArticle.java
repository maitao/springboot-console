package com.mt.console.web.po;

import java.sql.Timestamp;

public class QaArticle {

	private Long id;
	private Long categoryId;
	private String number;
	private String title;
	private Timestamp createTime;
	private String createAccount;
	private String summary;
	private Integer level;
	private String reviseAccount;
	private Timestamp updateTime;
	private Timestamp delete_time;
	private Timestamp freeze_time;
	private Integer viewCount;
	private Integer likeCount;
	private String content;
	private Integer commentCount;
	private String updateAccount;
	private Integer freezUpdate;
	private Integer freezComment;
	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getCreateAccount() {
		return createAccount;
	}

	public void setCreateAccount(String createAccount) {
		this.createAccount = createAccount;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getReviseAccount() {
		return reviseAccount;
	}

	public void setReviseAccount(String reviseAccount) {
		this.reviseAccount = reviseAccount;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Timestamp getDelete_time() {
		return delete_time;
	}

	public void setDelete_time(Timestamp delete_time) {
		this.delete_time = delete_time;
	}

	public Timestamp getFreeze_time() {
		return freeze_time;
	}

	public void setFreeze_time(Timestamp freeze_time) {
		this.freeze_time = freeze_time;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public String getUpdateAccount() {
		return updateAccount;
	}

	public void setUpdateAccount(String updateAccount) {
		this.updateAccount = updateAccount;
	}

	public Integer getFreezUpdate() {
		return freezUpdate;
	}

	public void setFreezUpdate(Integer freezUpdate) {
		this.freezUpdate = freezUpdate;
	}

	public Integer getFreezComment() {
		return freezComment;
	}

	public void setFreezComment(Integer freezComment) {
		this.freezComment = freezComment;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
