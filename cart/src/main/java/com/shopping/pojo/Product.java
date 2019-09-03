package com.shopping.pojo;

public class Product  implements java.io.Serializable {
	private String pid;
	private String pname;
	private String punit;
	private int quantity;
	private double cost;
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPunit() {
		return punit;
	}
	public void setPunit(String punit) {
		this.punit = punit;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public Product(String pid, String pname, String punit, double cost,int qutity) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.punit = punit;
		this.cost = cost;
		this.quantity = quantity;
	}
	public Product() {
		
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", punit=" + punit + ", quantity=" + quantity + ", cost="
				+ cost + "]";
	}
	
	
	
	

}
