package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "productos")
public class Productos {

	@Id
	@Column(name = "idproducto")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idproducto;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "stock")
	private int stock;
//ojo la siguiente defincion va toda junta
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
			fetch=FetchType.LAZY)
	@JoinColumn(name="idcategoria")
	private Categorias categoria;
	

//ojo la siguiente definicion va toda junta
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "producto_proveedor", //OJO es donde le digo que se crea la tabla
	joinColumns = @JoinColumn(name="idproducto"), 
	inverseJoinColumns = @JoinColumn(name="idproveedor"))
	private List<Proveedores> proveedores;

	//constructores

	public Productos() {

	}

	public Productos(String nombre, String descripcion, int stock) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.stock = stock;
	}

//metodos get y set
	public Categorias getCategoria() {
		return categoria;
	}

	public void setCategoria(Categorias categoria) {
		this.categoria = categoria;
	}

	public List<Proveedores> getProvedores() {
		return proveedores;
	}

	public void setProvedores(List<Proveedores> proveedores) {
		this.proveedores = proveedores;
	}




	public int getIdproducto() {
		return idproducto;
	}

	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}


	@Override
	public String toString() {
		return "Productos [idproducto=" + idproducto + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", stock=" + stock +", categor�a="+categoria+"]";
	}

	public void addProveedor(Proveedores proveedor) {
		if (proveedores == null) {
			proveedores = new ArrayList<Proveedores>();
			//mas eficiente utilizando set
			//private Set<Proveedores> proveedores=new HashSet();
		}
		proveedores.add(proveedor);
	}


}
