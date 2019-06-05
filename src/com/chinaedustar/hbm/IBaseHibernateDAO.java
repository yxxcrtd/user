package com.chinaedustar.hbm;

import org.hibernate.Session;

/**
 * Data access interface for domain model
 */
public interface IBaseHibernateDAO {
	
	public Session getSession();
	
}
