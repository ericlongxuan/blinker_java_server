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
@Table(name = "schooltype")
@NamedQueries({
    @NamedQuery(name = "Schooltype.findAll", query = "SELECT s FROM Schooltype s"),
    @NamedQuery(name = "Schooltype.findBySchoolTypeID", query = "SELECT s FROM Schooltype s WHERE s.schoolTypeID = :schoolTypeID"),
    @NamedQuery(name = "Schooltype.findBySchoolTypeName", query = "SELECT s FROM Schooltype s WHERE s.schoolTypeName = :schoolTypeName")})
public class Schooltype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SchoolTypeID")
    private Integer schoolTypeID;
    @Basic(optional = false)
    @Column(name = "SchoolTypeName")
    private String schoolTypeName;

    public Schooltype() {
    }

    public Schooltype(Integer schoolTypeID) {
        this.schoolTypeID = schoolTypeID;
    }

    public Schooltype(Integer schoolTypeID, String schoolTypeName) {
        this.schoolTypeID = schoolTypeID;
        this.schoolTypeName = schoolTypeName;
    }

    public Integer getSchoolTypeID() {
        return schoolTypeID;
    }

    public void setSchoolTypeID(Integer schoolTypeID) {
        this.schoolTypeID = schoolTypeID;
    }

    public String getSchoolTypeName() {
        return schoolTypeName;
    }

    public void setSchoolTypeName(String schoolTypeName) {
        this.schoolTypeName = schoolTypeName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (schoolTypeID != null ? schoolTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Schooltype)) {
            return false;
        }
        Schooltype other = (Schooltype) object;
        if ((this.schoolTypeID == null && other.schoolTypeID != null) || (this.schoolTypeID != null && !this.schoolTypeID.equals(other.schoolTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Schooltype[schoolTypeID=" + schoolTypeID + "]";
    }

}
