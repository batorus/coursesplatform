package com.batorus.coursesplatform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 250)
    @Column(unique = true)
    private String courseName;

    @Size(max = 250)
    @Column(unique = true)
    private String courseCode;

    @Size(max = 250)
    private String courseDescription;

    private float coursePrice;

    @Column(columnDefinition = "integer default 1")
    private int enabled = 1;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

    //    private Integer rating;
//    private String author;
    // private String tags;
    // private String test;
    // private String chapters;
    // private String videos;
    // private String image;
    // private String enrolls;

    public Course() {
    }

    public Course(String courseName, String courseCode, String courseDescription, float coursePrice, int enabled) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseDescription = courseDescription;
        this.coursePrice = coursePrice;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public float getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(float coursePrice) {
        this.coursePrice = coursePrice;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }


}
