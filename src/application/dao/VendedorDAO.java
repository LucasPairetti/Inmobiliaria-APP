package application.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.PersistentObjectException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import application.clases.Cliente;
import application.clases.TipoDNI;
import application.clases.Vendedor;
import jakarta.persistence.NoResultException;

public class VendedorDAO {
	//Singleton
		private static VendedorDAO instance;
		
		public static VendedorDAO getVendedorDAO() {
			if(instance == null) {
				instance = new VendedorDAO();
			}
			return instance;
		}
		
		public void createVendedor(Vendedor vendedor) {
			// TODO Auto-generated method stub
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			
			try {
			 session.beginTransaction();
			 session.persist(vendedor);
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
		
		public void updateVendedor(Vendedor vendedor) {
			// TODO Auto-generated method stub
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			
			try {
			session.beginTransaction();
			session.merge(vendedor);
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

			
		public void deleteVendedor(Vendedor vendedor) {
			// TODO Auto-generated method stub
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			
			try {
			session.beginTransaction();
			session.remove(vendedor);
			session.getTransaction().commit();
			session.close();
			}catch(PersistentObjectException e) {
				e.getStackTrace();
				session.getTransaction().commit();
				session.close();
				System.out.println("Ha ocurrido un error");
			}
			
		}

		
		public List<Vendedor> getAllVendedor() {
			// TODO Auto-generated method stub
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			
			try{
			
				session.beginTransaction();
			List<Vendedor> vendedores = session
					.createQuery("SELECT a FROM Vendedor a", Vendedor.class)
					.getResultList();
			session.getTransaction().commit();
			session.close();
			return vendedores;
			
			} catch(PersistentObjectException e) {
				e.getStackTrace();
				session.getTransaction().commit();
				session.close();
				System.out.println("Ha ocurrido un error");
				return null;
			}
			
		}

			
		public Vendedor getVendedorById(int idVendedor) {
			// TODO Auto-generated method stub
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			try {
			Vendedor vendedor = (Vendedor) session.get(Vendedor.class, idVendedor);
			
			session.getTransaction().commit();
			session.close();
			
			return vendedor;
			
			    } catch (final NoResultException nre) {
			    	session.getTransaction().commit();
					session.close();
			        return null;
			    }

		}
		public Vendedor getVendedor(TipoDNI tipodni,String dni) {
			// TODO Auto-generated method stub
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			try {
			
			Query<Vendedor> query = session.createQuery("SELECT a FROM Vendedor a WHERE "
					+ "(a.dni = :dni) AND "
					+ "(a.tipodni = :tipodni)", Vendedor.class);
			query.setParameter("dni", dni);
			query.setParameter("tipodni", tipodni);
			
		    Vendedor vendedor = query.getSingleResult();
		    
			session.getTransaction().commit();
			session.close();
			
			return vendedor;
			
			 } catch (final NoResultException nre) {
				 	session.getTransaction().commit();
					session.close();
					
			        return null;
			    }
			
			
		}
		
			public Vendedor validarVendedor(String dni, String nombre, String apellido, String clave) {
				// TODO Auto-generated method stub
				
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				
				try {
				
				Query<Vendedor> query = session.createQuery("SELECT a FROM Vendedor a WHERE "
						+ "(DNI = :dni) AND "
						+ "(NOMBRE = :nombre) AND "
						+ "(APELLIDO = :apellido) AND "
						+ "(CLAVE = :clave)", Vendedor.class);
				query.setParameter(1, dni);
				query.setParameter(2, nombre);
				query.setParameter(3, apellido);
				query.setParameter(4, clave);
				
			    Vendedor vendedor = query.getSingleResult();
			    
				session.getTransaction().commit();
				session.close();
				
				return vendedor;
				
				 } catch (final NoResultException nre) {
					 	session.getTransaction().commit();
						session.close();
						
				        return null;
				    }
				
				
			}
}
