package com.example.emailTemplateManagement.infrastructure.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMAIL")
public class EmailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String from;

    @ElementCollection
    @CollectionTable(name = "email_recipients", joinColumns = @JoinColumn(name = "email_id"))
    @Column(name = "recipient")
    private List<String> to;

    @ElementCollection
    @CollectionTable(name = "email_cc", joinColumns = @JoinColumn(name = "email_id"))
    @Column(name = "cc_recipient")
    private List<String> cc;

    @ElementCollection
    @CollectionTable(name = "email_bcc", joinColumns = @JoinColumn(name = "email_id"))
    @Column(name = "bcc_recipient")
    private List<String> bcc;

    @Column(nullable = false)
    private String subject;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String body; // Stores HTML content


    @Column(name = "template_name")
    private String templateName;

    @ElementCollection
    @CollectionTable(name = "email_template_model", joinColumns = @JoinColumn(name = "email_id"))
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    private Map<String, Object> templateModel;

    /**
     * Converts the HTML body to plain text.
     *
     * @return Plain text representation of the HTML body.
     */
    public String getPlainTextBody() {
        return body != null ? Jsoup.parse(body).text() : null;
    }


}
