package com.kshired.url.urlshortener.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@NoArgsConstructor
@ToString(of = {"id", "url", "converted"})
@SequenceGenerator(name = "MY_SEQ_GENERATOR", sequenceName = "MY_SEQ", initialValue = 100000000, allocationSize = 1)
public class Url {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MY_SEQ_GENERATOR")
    private Long id;
    private String url;
    private String converted;

    @CreatedDate
    @Column(updatable = false)
    private LocalDate createdAt;


    public Url(String url) {
        this.url = url;
    }

    public void changeConvertedUrl(String converted) {
        this.converted = converted;
    }
}
