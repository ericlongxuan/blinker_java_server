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
@Table(name = "school")
@NamedQueries({
    @NamedQuery(name = "School.findAll", query = "SELECT s FROM School s"),
    @NamedQuery(name = "School.findBySchoolID", query = "SELECT s FROM School s WHERE s.schoolID = :schoolID"),
    @NamedQuery(name = "School.findBySchoolName", query = "SELECT s FROM School s WHERE s.schoolName = :schoolName"),
    @NamedQuery(name = "School.findBySchoolType", query = "SELECT s FROM School s WHERE s.schoolType = :schoolType")})
public class School implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SchoolID")
    private Integer schoolID;
    @Basic(optional = false)
    @Column(name = "SchoolName")
    private String schoolName;
    @Basic(optional = false)
    @Column(name = "SchoolType")
    private String schoolType;

    public School() {
    }

    public School(Integer schoolID) {
        this.schoolID = schoolID;
    }

    public School(Integer schoolID, String schoolName, String schoolType) {
        this.schoolID = schoolID;
        this.schoolName = schoolName;
        this.schoolType = schoolType;
    }

    public Integer getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(Integer schoolID) {
        this.schoolID = schoolID;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (schoolID != null ? schoolID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof School)) {
            return false;
        }
        School other = (School) object;
        if ((this.schoolID == null && other.schoolID != null) || (this.schoolID != null && !this.schoolID.equals(other.schoolID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.School[schoolID=" + schoolID + "]";
    }

}
