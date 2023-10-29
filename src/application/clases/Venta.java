package application.clases;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
@Table(name="Venta")
public class Venta {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="idVenta")
	int id;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idPropiedad", nullable = false, referencedColumnName="idPropiedad", 
				foreignKey=@ForeignKey(name="FK_Venta_Propiedad", value=ConstraintMode.CONSTRAINT))
	Inmueble propiedad;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idCliente", nullable = false, referencedColumnName="idCliente", 
				foreignKey=@ForeignKey(name="FK_Venta_Cliente", value=ConstraintMode.CONSTRAINT))
	Cliente cliente;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idVendedor", nullable = false, referencedColumnName="idVendedor", 
				foreignKey=@ForeignKey(name="FK_Venta_Vendedor", value=ConstraintMode.CONSTRAINT))
	Vendedor vendedor;
	@Column(name="importeVenta", nullable=false)
	double importeVenta;
	@Column(name="tiempoVigencia", nullable=false)
	float tiempoVigencia; //??
	@Column(name="fecha", nullable=false)
	Date fecha;
	
	// Creo que los constructores no deberian tener el id, pero despues lo vemos
	
	public Venta(int id, Inmueble propiedad, Cliente cliente, Vendedor vendedor, double importeVenta,
			float tiempoVigencia, Date fecha) {
		super();
		this.id = id;
		this.propiedad = propiedad;
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.importeVenta = importeVenta;
		this.tiempoVigencia = tiempoVigencia;
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Inmueble getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(Inmueble propiedad) {
		this.propiedad = propiedad;
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

	public double getImporteVenta() {
		return importeVenta;
	}

	public void setImporteVenta(double importeVenta) {
		this.importeVenta = importeVenta;
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

	public boolean equals(Venta v) {
		if(v.getId()==id) {return true;}
		else {return false;}
	}
}
