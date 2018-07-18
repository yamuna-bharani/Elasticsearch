package com.pkg.Service;



import com.pkg.Model.PackageData;
import com.pkg.Repository.PackageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by via on 7/16/18.
 */

@Service
public class PackageElkServiceImpl implements PackageService{

    @Autowired
    private PackageRepository packageRepository ;

    public void setPackageRepository(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    @Override
    public void save(PackageData pkgData) {
        packageRepository.save(pkgData);
    }

    @Override
    public List<PackageData> findByCity(String city) {
        return packageRepository.findByCity(city);
    }
}
