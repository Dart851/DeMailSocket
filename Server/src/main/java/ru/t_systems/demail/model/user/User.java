package ru.t_systems.demail.model.user;

import java.sql.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import ru.t_systems.demail.dao.account.AccountDAO;
import ru.t_systems.demail.socket.dto.AccountDTO;
import ru.t_systems.demail.socket.dto.UserDTO;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "login")
	private String login;

	@Column(name = "fist_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "birth_day")
	private Date birthDay;

	@Column(name = "sex")
	private String sex;

	@Column(name = "city")
	private String city;

	@Column(name = "password")
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") })
	// @LazyCollection(LazyCollectionOption.EXTRA)
	private Role role;

	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private Set<Account> account;

	public Set<Account> getAccounts() {
		return account;
	}

	public void setAccounts(Set<Account> account) {
		this.account = account;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
        
        public User(){}
        
        public User(UserDTO userDTO){
          setLogin(userDTO.getLogin());
          setPassword(new String(userDTO.getPassword()));
          setEmail(userDTO.getEmail());
          setLastName(userDTO.getLastName());
          setFirstName(userDTO.getFirstName());
                 
        }
        
        public UserDTO toUserDTO(){
            UserDTO user = new UserDTO();
            user.setLogin(login);
            user.setLastName(lastName);
            user.setFirstName(firstName);
            user.setEmail(email);
            Set<AccountDTO> accounts = new HashSet<AccountDTO>();
            for (Iterator<Account> it = account.iterator(); it.hasNext();) {
                Account account1 = it.next();
                accounts.add(account1.toAccountDTO());
             System.out.println("size labels "+account1.getLabel().size());
            }
            user.setAccount(accounts);
            return user;
        }

}
