package application.clases;

import java.sql.Date;

import jakarta.persistence.*;



@Entity
@Table(name="Venta")
public class Venta {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="idVenta")
	int id;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="propiedad_id", nullable = false, referencedColumnName="idInmueble", 
				foreignKey=@ForeignKey(name="FK_Venta_Propiedad", value=ConstraintMode.CONSTRAINT))
	Inmueble propiedad;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="cliente_id", nullable = false, referencedColumnName="idCliente", 
				foreignKey=@ForeignKey(name="FK_Venta_Cliente", value=ConstraintMode.CONSTRAINT))
	Cliente cliente;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="vendedor_id", nullable = false, referencedColumnName="idVendedor", 
				foreignKey=@ForeignKey(name="FK_Venta_Vendedor", value=ConstraintMode.CONSTRAINT))
	Vendedor vendedor;
	@Column(name="importeVenta", nullable=false)
	double importeVenta;
	
	@Column(name="fecha", nullable=false)
	Date fecha;
	
	
	public Venta() {
		super();
	}
	
	public Venta(Inmueble propiedad, Cliente cliente, Vendedor vendedor, double importeVenta, Date fecha) {
		super();
		this.propiedad = propiedad;
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.importeVenta = importeVenta;
		this.fecha = fecha;
	}

	public Integer getId() {
		return id;
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
