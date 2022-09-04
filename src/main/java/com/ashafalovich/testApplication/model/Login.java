package com.ashafalovich.testApplication.model;

import javax.persistence.*;

@Entity
@Table(name = "logins", schema = "public")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_id")
    private Long id;

    private String application;
    private String appAccountName;
    private boolean active;
    private String jobTitle;
    private String department;

    public Login(String application, String appAccountName, boolean active, String jobTitle, String department) {
        this.application = application;
        this.appAccountName = appAccountName;
        this.active = active;
        this.jobTitle = jobTitle;
        this.department = department;
    }

    public Login() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getAppAccountName() {
        return appAccountName;
    }

    public void setAppAccountName(String appAccountName) {
        this.appAccountName = appAccountName;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
