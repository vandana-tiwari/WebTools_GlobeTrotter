package com.webToolsProj.TripOrg.POJO;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="categorytable")
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="categoryid",unique=true,nullable=false)
    private long id;
	
	@Column(name="title")
    private String title;
    
	//One cat has many adverts..so extra is in many i.e cat and hene join there and mapped by here
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "category")
	private Set<AdvertisementApartment> advertisements = new HashSet<AdvertisementApartment>();

    public Category(String title) {
        this.title = title;
        this.advertisements = new HashSet<AdvertisementApartment>();
    }

    Category() {
    }

    public Set<AdvertisementApartment> getAdvertisements() {
        return advertisements;
    }

    void setAdvertisements(Set<AdvertisementApartment> advertisements) {
        this.advertisements = advertisements;
    }

    public void addAdvertisement(AdvertisementApartment advertisements) {
        getAdvertisements().add(advertisements);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
