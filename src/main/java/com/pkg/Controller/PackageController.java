package com.pkg.Controller;


import com.pkg.ExecutorUtil;
import com.pkg.Model.PackageData;
import com.pkg.Service.PackageService;
import com.pkg.Utils.URIConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PackageController {

    @Autowired
    PackageService pkgService;

    @PostMapping(URIConstants.STORE)
    public void storePackageData(@RequestBody PackageData pkgData) {
        ExecutorUtil.getThreadPool().submit(() -> {
            pkgService.save(pkgData);
        });

    }

    @GetMapping(URIConstants.DETAIL)
    public List<PackageData> getPackages(@PathVariable String city) {
        return pkgService.findByCity(city);
    }

}