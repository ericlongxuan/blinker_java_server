/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Eric
 */
@Entity
@Table(name = "affiliationtype")
@NamedQueries({
    @NamedQuery(name = "Affiliationtype.findAll", query = "SELECT a FROM Affiliationtype a"),
    @NamedQuery(name = "Affiliationtype.findByAffiliationTypeID", query = "SELECT a FROM Affiliationtype a WHERE a.affiliationTypeID = :affiliationTypeID"),
    @NamedQuery(name = "Affiliationtype.findByAffiliationProfile", query = "SELECT a FROM Affiliationtype a WHERE a.affiliationProfile = :affiliationProfile")})
public class Affiliationtype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AffiliationTypeID")
    private Integer affiliationTypeID;
    @Column(name = "AffiliationProfile")
    private String affiliationProfile;

    public Affiliationtype() {
    }

    public Affiliationtype(Integer affiliationTypeID) {
        this.affiliationTypeID = affiliationTypeID;
    }

    public Integer getAffiliationTypeID() {
        return affiliationTypeID;
    }

    public void setAffiliationTypeID(Integer affiliationTypeID) {
        this.affiliationTypeID = affiliationTypeID;
    }

    public String getAffiliationProfile() {
        return affiliationProfile;
    }

    public void setAffiliationProfile(String affiliationProfile) {
        this.affiliationProfile = affiliationProfile;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (affiliationTypeID != null ? affiliationTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Affiliationtype)) {
            return false;
        }
        Affiliationtype other = (Affiliationtype) object;
        if ((this.affiliationTypeID == null && other.affiliationTypeID != null) || (this.affiliationTypeID != null && !this.affiliationTypeID.equals(other.affiliationTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Affiliationtype[affiliationTypeID=" + affiliationTypeID + "]";
    }

}
