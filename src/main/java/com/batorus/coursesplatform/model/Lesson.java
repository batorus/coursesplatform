package com.batorus.coursesplatform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "lessonName may not be null!")
    @Size(min = 2, max = 100, message = "lessonName must be between {min} and {max} characters long")
    @Column(unique = true)
    private String lessonName;

    @NotNull(message = "lessonDescription may not be null!")
    @Size(min = 2, max = 250, message = "lessonDescription must be between {min} and {max} characters long")
    private String lessonDescription;

    @NotNull(message = "lessonContent not be null!")
    @Size(min = 3, message = "lessonContent must be at least  {min} characters long")
    private String lessonContent;


    @Column(columnDefinition = "integer default 1")
    private int enabled = 1;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Lesson() {
    }

    public Lesson(String lessonName, String lessonDescription, String lessonContent) {
        this.lessonName = lessonName;
        this.lessonDescription = lessonDescription;
        this.lessonContent = lessonContent;
    }

    public Long getId() {
        return id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonDescription() {
        return lessonDescription;
    }

    public void setLessonDescription(String lessonDescription) {
        this.lessonDescription = lessonDescription;
    }

    public String getLessonContent() {
        return lessonContent;
    }

    public void setLessonContent(String lessonContent) {
        this.lessonContent = lessonContent;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", lessonName='" + lessonName + '\'' +
                ", lessonDescription='" + lessonDescription + '\'' +
                ", lessonContent='" + lessonContent + '\'' +
                ", enabled=" + enabled +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
}
