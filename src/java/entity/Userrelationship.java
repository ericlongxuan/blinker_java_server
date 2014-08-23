/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Eric
 */
@Entity
@Table(name = "userrelationship")
@NamedQueries({
    @NamedQuery(name = "Userrelationship.findAll", query = "SELECT u FROM Userrelationship u"),
    @NamedQuery(name = "Userrelationship.findByUserID", query = "SELECT u FROM Userrelationship u WHERE u.userID = :userID"),
    @NamedQuery(name = "Userrelationship.findByAffiliationID", query = "SELECT u FROM Userrelationship u WHERE u.affiliationID = :affiliationID"),
    @NamedQuery(name = "Userrelationship.findByAffiliationType", query = "SELECT u FROM Userrelationship u WHERE u.affiliationType = :affiliationType"),
    @NamedQuery(name = "Userrelationship.findByAcquaintanceDate", query = "SELECT u FROM Userrelationship u WHERE u.acquaintanceDate = :acquaintanceDate"),
    @NamedQuery(name = "Userrelationship.findByTelNumberVisibility", query = "SELECT u FROM Userrelationship u WHERE u.telNumberVisibility = :telNumberVisibility"),
    @NamedQuery(name = "Userrelationship.findByEmailAddressVisibility", query = "SELECT u FROM Userrelationship u WHERE u.emailAddressVisibility = :emailAddressVisibility"),
    @NamedQuery(name = "Userrelationship.findByIdolVisibility", query = "SELECT u FROM Userrelationship u WHERE u.idolVisibility = :idolVisibility"),
    @NamedQuery(name = "Userrelationship.findByInterestVisibility", query = "SELECT u FROM Userrelationship u WHERE u.interestVisibility = :interestVisibility"),
    @NamedQuery(name = "Userrelationship.findByLookingForVisibility", query = "SELECT u FROM Userrelationship u WHERE u.lookingForVisibility = :lookingForVisibility"),
    @NamedQuery(name = "Userrelationship.findBySchoolDotsVisibility", query = "SELECT u FROM Userrelationship u WHERE u.schoolDotsVisibility = :schoolDotsVisibility"),
    @NamedQuery(name = "Userrelationship.findByWorkDotsVisibility", query = "SELECT u FROM Userrelationship u WHERE u.workDotsVisibility = :workDotsVisibility"),
    @NamedQuery(name = "Userrelationship.findByBirthdayVisibility", query = "SELECT u FROM Userrelationship u WHERE u.birthdayVisibility = :birthdayVisibility")})
public class Userrelationship implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "UserID")
    private Integer userID;
    @Basic(optional = false)
    @Column(name = "AffiliationID")
    private int affiliationID;
    @Column(name = "AffiliationType")
    private String affiliationType;
    @Column(name = "AcquaintanceDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date acquaintanceDate;
    @Column(name = "TelNumberVisibility")
    private Boolean telNumberVisibility;
    @Column(name = "EmailAddressVisibility")
    private Boolean emailAddressVisibility;
    @Column(name = "IdolVisibility")
    private Boolean idolVisibility;
    @Column(name = "InterestVisibility")
    private Boolean interestVisibility;
    @Column(name = "LookingForVisibility")
    private Boolean lookingForVisibility;
    @Column(name = "SchoolDotsVisibility")
    private Boolean schoolDotsVisibility;
    @Column(name = "WorkDotsVisibility")
    private Boolean workDotsVisibility;
    @Column(name = "BirthdayVisibility")
    private Boolean birthdayVisibility;

    public Userrelationship() {
    }

    public Userrelationship(Integer userID) {
        this.userID = userID;
    }

    public Userrelationship(Integer userID, int affiliationID) {
        this.userID = userID;
        this.affiliationID = affiliationID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public int getAffiliationID() {
        return affiliationID;
    }

    public void setAffiliationID(int affiliationID) {
        this.affiliationID = affiliationID;
    }

    public String getAffiliationType() {
        return affiliationType;
    }

    public void setAffiliationType(String affiliationType) {
        this.affiliationType = affiliationType;
    }

    public Date getAcquaintanceDate() {
        return acquaintanceDate;
    }

    public void setAcquaintanceDate(Date acquaintanceDate) {
        this.acquaintanceDate = acquaintanceDate;
    }

    public Boolean getTelNumberVisibility() {
        return telNumberVisibility;
    }

    public void setTelNumberVisibility(Boolean telNumberVisibility) {
        this.telNumberVisibility = telNumberVisibility;
    }

    public Boolean getEmailAddressVisibility() {
        return emailAddressVisibility;
    }

    public void setEmailAddressVisibility(Boolean emailAddressVisibility) {
        this.emailAddressVisibility = emailAddressVisibility;
    }

    public Boolean getIdolVisibility() {
        return idolVisibility;
    }

    public void setIdolVisibility(Boolean idolVisibility) {
        this.idolVisibility = idolVisibility;
    }

    public Boolean getInterestVisibility() {
        return interestVisibility;
    }

    public void setInterestVisibility(Boolean interestVisibility) {
        this.interestVisibility = interestVisibility;
    }

    public Boolean getLookingForVisibility() {
        return lookingForVisibility;
    }

    public void setLookingForVisibility(Boolean lookingForVisibility) {
        this.lookingForVisibility = lookingForVisibility;
    }

    public Boolean getSchoolDotsVisibility() {
        return schoolDotsVisibility;
    }

    public void setSchoolDotsVisibility(Boolean schoolDotsVisibility) {
        this.schoolDotsVisibility = schoolDotsVisibility;
    }

    public Boolean getWorkDotsVisibility() {
        return workDotsVisibility;
    }

    public void setWorkDotsVisibility(Boolean workDotsVisibility) {
        this.workDotsVisibility = workDotsVisibility;
    }

    public Boolean getBirthdayVisibility() {
        return birthdayVisibility;
    }

    public void setBirthdayVisibility(Boolean birthdayVisibility) {
        this.birthdayVisibility = birthdayVisibility;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userrelationship)) {
            return false;
        }
        Userrelationship other = (Userrelationship) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Userrelationship[userID=" + userID + "]";
    }

}
