package com.saber.samplejavaee.model.query;

import jakarta.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "person")
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstName",length = 70)
    @NotBlank(message = "firstName is Required")
    private String firstName;
    @Column(name = "lastName",length = 90)
    @NotBlank(message = "lastName is Required")
    private String lastName;
    @Column(name = "age")
    @NotNull(message = "age is Required")
    @Positive(message = "age must be grate than 0")
    private Integer age;
    @Column(name = "email",length = 40)
    @NotNull(message = "email is Required")
    @Email(message = "email is invalid")
    private String email;
    @Column(name = "nationalCode",length = 10)
    @NotBlank(message = "nationalCode is Required")
    @Size(min = 10,max = 10,message = "nationalCode must be 10 digit")
    @Pattern(regexp = "\\d{10}" ,message = "nationalCode invalid")
    private String nationalCode;
    @NotBlank(message = "mobile is Required")
    @Size(min = 11,max = 11,message = "mobile must be 11 digit")
    @Pattern(regexp = "09[0-9]{9}" ,message = "mobile invalid")
    @Column(name = "mobile",length = 11)
    private String mobile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(age, person.age) && Objects.equals(email, person.email) && Objects.equals(nationalCode, person.nationalCode) && Objects.equals(mobile, person.mobile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, email, nationalCode, mobile);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
