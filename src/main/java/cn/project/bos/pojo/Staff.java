package cn.project.bos.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="tb_user")
public class Staff {

	@Id
	private Integer id;
	@Column
	private String name;
	@Column
	private String dept;
	@Column
	private String enter;
	@Column
	private String gender;
	@Column
	private String phone;
	@Column
	private String address;
	@Column
	private String graduate;
	@Column
	private String major;
	@Column
	private String degree;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getEnter() {
		return enter;
	}
	public void setEnter(String enter) {
		this.enter = enter;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGraduate() {
		return graduate;
	}
	public void setGraduate(String graduate) {
		this.graduate = graduate;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dept=" + dept
				+ ", enter=" + enter + ", gender=" + gender + ", phone="
				+ phone + ", address=" + address + ", graduate=" + graduate
				+ ", major=" + major + ", degree=" + degree + "]";
	}
	
	
}
