package application.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import application.clases.*;

public class HibernateUtil {
	
	public static SessionFactory sessionFactory;
	
	// singleton
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			/*Configuration cfg = new Configuration();
			
			cfg.configure("hibernate.cfg.xml");
			cfg.addAnnotatedClass(Puesto.class);
			cfg.addAnnotatedClass(Competencia.class);
			cfg.addAnnotatedClass(ItemCompetencia.class);*/
			
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.configure()
					.build();

			MetadataSources sources = new MetadataSources( standardRegistry );
			sources.addAnnotatedClass( Cliente.class );
			sources.addAnnotatedClass( Inmueble.class );
			sources.addAnnotatedClass( Propietario.class );
			sources.addAnnotatedClass( Reserva.class );
			sources.addAnnotatedClass( Vendedor.class );
			sources.addAnnotatedClass( Venta.class );
				
			Metadata metadata = sources.buildMetadata();
				    
			SessionFactoryBuilder sessionFactoryBuilder = metadata.getSessionFactoryBuilder();
			
			sessionFactory = sessionFactoryBuilder.build();
			
		}	
		
		return sessionFactory;
	
	}
	

}
