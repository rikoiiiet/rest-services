package ibm.openliberty.rdp.gestsv2.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.GenerationType;

	@Entity
	@Table(name = "GESTSV2_PROFILES")
	@JsonIgnoreProperties(ignoreUnknown=true)
	@Schema(description = "Поля оссновновной записи")
	@JsonPropertyOrder({ "creationTime", "id", "type", "firstName", "lastName", "jsonValue" })
	@NamedQuery(name = "Gestsv2Record.findAll", query = "SELECT e FROM Gestsv2Record e")
	@NamedQuery(name = "Gestsv2Record.findLast5", query = "SELECT e FROM Gestsv2Record e ORDER BY SQL('? DESC FETCH FIRST 5 ROWS ONLY', e.internalRecordId)")
	@NamedQuery(name = "Gestsv2Record.findOneRecord", query = "SELECT e FROM Gestsv2Record e WHERE " + "e.id = :id")
	public class Gestsv2Record implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "RECORD_ID")
	@GeneratedValue(generator="GestsSeq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="GestsSeq",sequenceName="GESTS_SEQ", allocationSize = 1, initialValue = 1)
	private long internalRecordId;

	@Column(name = "RECORD_JSON")
	private String jsonValue;
	
	@Column(name = "CREATION_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationTime;
	
	@Schema(example = "777", required=true)
	@Column(name = "ID")
	private String id;

	@Schema(example = "Record type", required=true)
	@Column(name = "RECORD_TYPE")
	private String type;
	
	@Schema(example = "mr.")
	@Column(name = "SALUTATION")
	private String salutation;
	@Schema(example = "Garham")
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Schema(example = "Hoover")
	@Column(name = "LAST_NAME")
	private String lastName;
	@Schema(example = "manager")
	@Column(name = "TITLE")
	private String title;
	@Schema(example = "+7987654321")
	@Column(name = "PHONE_HOME")
	private String phoneHome;
	@Schema(example = "+7987654322")
	@Column(name = "PHONE_MOBILE")
	private String phoneMobile;
	@Schema(example = "+7987654323")
	@Column(name = "PHONE_WORK")
	private String phoneWork;
	@Schema(example = "+7987654324")
	@Column(name = "PHONE_OTHER")
	private String phoneOther;
	@Schema(example = "+7987654325")
	@Column(name = "PHONE_FAX")
	private String phoneFax;
	@Schema(example = "Russia")
	@Column(name = "PRIMARY_ADDRESS_COUNTRY")
	private String primaryAddressCountry;
	@Schema(example ="Moscow")
	@Column(name = "PRIMARY_ADDRESS_CITY")
	private String primaryAddressCity;
	@Schema(example = "The Moscow City")
	@Column(name = "PRIMARY_ADDRESS_STATE")
	private String primaryAddressState;
	@Schema(example = "123112")
	@Column(name = "PRIMARY_ADDRESS_POSTAL_CODE")
	private String primaryAddressPostalcode;
	@Schema(example = "Presnenskya nabereznaya building 10 block A room 515")
	@Column(name = "PRIMARY_ADDRESS_STREET")
	private String primaryAddressStreet;
	
	@Schema(example = "Germany")
	@Column(name = "ALT_ADDRESS_COUNTRY")
	private String altAddressCountry;
	@Schema(example = "Freistaat Bayern")
	@Column(name = "ALT_ADDRESS_STATE")
	private String altAddressState;
	@Schema(example = "München")
	@Column(name = "ALT_ADDRESS_CITY")
	private String altAddressCity;
	@Schema(example = "80807")
	@Column(name = "ALT_ADDRESS_POSTAL_CODE")
	private String altAddressPostalcode;
	@Schema(example = "Mies-van-der-Rohe-Straße 6")
	@Column(name = "ALT_ADDRESS_STREET")
	private String altAddressStreet;
	
	
	
	@ElementCollection
	@CollectionTable(
	name="GESTSV2_RECORD_EMAIL",
	joinColumns=@JoinColumn(name="OWNER_ID")
	)
	private List<Gestsv2Email> emailAddresses;

	@JsonIgnore
	public long getInternalRecordId() {
		return internalRecordId;
	}
	
	@JsonIgnore
	public void setInternalRecordId(long id) {
		this.internalRecordId = id;
	}
	
	public String getJsonValue() {
		return jsonValue;
	}
	
	@Schema(readOnly=true)
	public void setJsonValue(String jsonValue) {
		this.jsonValue = jsonValue;
	}
	
	public Date getCreationTime() {
		return creationTime;
	}

	@Schema(readOnly=true, pattern = "dateTime", example="yyyy-MM-dd HH:mm:ss XXX z")
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
		
	public String getId() {
	return id;
	}
	
	public void setId(String id) {
	this.id = id;
	}
	
	public String getType() {
	return type;
	}
	
	public void setType(String type) {
	this.type = type;
	}
	
	public String getSalutation() {
	return salutation;
	}
	
	public void setSalutation(String salutation) {
	this.salutation = salutation;
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
	
	public String getTitle() {
	return title;
	}
	
	public void setTitle(String title) {
	this.title = title;
	}
	
	public String getPhoneHome() {
	return phoneHome;
	}
	
	public void setPhoneHome(String phoneHome) {
	this.phoneHome = phoneHome;
	}
	
	public String getPhoneMobile() {
	return phoneMobile;
	}
	
	public void setPhoneMobile(String phoneMobile) {
	this.phoneMobile = phoneMobile;
	}
	
	public String getPhoneWork() {
	return phoneWork;
	}
	
	public void setPhoneWork(String phoneWork) {
	this.phoneWork = phoneWork;
	}
	
	public String getPhoneOther() {
	return phoneOther;
	}
	
	public void setPhoneOther(String phoneOther) {
	this.phoneOther = phoneOther;
	}
	
	public String getPhoneFax() {
	return phoneFax;
	}
	
	public void setPhoneFax(String phoneFax) {
	this.phoneFax = phoneFax;
	}
	
	public String getPrimaryAddressStreet() {
	return primaryAddressStreet;
	}
	
	public void setPrimaryAddressStreet(String primaryAddressStreet) {
	this.primaryAddressStreet = primaryAddressStreet;
	}
	
	public String getPrimaryAddressCity() {
	return primaryAddressCity;
	}
	
	public void setPrimaryAddressCity(String primaryAddressCity) {
	this.primaryAddressCity = primaryAddressCity;
	}
	
	public String getPrimaryAddressState() {
	return primaryAddressState;
	}
	
	public void setPrimaryAddressState(String primaryAddressState) {
	this.primaryAddressState = primaryAddressState;
	}
	
	public String getPrimaryAddressPostalcode() {
	return primaryAddressPostalcode;
	}
	
	public void setPrimaryAddressPostalcode(String primaryAddressPostalcode) {
	this.primaryAddressPostalcode = primaryAddressPostalcode;
	}
	
	public String getPrimaryAddressCountry() {
	return primaryAddressCountry;
	}
	
	public void setPrimaryAddressCountry(String primaryAddressCountry) {
	this.primaryAddressCountry = primaryAddressCountry;
	}
	
	public String getAltAddressStreet() {
	return altAddressStreet;
	}
	
	public void setAltAddressStreet(String altAddressStreet) {
	this.altAddressStreet = altAddressStreet;
	}
	
	public String getAltAddressCity() {
	return altAddressCity;
	}
	
	public void setAltAddressCity(String altAddressCity) {
	this.altAddressCity = altAddressCity;
	}
	
	public String getAltAddressState() {
	return altAddressState;
	}
	
	public void setAltAddressState(String altAddressState) {
	this.altAddressState = altAddressState;
	}
	
	public String getAltAddressPostalcode() {
	return altAddressPostalcode;
	}
	
	public void setAltAddressPostalcode(String altAddressPostalcode) {
	this.altAddressPostalcode = altAddressPostalcode;
	}
	
	public String getAltAddressCountry() {
	return altAddressCountry;
	}
	
	public void setAltAddressCountry(String altAddressCountry) {
	this.altAddressCountry = altAddressCountry;
	}
	
	public List<Gestsv2Email> getEmailAddresses() {
	return emailAddresses;
	}
	
	public void setEmailAddresses(List<Gestsv2Email> emailAddresses) {
	this.emailAddresses = emailAddresses;
	}
		

	public Gestsv2Record() {
	}

	public Gestsv2Record(long internalRecordId, String jsonValue, Date creationTime, String id, String type,
			String salutation, String firstName, String lastName, String title, String phoneHome, String phoneMobile,
			String phoneWork, String phoneOther, String phoneFax, String primaryAddressCountry,
			String primaryAddressCity, String primaryAddressState, String primaryAddressPostalcode,
			String primaryAddressStreet, String altAddressCountry, String altAddressState, String altAddressCity,
			String altAddressPostalcode, String altAddressStreet, List<Gestsv2Email> emailAddresses) {
		super();
		this.internalRecordId = internalRecordId;
		this.jsonValue = jsonValue;
		this.creationTime = creationTime;
		this.id = id;
		this.type = type;
		this.salutation = salutation;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.phoneHome = phoneHome;
		this.phoneMobile = phoneMobile;
		this.phoneWork = phoneWork;
		this.phoneOther = phoneOther;
		this.phoneFax = phoneFax;
		this.primaryAddressCountry = primaryAddressCountry;
		this.primaryAddressCity = primaryAddressCity;
		this.primaryAddressState = primaryAddressState;
		this.primaryAddressPostalcode = primaryAddressPostalcode;
		this.primaryAddressStreet = primaryAddressStreet;
		this.altAddressCountry = altAddressCountry;
		this.altAddressState = altAddressState;
		this.altAddressCity = altAddressCity;
		this.altAddressPostalcode = altAddressPostalcode;
		this.altAddressStreet = altAddressStreet;
		this.emailAddresses = emailAddresses;
	}
	
//	public Gestsv2Record(String name, String location, String time) {
//	this.name = name;
//	this.location = location;
//	this.time = time;
//	}
	
//	@Override
//	public int hashCode() {
//	final int prime = 31;
//	int result = 1;
//	result = prime * result + id;
//	result = prime * result + ((location == null) ? 0 : location.hashCode());
//	result = prime * result + ((name == null) ? 0 : name.hashCode());
//	result = prime * result +
//	 (int) (serialVersionUID ^ (serialVersionUID >>> 32));
//	result = prime * result + ((time == null) ? 0 : time.hashCode());
//	return result;
//	}
	
//	@Override
//	public boolean equals(Object obj) {
//	if (this == obj) {
//	return true;
//	}
//	if (obj == null) {
//	return false;
//	}
//	if (getClass() != obj.getClass()) {
//	return false;
//	}
//	Gestsv2Record other = (Gestsv2Record) obj;
//	if (location == null) {
//	if (other.location != null) {
//	return false;
//	}
//	} else if (!location.equals(other.location)) {
//	return false;
//	}
//	if (time == null) {
//	if (other.time != null) {
//	return false;
//	}
//	} else if (!time.equals(other.time)) {
//	return false;
//	}
//	if (name == null) {
//	if (other.name != null) {
//	return false;
//	}
//	} else if (!name.equals(other.name)) {
//	return false;
//	}
//	
//	return true;
//	}
//	
//	@Override
//	public String toString() {
//	return "Event [name=" + name + ", location=" + location + ", time=" + time
//	+ "]";
//	}
}

	
	
