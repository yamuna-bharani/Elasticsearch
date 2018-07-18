package com.pkg.Repository;

import com.pkg.Model.PackageData;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by via on 7/16/18.
 */
@Repository
public interface PackageRepository extends ElasticsearchRepository<PackageData, String> {

    List<PackageData> findByCity(String city);
}
