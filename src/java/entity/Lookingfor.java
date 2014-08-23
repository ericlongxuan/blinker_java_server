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
@Table(name = "lookingfor")
@NamedQueries({
    @NamedQuery(name = "Lookingfor.findAll", query = "SELECT l FROM Lookingfor l"),
    @NamedQuery(name = "Lookingfor.findByLookingForID", query = "SELECT l FROM Lookingfor l WHERE l.lookingForID = :lookingForID"),
    @NamedQuery(name = "Lookingfor.findByLookingForProfile", query = "SELECT l FROM Lookingfor l WHERE l.lookingForProfile = :lookingForProfile")})
public class Lookingfor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "LookingForID")
    private Integer lookingForID;
    @Column(name = "LookingForProfile")
    private String lookingForProfile;

    public Lookingfor() {
    }

    public Lookingfor(Integer lookingForID) {
        this.lookingForID = lookingForID;
    }

    public Integer getLookingForID() {
        return lookingForID;
    }

    public void setLookingForID(Integer lookingForID) {
        this.lookingForID = lookingForID;
    }

    public String getLookingForProfile() {
        return lookingForProfile;
    }

    public void setLookingForProfile(String lookingForProfile) {
        this.lookingForProfile = lookingForProfile;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lookingForID != null ? lookingForID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lookingfor)) {
            return false;
        }
        Lookingfor other = (Lookingfor) object;
        if ((this.lookingForID == null && other.lookingForID != null) || (this.lookingForID != null && !this.lookingForID.equals(other.lookingForID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Lookingfor[lookingForID=" + lookingForID + "]";
    }

}
