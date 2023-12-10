module Inmobiliaria {
	requires javafx.controls;
    requires java.sql;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.desktop;
	requires org.hibernate.orm.core;
	requires hibernate.jpa;
	requires jakarta.persistence;
	requires itextpdf;
	requires jakarta.mail;
	
    opens application to javafx.graphics, javafx.fxml, javafx.base, org.hibernate.core, hibernate.jpa, jboss.annotations, jboss.jms.api,
    jboss.logging, jboss.transaction.api, itextpdf, jakarta.mail;
    opens application.clases to org.hibernate.orm.core;
    opens Controllers to javafx.fxml;
    
    opens dto to javafx.graphics, javafx.fxml, javafx.base, org.hibernate.core, hibernate.jpa, jboss.annotations, jboss.jms.api,
    jboss.logging, jboss.transaction.api;

}
