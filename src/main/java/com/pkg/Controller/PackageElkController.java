package com.pkg.Controller;


import com.pkg.Handler.ElkServiceHandler;
import com.pkg.Model.BaseResponse;
import com.pkg.Model.PackageData;
import com.pkg.Model.PkgResponse;
import com.pkg.Utils.URIConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class PackageElkController {

    @Autowired
    ElkServiceHandler serviceHandler;

    @PostMapping(URIConstants.STORE)
    public BaseResponse storePackageData(@RequestBody PackageData pkgData) {
        return serviceHandler.save(pkgData);
    }

    @GetMapping(URIConstants.DETAIL)
    public PkgResponse getPackages(@PathVariable String city) {
        return serviceHandler.findByCity(city);
    }

}