package application.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.PersistentObjectException;
import org.hibernate.Session;
import application.clases.Vendedor;
import application.clases.Venta;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class VentaDAO {
	
	//Singleton
	private static VentaDAO instance;
	
	public static VentaDAO getVentaDAO() {
		if(instance == null) {
			instance = new VentaDAO();
		}
		return instance;
	}
	
	public void createVenta(Venta venta) {
		// TODO Auto-generated method stub
		
		try {
		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		 session.beginTransaction();
		 session.persist(venta);
		 System.out.println("Created Successfully");
		 session.getTransaction().commit();
		 session.close();
		}catch(PersistentObjectException e) {
			e.getStackTrace();
		}
	}
	
	public void updateVenta(Venta venta) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		session.merge(venta);
		 System.out.println("Updated Successfully");
		
		session.getTransaction().commit();
		session.close();
		
	}

		
	public void deleteVenta(Venta venta) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		session.remove(venta);
		
		session.getTransaction().commit();
		session.close();
		
	}

	
	public List<Venta> getAllVentas() {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		List<Venta> ventas = session
				.createQuery("SELECT a FROM venta a", Venta.class)
				.getResultList();
		
		session.getTransaction().commit();
		session.close();
		
		return ventas;
		
	}

		
	public Venta getVentaById(int idVenta) {
		// TODO Auto-generated method stub
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		try {
		Venta venta = (Venta) session.get(Venta.class, idVenta);
		
		session.getTransaction().commit();
		session.close();
		
		return venta;
		
		    } catch (final NoResultException nre) {
		    	session.getTransaction().commit();
				session.close();
		        return null;
		    }

	}

		public List<Venta> getVentasByVendedor(Vendedor vendedor) {
			// TODO Auto-generated method stub
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			try {
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
		    CriteriaQuery<Venta> criteria = builder.createQuery(Venta.class);
		    Root<Venta> from = criteria.from(Venta.class);
		    criteria.select(from);
		    criteria.where(builder.equal(from.get("vendedor_id"), vendedor.getId()));
		    TypedQuery<Venta> typed = session.createQuery(criteria);
			
		    ArrayList<Venta> ventas = (ArrayList<Venta>) typed.getResultList();
		    
			session.getTransaction().commit();
			session.close();
			
			return ventas;
			
			 } catch (final NoResultException nre) {
				 	session.getTransaction().commit();
					session.close();
					
			        return null;
			    }
			
			
		}
			
		
	}
