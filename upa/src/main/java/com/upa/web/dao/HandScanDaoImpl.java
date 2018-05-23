package com.upa.web.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.upa.web.constant.HandScanConstant;
import com.upa.web.model.entity.HandScanHeader;
import com.upa.web.model.entity.HandScanRecord;

@Repository
public class HandScanDaoImpl {

	@Autowired
	private SessionFactory sessionFactory;

	public HandScanDaoImpl(){}

	public HandScanDaoImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}

	public String saveHandscan(HandScanRecord handscan){
		sessionFactory.getCurrentSession().saveOrUpdate(handscan);
		return HandScanConstant.SUCCESS;
	}
	
	public String saveHandscanHeader(HandScanHeader hs) {
		sessionFactory.getCurrentSession().saveOrUpdate(hs);
		return HandScanConstant.SUCCESS;
	}
	
	public String getCurrentDate() {
        Session session = sessionFactory.openSession();
        SQLQuery sqlQuery = session.createSQLQuery("select now() as sysdate from dual");
        String result = sqlQuery.getSingleResult().toString();
        return sqlQuery.toString();
	}



	@SuppressWarnings("unchecked")
	public List<HandScanHeader> getHandScan() {
		//List<HandScanHeader> hsList = (List<HandScanHeader>) sessionFactory.getCurrentSession().createCriteria(HandScanHeader.class);
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try{
	         tx = session.beginTransaction();
	         List<HandScanHeader> hsList = session.createQuery("FROM HandScanHeader").list(); 
	         for (Iterator<HandScanHeader> iterator = hsList.iterator(); iterator.hasNext();){
	        	 HandScanHeader hs = (HandScanHeader) iterator.next(); 
	         }
	         tx.commit();
	         return hsList;
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace();
	         return null;
	      }finally {
	         session.close();
	      }
	}
	
	@SuppressWarnings("unchecked")
	public HandScanHeader getHandScanOfTerm(Date date, String userId) {
		//List<HandScanHeader> hsList = (List<HandScanHeader>) sessionFactory.getCurrentSession().createCriteria(HandScanHeader.class);
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		System.out.println("2018-05-18-02");
		try{
			tx = session.beginTransaction();
			// Create HQL Between clause
			//String HQL_QUERY = "FROM HandScanHeader where firstDate <= :date";// and lastDate >= :date ";
			String HQL_QUERY = "FROM HandScanHeader where firstDate <= :date and lastDate >= :date and appuser.userId = :userId";

			Query query = session.createQuery(HQL_QUERY);
			
			query.setParameter("date", date, TemporalType.DATE);
			query.setParameter("userId", userId);
			
			//HandScanHeader hs = (HandScanHeader) query.getSingleResult();
			
			
			HandScanHeader hs = new HandScanHeader();
			hs = (HandScanHeader) query.getSingleResult();
			
//			if(obj!=null){
//				hs = (HandScanHeader)obj; 
//			}
//			if(hs != null){
//				hs.getHandscanrecords();
//			}

			tx.commit();
			return hs;
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			session.close(); 
			return null;
		}catch(NoResultException e){
			e.printStackTrace();
			session.close(); 
			return null;
		}catch(Exception e){
			//todo: java.lang.String cannot be cast to java.lang.Long when NoResult
			e.printStackTrace();
			session.close();
			return null;
		}
	}

	
	@SuppressWarnings("unchecked")
	public HandScanHeader getHandScanHeaderById(long id) {
		//List<HandScanHeader> hsList = (List<HandScanHeader>) sessionFactory.getCurrentSession().createCriteria(HandScanHeader.class);
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			// Create HQL Between clause
			String HQL_QUERY = "FROM HandScanHeader where headerId <= :id";// and lastDate >= :date ";

			Query query = session.createQuery(HQL_QUERY);

			query.setParameter("id", id);

			HandScanHeader hs = (HandScanHeader) query.getSingleResult();
			if(hs != null){
				hs.getHandscanrecords();
			}

			tx.commit();
			return hs;
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			return null;
		}catch(NoResultException e){
			//e.printStackTrace();
			return null;
		}finally {
			session.close(); 
		}
	}

	public List<Date> getParticipateTime(Integer id) {
		//List<HandScanHeader> hsList = (List<HandScanHeader>) sessionFactory.getCurrentSession().createCriteria(HandScanHeader.class);
		List<Date> participationTime = new ArrayList<Date>();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			// Create HQL Between clause
			String HQL_QUERY = "select participationTime FROM HandScanRecord where handScanHeader.headerId <= :id";// and lastDate >= :date ";

			Query query = session.createQuery(HQL_QUERY);

			query.setParameter("id", id);

			//HandScanHeader hs = (HandScanHeader) query.getSingleResult();
			participationTime = (List<Date>)query.getResultList();
			
			List<Date> participationTime2 = new ArrayList<Date>();
			if(participationTime != null && participationTime.size() > 0){
				for(Date pt : participationTime){
					if(pt != null){
						participationTime2.add(pt);
					}
				}
			}else{
				return null;
			}
			
			tx.commit();
			return participationTime2;
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			session.close(); 
			return null;
		}catch(NoResultException e){
			session.close(); 
			return null;
		}
	}

	
}
