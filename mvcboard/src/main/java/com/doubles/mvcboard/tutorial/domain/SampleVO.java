package com.doubles.mvcboard.tutorial.domain;

public class SampleVO {

    private Integer sampleNo;
    private String firstName;
    private String lastName;

    public Integer getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(Integer sampleNo) {
        this.sampleNo = sampleNo;
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

    @Override
    public String toString() {
        return "SampleVO{" +
                "sampleNo=" + sampleNo +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
