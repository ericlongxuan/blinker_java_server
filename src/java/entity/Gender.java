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
@Table(name = "gender")
@NamedQueries({
    @NamedQuery(name = "Gender.findAll", query = "SELECT g FROM Gender g"),
    @NamedQuery(name = "Gender.findByGenderID", query = "SELECT g FROM Gender g WHERE g.genderID = :genderID"),
    @NamedQuery(name = "Gender.findByGenderProfile", query = "SELECT g FROM Gender g WHERE g.genderProfile = :genderProfile")})
public class Gender implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "GenderID")
    private Integer genderID;
    @Column(name = "GenderProfile")
    private String genderProfile;

    public Gender() {
    }

    public Gender(Integer genderID) {
        this.genderID = genderID;
    }

    public Integer getGenderID() {
        return genderID;
    }

    public void setGenderID(Integer genderID) {
        this.genderID = genderID;
    }

    public String getGenderProfile() {
        return genderProfile;
    }

    public void setGenderProfile(String genderProfile) {
        this.genderProfile = genderProfile;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (genderID != null ? genderID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gender)) {
            return false;
        }
        Gender other = (Gender) object;
        if ((this.genderID == null && other.genderID != null) || (this.genderID != null && !this.genderID.equals(other.genderID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Gender[genderID=" + genderID + "]";
    }

}
