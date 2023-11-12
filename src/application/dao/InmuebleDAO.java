package application.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.PersistentObjectException;
import org.hibernate.Session;
import application.clases.Inmueble;
import application.clases.Localidad;
import application.clases.Propietario;
import application.clases.Provincia;
import application.clases.TipoInmueble;
import application.clases.Vendedor;
import application.clases.Venta;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class InmuebleDAO {

	//public class VentaDAO {
		
		//Singleton
		private static InmuebleDAO instance;
		
		public static InmuebleDAO getVentaDAO() {
			if(instance == null) {
				instance = new InmuebleDAO();
			}
			return instance;
		}
		
		public void createInmueble(Inmueble inmueble) {
			// TODO Auto-generated method stub
			
			try {
			 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			 session.beginTransaction();
			 session.persist(inmueble);
			 System.out.println("Created Successfully");
			 session.getTransaction().commit();
			 session.close();
			}catch(PersistentObjectException e) {
				e.getStackTrace();
			}
		}
		
		public void updateInmueble(Inmueble inmueble) {
			// TODO Auto-generated method stub
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			session.merge(inmueble);
			 System.out.println("Updated Successfully");
			
			session.getTransaction().commit();
			session.close();
			
		}

			
		public void deleteInmueble(Inmueble inmueble) {
			// TODO Auto-generated method stub
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			session.remove(inmueble);
			
			session.getTransaction().commit();
			session.close();
			
		}

		
		public List<Inmueble> getAllInmuebles() {
			// TODO Auto-generated method stub
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			List<Inmueble> inmuebles = session
					.createQuery("SELECT a FROM Inmueble", Inmueble.class)
					.getResultList();
			
			session.getTransaction().commit();
			session.close();
			
			return inmuebles;
			
		}

			
		public Inmueble getInmuebleById(int idInmueble) {
			// TODO Auto-generated method stub
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			try {
			Inmueble inmueble = (Inmueble) session.get(Inmueble.class, idInmueble);
			
			session.getTransaction().commit();
			session.close();
			
			return inmueble;
			
			    } catch (final NoResultException nre) {
			    	session.getTransaction().commit();
					session.close();
			        return null;
			    }

		}
		
		//Noimplementado
		
			public List<Inmueble> getInmueble(ArrayList<Object> argumentos) {
				// TODO Auto-generated method stub
				
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				
				try {
				
				CriteriaBuilder builder = session.getCriteriaBuilder();
			    CriteriaQuery<Inmueble> criteria = builder.createQuery(Inmueble.class);
			    Root<Inmueble> from = criteria.from(Inmueble.class);
			    criteria.select(from);
			    criteria.where(builder.equal(from.get("propietario"), ""));
			    TypedQuery<Inmueble> typed = session.createQuery(criteria);
				
			    ArrayList<Inmueble> inmuebles = (ArrayList<Inmueble>) typed.getResultList();
			    
				session.getTransaction().commit();
				session.close();
				
				return inmuebles;
				
				 } catch (final NoResultException nre) {
					 	session.getTransaction().commit();
						session.close();
						
				        return null;
				    }
				
				
			}
				
			
	//	}
	
}
