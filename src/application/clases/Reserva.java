package application.clases;

import java.sql.Date;
import java.util.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;

import jakarta.persistence.*;

@Entity
@Table(name="Reserva")
public class Reserva {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="idPropietario")
	int id;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="inmueble_id", nullable = false, referencedColumnName="idInmueble", 
				foreignKey=@ForeignKey(name="FK_Reserva_Inmueble", value=ConstraintMode.CONSTRAINT))
	Inmueble inmueble;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="cliente_id", nullable = false, referencedColumnName="idCliente", 
				foreignKey=@ForeignKey(name="FK_Reserva_Cliente", value=ConstraintMode.CONSTRAINT))
	Cliente cliente;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="vendedor_id", nullable = false, referencedColumnName="idVendedor", 
				foreignKey=@ForeignKey(name="FK_Reserva_Vendedor", value=ConstraintMode.CONSTRAINT))
	Vendedor vendedor;
	@Column(name="importeReserva", nullable= false)
	double importeReserva;
	@Column(name="tiempoVigencia", nullable= false)
	float tiempoVigencia;
	@Column(name="fecha", nullable= false)
	Date fecha;
	
	
	
	public Reserva() {
		super();
	}


	public Reserva(Inmueble inmueble, Cliente cliente, Vendedor vendedor, double importeReserva, float tiempoVigencia,
			Date fecha) {
		super();
		this.inmueble = inmueble;
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.importeReserva = importeReserva;
		this.tiempoVigencia = tiempoVigencia;
		this.fecha = fecha;
	}
   

	public boolean esReservaValida() {
	    LocalDate today = LocalDate.now();
	    LocalDate fechaActual = LocalDate.now();
	    LocalDate fechaCreacion = this.fecha.toLocalDate();

	    LocalDate fechaVencimiento = fechaCreacion.plusDays((int) this.tiempoVigencia);

	    return !fechaActual.isAfter(fechaVencimiento);
	}



	public Inmueble getInmueble() {
		return inmueble;
	}



	public void setInmueble(Inmueble inmueble) {
		this.inmueble = inmueble;
	}



	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public Vendedor getVendedor() {
		return vendedor;
	}



	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}



	public double getImporteReserva() {
		return importeReserva;
	}



	public void setImporteReserva(double importeReserva) {
		this.importeReserva = importeReserva;
	}



	public float getTiempoVigencia() {
		return tiempoVigencia;
	}



	public void setTiempoVigencia(float tiempoVigencia) {
		this.tiempoVigencia = tiempoVigencia;
	}



	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public Integer getId() {
		return id;
	}



	public boolean equals(Reserva v) {
		if(v.getId().equals(id)) {return true;}
		else {return false;}
	}
}
