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
						.createQuery("SELECT a FROM Inmueble", Inmueble.class)
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

		
			public List<Inmueble> getInmueble(Provincia provincia, String localidad, String barrio, TipoInmueble tipo, int dormitorios, 
					float precioMin, float precioMax) {
				// TODO Auto-generated method stub
				
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				
				try {
				
				Query<Inmueble> query = session.createQuery("SELECT * FROM INMUEBLE WHERE"
						+ "(PROVINCIA = ?1 OR ?1 IS NULL) AND"
						+ "(LOCALIDAD = ?2 OR ?2 IS NULL) AND"
						+ "(BARRIO = ?3 OR ?3 IS NULL) AND"
						+ "(TIPO = ?4 OR ?4 IS NULL) AND"
						+ "(DORMITORIOS = ?5 OR ?5 IS NULL) AND"
						+ "(PRECIO>=?6 OR ?6 IS NULL) AND"
						+ "(PRECIO<=?7 OR ?7 IS NULL);", Inmueble.class);
				query.setParameter(1, provincia);
				query.setParameter(2, localidad);
				query.setParameter(3, barrio);
				query.setParameter(4, tipo);
				query.setParameter(5, dormitorios);
				query.setParameter(6, precioMin);
				query.setParameter(7, precioMax);
				
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
			public List<Inmueble> getInmueble(Provincia provincia, String localidad,String calle, int numero,
					String pisodpto, TipoInmueble tipoInmueble) {
				
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				
				try {
				
				Query<Inmueble> query = session.createQuery("SELECT * FROM INMUEBLE WHERE"
						+ "(PROVINCIA = ?1 OR ?1 IS NULL) AND"
						+ "(LOCALIDAD = ?2 OR ?2 IS NULL) AND"
						+ "(CALLE = ?3 OR ?3 IS NULL) AND"
						+ "(NUMERO = ?4 OR ?4 IS NULL) AND"
						+ "(PISODPTO = ?5 OR ?5 IS NULL) AND"
						+ "(TIPO = ?6 OR ?6 IS NULL)", Inmueble.class);
				query.setParameter(1, provincia);
				query.setParameter(2, localidad);
				query.setParameter(3, calle);
				query.setParameter(4, numero);
				query.setParameter(5, pisodpto);
				query.setParameter(6, tipoInmueble);
				
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

	//	}
	

}
