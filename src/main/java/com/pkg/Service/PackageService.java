package com.pkg.Service;

import com.pkg.Model.PackageData;

import java.util.List;

/**
 * Created by via on 7/16/18.
 */


public interface PackageService {

    PackageData save(PackageData pkgData);

    List<PackageData> findByCity(String PackageData);
}
