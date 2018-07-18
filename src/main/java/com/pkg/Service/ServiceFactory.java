package com.pkg.Service;

import com.pkg.Service.PackageElkServiceImpl;
import com.pkg.Service.PackageService;
import com.pkg.Enum.ServiceType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by via on 7/18/18.
 */
@Service
public class ServiceFactory {
    @Autowired
    PackageElkServiceImpl packageElkService;

    public PackageService getService(ServiceType type) {
        switch (type) {
            case ELK:
                return packageElkService;
            default:
                return null;
        }
    }

}
