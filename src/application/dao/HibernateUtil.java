package application.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import application.clases.*;

public class HibernateUtil {
	
	public static SessionFactory sessionFactory;
	
	// singleton
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			/*Configuration cfg = new Configuration();
			
			cfg.configure("hibernate.cfg.xml");
			cfg.addAnnotatedClass(Cliente.class );
			cfg.addAnnotatedClass( Inmueble.class );
			cfg.addAnnotatedClass( Propietario.class );
			cfg.addAnnotatedClass( Reserva.class );
			cfg.addAnnotatedClass( Vendedor.class );
			cfg.addAnnotatedClass( Venta.class );*/
			
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.configure()
					.build();

			MetadataSources sources = new MetadataSources( standardRegistry );
			sources.addAnnotatedClass( application.clases.Cliente.class );
			sources.addAnnotatedClass( application.clases.Inmueble.class );
			sources.addAnnotatedClass( application.clases.Propietario.class );
			sources.addAnnotatedClass( application.clases.Reserva.class );
			sources.addAnnotatedClass( application.clases.Vendedor.class );
			sources.addAnnotatedClass( application.clases.Venta.class );
				
			Metadata metadata = sources.buildMetadata();
				    
			SessionFactoryBuilder sessionFactoryBuilder = metadata.getSessionFactoryBuilder();
			
			sessionFactory = sessionFactoryBuilder.build();
			
		}	
		
		return sessionFactory;
	
	}
	

}
