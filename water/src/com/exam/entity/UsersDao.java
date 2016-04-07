package com.exam.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.framework.auto.consts.Consts;

/**
* Dao object for domain model class Users.
* @see com.exam.entity.Users
* @author Calvin. Sun
* Generated 2010-06-06 11:48:42
*/
public class UsersDao extends HibernateDaoSupport implements IUsersDao {}

