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

import com.upa.web.model.HandScanHeader;
import com.upa.web.model.HandScanRecord;
import com.upa.web.model.TestSequence;

@Repository
public class HandScanDaoImpl implements HandScanDao{

	@Autowired
	private SessionFactory sessionFactory;

	public HandScanDaoImpl(){}

	public HandScanDaoImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}

	@Override
	public String saveHandscan(HandScanRecord handscan){
		sessionFactory.getCurrentSession().saveOrUpdate(handscan);
		return "SUCESS";
	}
	
	@Override
	public String saveHandscanHeader(HandScanHeader hs) {
		sessionFactory.getCurrentSession().saveOrUpdate(hs);
		return "SUCCESS";
	}

	@Override
	public String getCurrentDate() {
        Session session = sessionFactory.openSession();
        SQLQuery sqlQuery = session.createSQLQuery("select now()  as sysdate from dual");
        String result = sqlQuery.getSingleResult().toString();
        return sqlQuery.toString();
	}

	@Override
	public String getTestSequence(TestSequence ts) {
		sessionFactory.getCurrentSession().save(ts);
		return "SUCCESS";
	}

	@SuppressWarnings("unchecked")
	@Override
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
	@Override
	public HandScanHeader getHandScanOfTerm(Date date) {
		//List<HandScanHeader> hsList = (List<HandScanHeader>) sessionFactory.getCurrentSession().createCriteria(HandScanHeader.class);
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			// Create HQL Between clause
			String HQL_QUERY = "FROM HandScanHeader where firstDate <= :date";// and lastDate >= :date ";

			Query query = session.createQuery(HQL_QUERY);

			query.setParameter("date", date, TemporalType.DATE);

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

	
	@SuppressWarnings("unchecked")
	@Override
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

	@Override
	public List<Date> getParticipateTime(Long id) {
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
			return null;
		}catch(NoResultException e){
			//e.printStackTrace();
			return null;
		}finally {
			session.close(); 
		}
	}

	
}
