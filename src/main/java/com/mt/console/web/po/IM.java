package com.mt.console.web.po;

import java.io.Serializable;
import java.sql.Timestamp;

public class IM implements Serializable {
	private static final long serialVersionUID = -172021138199730189L;
	private Long id;
	private String fromAccount;
	private String toAccount;
	private String content;
	private int reach;
	private Timestamp reachTime;
	private Timestamp createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public String getToAccount() {
		return toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReach() {
		return reach;
	}

	public void setReach(int reach) {
		this.reach = reach;
	}

	public Timestamp getReachTime() {
		return reachTime;
	}

	public void setReachTime(Timestamp reachTime) {
		this.reachTime = reachTime;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
