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
@Table(name = "footprintprofile")
@NamedQueries({
    @NamedQuery(name = "Footprintprofile.findAll", query = "SELECT f FROM Footprintprofile f"),
    @NamedQuery(name = "Footprintprofile.findByFootprintUserID", query = "SELECT f FROM Footprintprofile f WHERE f.footprintUserID = :footprintUserID"),
    @NamedQuery(name = "Footprintprofile.findByFootprintID", query = "SELECT f FROM Footprintprofile f WHERE f.footprintID = :footprintID"),
    @NamedQuery(name = "Footprintprofile.findByFootprintDate", query = "SELECT f FROM Footprintprofile f WHERE f.footprintDate = :footprintDate"),
    @NamedQuery(name = "Footprintprofile.findByFootprintTimes", query = "SELECT f FROM Footprintprofile f WHERE f.footprintTimes = :footprintTimes"),
    @NamedQuery(name = "Footprintprofile.findByFootprintPictureSmallURL", query = "SELECT f FROM Footprintprofile f WHERE f.footprintPictureSmallURL = :footprintPictureSmallURL"),
    @NamedQuery(name = "Footprintprofile.findByFootprintPictureURL", query = "SELECT f FROM Footprintprofile f WHERE f.footprintPictureURL = :footprintPictureURL"),
    @NamedQuery(name = "Footprintprofile.findByFootprintPictureBigURL", query = "SELECT f FROM Footprintprofile f WHERE f.footprintPictureBigURL = :footprintPictureBigURL"),
    @NamedQuery(name = "Footprintprofile.findByFootprintDescription", query = "SELECT f FROM Footprintprofile f WHERE f.footprintDescription = :footprintDescription")})
public class Footprintprofile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "FootprintUserID")
    private int footprintUserID;
    @Id
    @Basic(optional = false)
    @Column(name = "FootprintID")
    private Integer footprintID;
    @Basic(optional = false)
    @Column(name = "FootprintDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date footprintDate;
    @Column(name = "FootprintTimes")
    private Integer footprintTimes;
    @Column(name = "FootprintPictureSmallURL")
    private String footprintPictureSmallURL;
    @Column(name = "FootprintPictureURL")
    private String footprintPictureURL;
    @Column(name = "FootprintPictureBigURL")
    private String footprintPictureBigURL;
    @Basic(optional = false)
    @Column(name = "FootprintDescription")
    private String footprintDescription;

    public Footprintprofile() {
    }

    public Footprintprofile(Integer footprintID) {
        this.footprintID = footprintID;
    }

    public Footprintprofile(Integer footprintID, int footprintUserID, Date footprintDate, String footprintDescription) {
        this.footprintID = footprintID;
        this.footprintUserID = footprintUserID;
        this.footprintDate = footprintDate;
        this.footprintDescription = footprintDescription;
    }

    public int getFootprintUserID() {
        return footprintUserID;
    }

    public void setFootprintUserID(int footprintUserID) {
        this.footprintUserID = footprintUserID;
    }

    public Integer getFootprintID() {
        return footprintID;
    }

    public void setFootprintID(Integer footprintID) {
        this.footprintID = footprintID;
    }

    public Date getFootprintDate() {
        return footprintDate;
    }

    public void setFootprintDate(Date footprintDate) {
        this.footprintDate = footprintDate;
    }

    public Integer getFootprintTimes() {
        return footprintTimes;
    }

    public void setFootprintTimes(Integer footprintTimes) {
        this.footprintTimes = footprintTimes;
    }

    public String getFootprintPictureSmallURL() {
        return footprintPictureSmallURL;
    }

    public void setFootprintPictureSmallURL(String footprintPictureSmallURL) {
        this.footprintPictureSmallURL = footprintPictureSmallURL;
    }

    public String getFootprintPictureURL() {
        return footprintPictureURL;
    }

    public void setFootprintPictureURL(String footprintPictureURL) {
        this.footprintPictureURL = footprintPictureURL;
    }

    public String getFootprintPictureBigURL() {
        return footprintPictureBigURL;
    }

    public void setFootprintPictureBigURL(String footprintPictureBigURL) {
        this.footprintPictureBigURL = footprintPictureBigURL;
    }

    public String getFootprintDescription() {
        return footprintDescription;
    }

    public void setFootprintDescription(String footprintDescription) {
        this.footprintDescription = footprintDescription;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (footprintID != null ? footprintID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Footprintprofile)) {
            return false;
        }
        Footprintprofile other = (Footprintprofile) object;
        if ((this.footprintID == null && other.footprintID != null) || (this.footprintID != null && !this.footprintID.equals(other.footprintID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Footprintprofile[footprintID=" + footprintID + "]";
    }

}
