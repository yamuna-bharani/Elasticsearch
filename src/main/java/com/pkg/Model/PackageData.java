package com.pkg.Model;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Data

@Document(indexName = "elasticsearch", type = "package")
public class PackageData {
    @Id
    private String name;
    private String city;
    private String description;
    private String address;
}