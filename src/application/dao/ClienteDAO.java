package application.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.PersistentObjectException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import application.clases.Cliente;
import application.clases.Inmueble;
import application.clases.Localidad;
import application.clases.Provincia;
import application.clases.TipoDNI;
import application.clases.TipoInmueble;
import jakarta.persistence.NoResultException;

public class ClienteDAO {
	//Singleton
	private static ClienteDAO instance;
	
	public static ClienteDAO getClienteDAO() {
		if(instance == null) {
			instance = new ClienteDAO();
		}
		return instance;
	}
	
	public void createCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
		 session.beginTransaction();
		 session.persist(cliente);
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
	
	public void updateCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
		session.beginTransaction();
		session.merge(cliente);
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

		
	public void deleteCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
		session.beginTransaction();
		session.remove(cliente);
		session.getTransaction().commit();
		session.close();
		}catch(PersistentObjectException e) {
			e.getStackTrace();
			session.getTransaction().commit();
			session.close();
			System.out.println("Ha ocurrido un error");
		}
		
	}

	
	public List<Cliente> getAllClientes() {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try{
		
			session.beginTransaction();
		List<Cliente> clientes = session
				.createQuery("SELECT a FROM Cliente a", Cliente.class)
				.getResultList();
		session.getTransaction().commit();
		session.close();
		return clientes;
		
		} catch(PersistentObjectException e) {
			e.getStackTrace();
			session.getTransaction().commit();
			session.close();
			System.out.println("Ha ocurrido un error");
			return null;
		}
		
	}

		
	public Cliente getClienteById(int idCliente) {
		// TODO Auto-generated method stub
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		try {
		Cliente cliente = (Cliente) session.get(Cliente.class, idCliente);
		
		session.getTransaction().commit();
		session.close();
		
		return cliente;
		
		    } catch (final NoResultException nre) {
		    	session.getTransaction().commit();
				session.close();
		        return null;
		    }

	}

	
		public List<Cliente> getCliente(String dni,TipoDNI tipo,String nombre, String apellido) {
			// TODO Auto-generated method stub
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			try {
			
			Query<Cliente> query = session.createQuery("SELECT a FROM Cliente a WHERE"
					+ "(a.dni = :dni OR :dni IS NULL) AND "
					+ "(a.tipodni = :tipodni OR :tipodni IS NULL) AND "
					+ "(a.nombre = :nombre OR :nombre IS NULL) AND "
					+ "(a.apellido = :apellido OR :apellido IS NULL)", Cliente.class);
			query.setParameter("dni", dni);
			query.setParameter("tipodni", tipo);
			query.setParameter("nombre", nombre);
			query.setParameter("apellido", apellido);
			
		    ArrayList<Cliente> clientes = (ArrayList<Cliente>) query.getResultList();
		    
			session.getTransaction().commit();
			session.close();
			
			return clientes;
			
			 } catch (final NoResultException nre) {
				 	session.getTransaction().commit();
					session.close();
					
			        return null;
			    }
			
			
		}
}
