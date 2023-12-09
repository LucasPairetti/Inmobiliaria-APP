package application.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.PersistentObjectException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import application.clases.Cliente;
import application.clases.Propietario;
import application.clases.TipoDNI;
import jakarta.persistence.NoResultException;

public class PropietarioDAO {
	//Singleton
		private static PropietarioDAO instance;
		
		public static PropietarioDAO getPropietarioDAO() {
			if(instance == null) {
				instance = new PropietarioDAO();
			}
			return instance;
		}
		
		public void createPropietario(Propietario propietario) {
			// TODO Auto-generated method stub
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			
			try {
			 session.beginTransaction();
			 session.persist(propietario);
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
		
		public void updatePropietario(Propietario propietario) {
			// TODO Auto-generated method stub
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			
			try {
			session.beginTransaction();
			session.merge(propietario);
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

			
		public void deletePropietario(Propietario propietario) {
			// TODO Auto-generated method stub
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			
			try {
			session.beginTransaction();
			session.remove(propietario);
			session.getTransaction().commit();
			session.close();
			}catch(PersistentObjectException e) {
				e.getStackTrace();
				session.getTransaction().commit();
				session.close();
				System.out.println("Ha ocurrido un error");
			}
			
		}

		
		public List<Propietario> getAllPropietario() {
			// TODO Auto-generated method stub
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			
			try{
			
				session.beginTransaction();
			List<Propietario> propietarios = session
					.createQuery("SELECT a FROM propietario a", Propietario.class)
					.getResultList();
			session.getTransaction().commit();
			session.close();
			return propietarios;
			
			} catch(PersistentObjectException e) {
				e.getStackTrace();
				session.getTransaction().commit();
				session.close();
				System.out.println("Ha ocurrido un error");
				return null;
			}
			
		}

			
		public Propietario getPropietarioById(int idPropietario) {
			// TODO Auto-generated method stub
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			try {
				Propietario propietario = (Propietario) session.get(Propietario.class, idPropietario);
			
			session.getTransaction().commit();
			session.close();
			
			return propietario;
			
			    } catch (final NoResultException nre) {
			    	session.getTransaction().commit();
					session.close();
			        return null;
			    }

		}

		
			public List<Propietario> getCliente(TipoDNI tipo, String dni, String nombre, String apellido) {
				// TODO Auto-generated method stub
				
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				
				try {
				
				Query<Propietario> query = session.createQuery("SELECT * FROM PROPIETARIO WHERE"
						+ "(TIPODNI = ?1 OR ?1 IS NULL) AND"
						+ "(DNI = ?2 OR ?2 IS NULL) AND"
						+ "(NOMBRE = ?3 OR ?3 IS NULL) AND"
						+ "(APELLIDO = ?4 OR ?4 IS NULL)", Propietario.class);
				query.setParameter(1, tipo);
				query.setParameter(2, dni);
				query.setParameter(3, nombre);
				query.setParameter(4, apellido);
				
			    ArrayList<Propietario> propietarios = (ArrayList<Propietario>) query.getResultList();
			    
				session.getTransaction().commit();
				session.close();
				
				return propietarios;
				
				 } catch (final NoResultException nre) {
					 	session.getTransaction().commit();
						session.close();
						
				        return null;
				    }
				
				
			}
			
}
