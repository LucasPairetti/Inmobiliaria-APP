package application.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.PersistentObjectException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import application.clases.Inmueble;
import application.clases.Localidad;
import application.clases.Propietario;
import application.clases.Provincia;
import application.clases.Reserva;
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
		
		public static InmuebleDAO getInmuebleDAO() {
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
			
			try {
			session.beginTransaction();
			session.remove(inmueble);
			session.getTransaction().commit();
			session.close();
			}catch(final NoResultException nre) {
				session.getTransaction().commit();
				session.close();
				System.out.println("Ha ocurrido un error");
		    }
			
		}

		
		public List<Inmueble> getAllInmuebles() {
			// TODO Auto-generated method stub
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			
			try {
				session.beginTransaction();
				List<Inmueble> inmuebles = session
						.createQuery("SELECT a FROM Inmueble a", Inmueble.class)
						.getResultList();
				session.getTransaction().commit();
				session.close();
				
				return inmuebles;
				
			}catch(final NoResultException nre) {
				session.getTransaction().commit();
				session.close();
				System.out.println("Ha ocurrido un error");
				return null;
		    }
			
			
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

		
			public List<Inmueble> getInmueble(Provincia provincia, String localidad, String barrio, TipoInmueble tipo, Integer dormitorios, 
					float precioMin, float precioMax) {
				// TODO Auto-generated method stub
				
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				
				try {
				
				Query<Inmueble> query = session.createQuery("SELECT a FROM Inmueble a WHERE "
						+ "(a.provincia = :provincia OR :provincia IS NULL) AND "
						+ "(a.localidad = :localidad OR :localidad IS NULL) AND "
						+ "(a.barrio = :barrio OR :barrio IS NULL) AND "
						+ "(a.tipoInmueble = :tipo OR :tipo IS NULL) AND "
						+ "(a.dormitorio = :dormitorio OR :dormitorio IS NULL) AND "
						+ "(a.precio >= :precio OR :precio IS NULL) AND "
						+ "(a.precio <= :precio OR :precio IS NULL)", Inmueble.class);
				query.setParameter("provincia", provincia);
				query.setParameter("localidad", localidad);
				query.setParameter("barrio", barrio);
				query.setParameter("tipo", tipo);
				query.setParameter("dormitorios", dormitorios);
				query.setParameter("precioMin", precioMin);
				query.setParameter("precioMax", precioMax);
				
			    ArrayList<Inmueble> inmuebles = (ArrayList<Inmueble>) query.getResultList();
			    
				session.getTransaction().commit();
				session.close();
				
				return inmuebles;
				
				 } catch (final NoResultException nre) {
					 	session.getTransaction().commit();
						session.close();
						
				        return null;
				    }
				
				
			}
			public List<Inmueble> getInmueble(Provincia provincia, String localidad,String calle, Integer numero,
					String pisodpto, TipoInmueble tipoInmueble) {
				
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				
				try {
				
				Query<Inmueble> query = session.createQuery("SELECT a FROM Inmueble a WHERE "
						+ "(a.provincia = :provincia OR :provincia IS NULL) AND "
						+ "(a.localidad = :localidad OR :localidad IS NULL) AND "
						+ "(a.calle  = :calle OR :calle IS NULL) AND "
						+ "(a.numero = :numero OR :numero IS NULL) AND "
						+ "(a.pisodpto = :pisodpto OR :pisodpto IS NULL) AND "
						+ "(a.tipoInmueble = :tipo OR :tipo IS NULL)", Inmueble.class);
				query.setParameter("provincia", provincia);
				query.setParameter("localidad", localidad);
				query.setParameter("calle", calle);
				query.setParameter("numero", numero);
				query.setParameter("pisodpto", pisodpto);
				query.setParameter("tipo", tipoInmueble);
				
			    ArrayList<Inmueble> inmuebles = (ArrayList<Inmueble>) query.getResultList();
			    
				session.getTransaction().commit();
				session.close();
				
				return inmuebles;
				
				 } catch (final NoResultException nre) {
					 	session.getTransaction().commit();
						session.close();
						
				        return null;
				    }
				
				
			}
			
			public List<Inmueble> getInmueble(Propietario propietario) {
				// TODO Auto-generated method stub
				
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				
				try {
				
				CriteriaBuilder builder = session.getCriteriaBuilder();
			    CriteriaQuery<Inmueble> criteria = builder.createQuery(Inmueble.class);
			    Root<Inmueble> from = criteria.from(Inmueble.class);
			    criteria.select(from);
			    criteria.where(builder.equal(from.get("idPropietario"), propietario.getId()));
			    TypedQuery<Inmueble> typed = session.createQuery(criteria);
			    
			    ArrayList<Inmueble> reservas = (ArrayList<Inmueble>) typed.getResultList();
			    
				session.getTransaction().commit();
				session.close();
				
				return reservas;
				
				 } catch (final NoResultException nre) {
					 	session.getTransaction().commit();
						session.close();
						
				        return null;
				    }
				
				
			}
	//	}
	

}
