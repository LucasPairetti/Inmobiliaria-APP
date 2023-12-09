package application.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.PersistentObjectException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import application.clases.Cliente;
import application.clases.Inmueble;
import application.clases.Provincia;
import application.clases.Reserva;
import application.clases.TipoInmueble;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class ReservaDAO {

	private static ReservaDAO instance;
	
	public static ReservaDAO getReservaDAO() {
		if(instance == null) {
			instance = new ReservaDAO();
		}
		return instance;
	}
	
	public void createReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
		 session.beginTransaction();
		 session.persist(reserva);
		 System.out.println("Created Successfully");
		 session.getTransaction().commit();
		session.close();
		}catch(PersistentObjectException e) {
			e.getStackTrace();
			session.getTransaction().commit();
			session.close();
			System.out.println("Ha ocurrido un error");
		}
		
	}
	
	public void updateReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
		session.beginTransaction();
		session.merge(reserva);
		System.out.println("Updated Successfully");
		session.getTransaction().commit();
		session.close();
		
		}catch(PersistentObjectException e) {
			e.getStackTrace();
			session.getTransaction().commit();
			session.close();
			System.out.println("Ha ocurrido un error");
		}
		
	}

		
	public void DeleteReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
		session.beginTransaction();
		session.remove(reserva);
		session.getTransaction().commit();
		session.close();
		}catch(PersistentObjectException e) {
			e.getStackTrace();
			session.getTransaction().commit();
			session.close();
			System.out.println("Ha ocurrido un error");
		}
		
	}

	
	public List<Reserva> getAllReservas() {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try{
		
			session.beginTransaction();
		List<Reserva> reservas = session
				.createQuery("SELECT a FROM reserva a", Reserva.class)
				.getResultList();
		session.getTransaction().commit();
		session.close();
		return reservas;
		
		} catch(PersistentObjectException e) {
			e.getStackTrace();
			session.getTransaction().commit();
			session.close();
			System.out.println("Ha ocurrido un error");
			return null;
		}
		
	}

		
	public Reserva getReservaById(int idReserva) {
		// TODO Auto-generated method stub
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		try {
		Reserva reserva = (Reserva) session.get(Reserva.class, idReserva);
		
		session.getTransaction().commit();
		session.close();
		
		return reserva;
		
		    } catch (final NoResultException nre) {
		    	session.getTransaction().commit();
				session.close();
		        return null;
		    }

	}
	public List<Reserva> getReservasByInmueble(Inmueble inmueble) {
		// TODO Auto-generated method stub
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		try {
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
	    CriteriaQuery<Reserva> criteria = builder.createQuery(Reserva.class);
	    Root<Reserva> from = criteria.from(Reserva.class);
	    criteria.select(from);
	    criteria.where(builder.equal(from.get("inmueble_id"), inmueble.getId()));
	    TypedQuery<Reserva> typed = session.createQuery(criteria);
	    
	    ArrayList<Reserva> reservas = (ArrayList<Reserva>) typed.getResultList();
	    
		session.getTransaction().commit();
		session.close();
		
		return reservas;
		
		 } catch (final NoResultException nre) {
			 	session.getTransaction().commit();
				session.close();
				
		        return null;
		    }
		
		
	}
	
		public List<Reserva> getReservasByCliente(Cliente cliente) {
			// TODO Auto-generated method stub
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			try {
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
		    CriteriaQuery<Reserva> criteria = builder.createQuery(Reserva.class);
		    Root<Reserva> from = criteria.from(Reserva.class);
		    criteria.select(from);
		    criteria.where(builder.equal(from.get("cliente_id"), cliente.getId()));
		    TypedQuery<Reserva> typed = session.createQuery(criteria);
		    
		    ArrayList<Reserva> reservas = (ArrayList<Reserva>) typed.getResultList();
		    
			session.getTransaction().commit();
			session.close();
			
			return reservas;
			
			 } catch (final NoResultException nre) {
				 	session.getTransaction().commit();
					session.close();
					
			        return null;
			    }
			
			
		}
	
}
