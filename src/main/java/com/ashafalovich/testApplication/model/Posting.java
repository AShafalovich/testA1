package com.ashafalovich.testApplication.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "postings")
public class Posting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posting_id")
    private Long id;

    private Long matDoc;
    private int item;
    private LocalDate docDate;
    private LocalDate pstngDate;
    private String materialDescription;
    private int quantity;
    private String bUn;
    private double amountLC;
    private String crcy;
    private String userName;
    private boolean authorizedDelivery = true;

    public Posting(Long matDoc, int item, LocalDate docDate, LocalDate pstngDate,
                   String materialDescription, int quantity, String bUn, double amountLC,
                   String crcy, String userName, boolean authorizedDelivery) {
        this.matDoc = matDoc;
        this.item = item;
        this.docDate = docDate;
        this.pstngDate = pstngDate;
        this.materialDescription = materialDescription;
        this.quantity = quantity;
        this.bUn = bUn;
        this.amountLC = amountLC;
        this.crcy = crcy;
        this.userName = userName;
        this.authorizedDelivery = authorizedDelivery;
    }

    public Posting() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMatDoc() {
        return matDoc;
    }

    public void setMatDoc(Long matDoc) {
        this.matDoc = matDoc;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public LocalDate getDocDate() {
        return docDate;
    }

    public void setDocDate(LocalDate docDate) {
        this.docDate = docDate;
    }

    public LocalDate getPstngDate() {
        return pstngDate;
    }

    public void setPstngDate(LocalDate pstngDate) {
        this.pstngDate = pstngDate;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getbUn() {
        return bUn;
    }

    public void setbUn(String bUn) {
        this.bUn = bUn;
    }

    public double getAmountLC() {
        return amountLC;
    }

    public void setAmountLC(double amountLC) {
        this.amountLC = amountLC;
    }

    public String getCrcy() {
        return crcy;
    }

    public void setCrcy(String crcy) {
        this.crcy = crcy;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isAuthorizedDelivery() {
        return authorizedDelivery;
    }

    public void setAuthorizedDelivery(boolean authorizedDelivery) {
        this.authorizedDelivery = authorizedDelivery;
    }
}
