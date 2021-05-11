package com.batorus.coursesplatform.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull(message = "title may not be null!")
    //@NotBlank(message = "title may not be empty!")
    @Size(min = 2, max = 100, message = "title must be between {min} and {max} characters long")
    @Column(unique = true)
    private String title;

    @NotNull(message = "description may not be null!")
    //@NotBlank(message = "description may not be empty!")
    @Size(min = 2, max = 250, message = "description must be between {min} and {max} characters long")
    private String description;

    public Category() {}

    public Category(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
