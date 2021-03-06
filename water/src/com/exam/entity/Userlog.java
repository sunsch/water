package com.exam.entity;

// Generated 2010-6-6 10:52:06 by Hibernate Tools 3.2.2.GA

import java.util.Date;

import com.framework.auto.anno.IDMarker;
import com.framework.auto.anno.ShowMarker;
import com.framework.auto.anno.ZnValue;

/**
 * Userlog generated by hbm2java
 */
public class Userlog implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@IDMarker(true)
	private int ulid;
	
	@ShowMarker(true)
	@ZnValue("用户ID")
	private Users users;
	
	@ShowMarker(true)
	@ZnValue("上次登录时间")
	private Date lastlogontime;
	private Integer totalonlinetime;
	private Integer totallogoncount;
	
	@ShowMarker(true)
	@ZnValue("备注")
	private String comments;

	public Userlog() {
	}

	public Userlog(int ulid) {
		this.ulid = ulid;
	}

	public Userlog(int ulid, Users users, Date lastlogontime,
			Integer totalonlinetime, Integer totallogoncount, String comments) {
		this.ulid = ulid;
		this.users = users;
		this.lastlogontime = lastlogontime;
		this.totalonlinetime = totalonlinetime;
		this.totallogoncount = totallogoncount;
		this.comments = comments;
	}

	public int getUlid() {
		return this.ulid;
	}

	public void setUlid(int ulid) {
		this.ulid = ulid;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Date getLastlogontime() {
		return this.lastlogontime;
	}

	public void setLastlogontime(Date lastlogontime) {
		this.lastlogontime = lastlogontime;
	}

	public Integer getTotalonlinetime() {
		return this.totalonlinetime;
	}

	public void setTotalonlinetime(Integer totalonlinetime) {
		this.totalonlinetime = totalonlinetime;
	}

	public Integer getTotallogoncount() {
		return this.totallogoncount;
	}

	public void setTotallogoncount(Integer totallogoncount) {
		this.totallogoncount = totallogoncount;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
