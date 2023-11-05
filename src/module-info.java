module Inmobiliaria {
	requires javafx.controls;
    requires java.sql;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.desktop;
    

    opens application to javafx.graphics, javafx.fxml, javafx.base, org.hibernate.core, hibernate.jpa, jboss.annotations, jboss.jms.api,
    jboss.logging, jboss.transaction.api;
    opens Controllers to javafx.fxml;

}
