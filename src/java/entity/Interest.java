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
@Table(name = "interest")
@NamedQueries({
    @NamedQuery(name = "Interest.findAll", query = "SELECT i FROM Interest i"),
    @NamedQuery(name = "Interest.findByInterestID", query = "SELECT i FROM Interest i WHERE i.interestID = :interestID"),
    @NamedQuery(name = "Interest.findByInterestName", query = "SELECT i FROM Interest i WHERE i.interestName = :interestName")})
public class Interest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "InterestID")
    private Integer interestID;
    @Column(name = "InterestName")
    private String interestName;

    public Interest() {
    }

    public Interest(Integer interestID) {
        this.interestID = interestID;
    }

    public Integer getInterestID() {
        return interestID;
    }

    public void setInterestID(Integer interestID) {
        this.interestID = interestID;
    }

    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (interestID != null ? interestID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Interest)) {
            return false;
        }
        Interest other = (Interest) object;
        if ((this.interestID == null && other.interestID != null) || (this.interestID != null && !this.interestID.equals(other.interestID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Interest[interestID=" + interestID + "]";
    }

}
