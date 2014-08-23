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
@Table(name = "affiliation")
@NamedQueries({
    @NamedQuery(name = "Affiliation.findAll", query = "SELECT a FROM Affiliation a"),
    @NamedQuery(name = "Affiliation.findByAffiliationID", query = "SELECT a FROM Affiliation a WHERE a.affiliationID = :affiliationID"),
    @NamedQuery(name = "Affiliation.findByStatus", query = "SELECT a FROM Affiliation a WHERE a.status = :status"),
    @NamedQuery(name = "Affiliation.findByAffiliationName", query = "SELECT a FROM Affiliation a WHERE a.affiliationName = :affiliationName"),
    @NamedQuery(name = "Affiliation.findByAffiliationSignature", query = "SELECT a FROM Affiliation a WHERE a.affiliationSignature = :affiliationSignature")})
public class Affiliation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AffiliationID")
    private Integer affiliationID;
    @Basic(optional = false)
    @Column(name = "Status")
    private boolean status;
    @Basic(optional = false)
    @Column(name = "AffiliationName")
    private String affiliationName;
    @Column(name = "AffiliationSignature")
    private String affiliationSignature;

    public Affiliation() {
    }

    public Affiliation(Integer affiliationID) {
        this.affiliationID = affiliationID;
    }

    public Affiliation(Integer affiliationID, boolean status, String affiliationName) {
        this.affiliationID = affiliationID;
        this.status = status;
        this.affiliationName = affiliationName;
    }

    public Integer getAffiliationID() {
        return affiliationID;
    }

    public void setAffiliationID(Integer affiliationID) {
        this.affiliationID = affiliationID;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAffiliationName() {
        return affiliationName;
    }

    public void setAffiliationName(String affiliationName) {
        this.affiliationName = affiliationName;
    }

    public String getAffiliationSignature() {
        return affiliationSignature;
    }

    public void setAffiliationSignature(String affiliationSignature) {
        this.affiliationSignature = affiliationSignature;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (affiliationID != null ? affiliationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Affiliation)) {
            return false;
        }
        Affiliation other = (Affiliation) object;
        if ((this.affiliationID == null && other.affiliationID != null) || (this.affiliationID != null && !this.affiliationID.equals(other.affiliationID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Affiliation[affiliationID=" + affiliationID + "]";
    }

}
