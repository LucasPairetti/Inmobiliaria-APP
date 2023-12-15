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
					.createQuery("SELECT a FROM Propietario a", Propietario.class)
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

		
			public List<Propietario> getPropietario(TipoDNI tipo, String dni, String nombre, String apellido) {
				// TODO Auto-generated method stub
				
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				
				try {
				
				Query<Propietario> query = session.createQuery("SELECT a FROM Propietario a WHERE"
						+ "(a.tipodni = :tipodni OR :tipodni IS NULL) AND "
						+ "(a.dni = :dni OR :dni IS NULL) AND "
						+ "(a.nombre = :nombre OR :nombre IS NULL) AND "
						+ "(a.apellido = :apellido OR :apellido IS NULL)", Propietario.class);
				query.setParameter("tipodni", tipo);
				query.setParameter("dni", dni);
				query.setParameter("nombre", nombre);
				query.setParameter("apellido", apellido);
				
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
