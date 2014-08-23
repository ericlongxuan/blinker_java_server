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
@Table(name = "idol")
@NamedQueries({
    @NamedQuery(name = "Idol.findAll", query = "SELECT i FROM Idol i"),
    @NamedQuery(name = "Idol.findByIdolName", query = "SELECT i FROM Idol i WHERE i.idolName = :idolName")})
public class Idol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Idol_Name")
    private String idolName;

    public Idol() {
    }

    public Idol(String idolName) {
        this.idolName = idolName;
    }

    public String getIdolName() {
        return idolName;
    }

    public void setIdolName(String idolName) {
        this.idolName = idolName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idolName != null ? idolName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Idol)) {
            return false;
        }
        Idol other = (Idol) object;
        if ((this.idolName == null && other.idolName != null) || (this.idolName != null && !this.idolName.equals(other.idolName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Idol[idolName=" + idolName + "]";
    }

}
