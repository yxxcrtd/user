package com.chinaedustar.hbm;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Data access object (DAO) for domain model
 */
@SuppressWarnings( {"rawtypes", "unchecked"})
public class BaseHibernateDAO implements IBaseHibernateDAO {

	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}

	/**
	 * 通过用户数据的 ID 号得到数据
	 * 
	 * @param sDataId
	 * @return
	 */
	public TUser GetUserById(Long sDataId) {
		Session oSession = getSession();
		List lDataList = oSession.createQuery("from TUser as model where model.id = " + sDataId).list();
		oSession.close();
		if (lDataList == null || lDataList.size() == 0) {
			return null;
		} else {
			return (TUser) lDataList.get(0);
		}
	}

	/**
	 * 通过用户的登录名得到数据
	 * 
	 * @param sUserId
	 * @return
	 */
	public TUser GetUserByUserId(String sUserId) {
		Session oSession = getSession();

		try {
			String sHql = "from TUser as model where model.loginId = ?";

			Query oQuery = oSession.createQuery(sHql);
			oQuery.setParameter(0, sUserId);

			List lDataList = oQuery.list();

			if (lDataList == null || lDataList.size() == 0) {
				return null;
			} else {
				return (TUser) lDataList.get(0);
			}
		} catch (RuntimeException re) {
			System.out.println(re.getStackTrace());
			return null;
		} finally {
			oSession.close();
		}
	}

	/**
	 * 依据搜索条件得到数据列表
	 * 
	 * @param sKey
	 * @return
	 */
	public List<TUser> GetAllUserByKey(String[] sKey, String[] sSym,
			String[] sVal, String[] sRea) {
		Session oSession = getSession();

		try {
			int i = 0;

			String sHql = "from TUser as model";

			if (sKey != null) {
				sHql += " where";

				for (i = 0; i < sKey.length; i++) {
					sHql += " model." + sKey[i] + " " + sSym[i] + " ?";

					if (i < (sKey.length - 1))
						sHql += " " + sRea[i];
				}
			}

			sHql += "orderby id desc";

			Query oQuery = oSession.createQuery(sHql);

			if (sKey != null) {
				for (i = 0; i < sVal.length; i++) {
					oQuery.setParameter(i, sVal[i]);
				}
			}

			return oQuery.list();
		} catch (RuntimeException re) {
			System.out.println(re.getStackTrace());

			return null;
		} finally {
			oSession.close();
		}
	}

	/**
	 * 依据搜索条件得到数据列表（起始行和数据条数）
	 * 
	 * @param sKey
	 * @param iStartPos
	 * @param iDataCou
	 * @return
	 */
	public List<TUser> GetAllUserByKey(String[] sKey, String[] sSym,
			String[] sVal, String[] sRea, int iStartPos, int iDataCou) {
		Session oSession = getSession();

		try {
			int i = 0;

			String sHql = "from TUser as model";

			if (sKey != null) {
				sHql += " where";

				for (i = 0; i < sKey.length; i++) {
					sHql += " model." + sKey[i] + " " + sSym[i] + " ?";

					if (i < (sKey.length - 1))
						sHql += " " + sRea[i];
				}
			}

			sHql += " order by id asc";

			Query oQuery = oSession.createQuery(sHql);

			if (sKey != null) {
				for (i = 0; i < sVal.length; i++) {
					oQuery.setParameter(i, sVal[i]);
				}
			}

			oQuery.setFirstResult((iStartPos > 0) ? iStartPos : 0);
			oQuery.setMaxResults((iDataCou > 0) ? iDataCou : 20);

			return oQuery.list();
		} catch (RuntimeException re) {
			System.out.println(re.getMessage());

			return null;
		} finally {
			oSession.close();
		}
	}

	/**
	 * 依据条件得到用户数据数目
	 * 
	 * @param sKey
	 * @return
	 */
	public int GetUserCount(String[] sKey, String[] sSym, String[] sVal,
			String[] sRea) {
		Session oSession = getSession();

		try {
			int i = 0;

			String sHql = "select count(*) from TUser as model";

			if (sKey != null) {
				sHql += " where";

				for (i = 0; i < sKey.length; i++) {
					sHql += " model." + sKey[i] + " " + sSym[i] + " ?";

					if (i < (sKey.length - 1))
						sHql += " " + sRea[i];
				}
			}

			Query oQuery = oSession.createQuery(sHql);

			if (sKey != null) {
				for (i = 0; i < sVal.length; i++) {
					oQuery.setParameter(i, sVal[i]);
				}
			}

			Object oQueryRes = oQuery.uniqueResult();

			if (oQueryRes == null) {
				return 0;
			} else {
				return ((Integer) oQueryRes).intValue();
			}
		} catch (RuntimeException re) {
			System.out.println(re.getMessage());

			return 0;
		} finally {
			oSession.close();
		}
	}

	/**
	 * 通过用户数据的 ID 号删除该条数据
	 * 
	 * @param sKey
	 * @return
	 */
	public int DelUserByKey(String sDataId) {
		if (!sDataId.equals("")) {
			TUser oTUser = GetUserById(Long.valueOf(sDataId));

			if (oTUser.getLoginId().equals("admin"))
				return 0;

			Session oSession = getSession();

			try {
				Transaction oTraSes = oSession.beginTransaction();
				oSession.delete(oTUser);
				oTraSes.commit();
				return 1;
			} catch (Exception e) {
				System.out.println(e.toString());
			} finally {
				oSession.close();
			}
		}
		return 0;
	}
	
}
