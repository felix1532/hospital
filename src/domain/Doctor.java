package domain;


import java.sql.Date;
import java.text.SimpleDateFormat;


public class Doctor extends Entity {
    private Speciality speciality;
	private String firstName;
	private String lastName;
	private String patronymic;
	private Date birthdayDate;
	private Date employmentDate;
	private Integer lotNumber;
	private Double salary;
	
	public Speciality getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
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

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public Date getBirthdayDate() { return birthdayDate; }

	public void setBirthdayDate(Date birthdayDate) {this.birthdayDate = birthdayDate;}

	public Date getEmploymentDate() {return employmentDate;}

	public void setEmploymentDate(Date employmentDate) {
		this.employmentDate = employmentDate;
	}

	public Integer getLotNumber() {return lotNumber;}

	public void setLotNumber(Integer lotNumber) {
		this.lotNumber = lotNumber;
		}

	public Double getSalary() {return salary;}

	public void setSalary(Double salary) {
		java.util.Date dateOne = new java.util.Date(this.employmentDate.getTime());
		java.util.Date dateTwo = new java.util.Date();
		long difference = dateTwo.getTime() - dateOne.getTime();
		int days = (int) (difference / ( 24 * 60 * 60 * 1000));
		if(days >= 1825 && days < 3650){
			this.salary = salary * 1.05;
			getSpeciality().setSalaryExpenses(salary * 1.05);
		}else {
				if( days >= 3650 && days< 7300){
					getSpeciality().setSalaryExpenses(salary * 1.1);
					this.salary = salary * 1.1; }
				else{
					if(days >= 7300 && days <12775){
						getSpeciality().setSalaryExpenses(salary * 1.15);
						this.salary = salary * 1.15;}
				else{
					if(days >= 12775){
						getSpeciality().setSalaryExpenses(salary * 1.2);
						this.salary = salary * 1.2;
				}else{
						getSpeciality().setSalaryExpenses(salary);
						this.salary = salary;
				}
				}
			}
		}
	}

}
