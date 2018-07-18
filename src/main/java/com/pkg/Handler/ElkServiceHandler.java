package com.pkg.Handler;

import com.pkg.Enum.ServiceType;
import com.pkg.Model.BaseResponse;
import com.pkg.Model.PackageData;
import com.pkg.Model.PkgResponse;
import com.pkg.Model.ServiceFactory;
import com.pkg.Service.PackageService;
import com.pkg.Utils.ExecutorUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Data;

/**
 * Created by via on 7/18/18.
 */
@Component
@Data
public class ElkServiceHandler {
    @Autowired
    private ServiceFactory serviceFactory;
    protected ServiceType serviceType = ServiceType.ELK;


    public BaseResponse save(PackageData pkgData) {

        BaseResponse baseResponse = new BaseResponse();

        //pre-check
        /*if(Objects.isNull(pkgService)) {
            baseResponse.setError(new Error("invalid service type"));
        }*/

        try {
            ExecutorUtil.getThreadPool().submit(() -> {
                serviceFactory.getService(serviceType).save(pkgData);
            });
        } catch (Exception e) {
            baseResponse.setSuccess(false);
            baseResponse.setError(new Error(e.getMessage()));
        }

        baseResponse.setSuccess(true);
        baseResponse.setMessage("Your data has been stored");
        return baseResponse;
    }

    public PkgResponse findByCity(String city) {

        PkgResponse pkgResponse = new PkgResponse();
        List<PackageData> packageDataList= new ArrayList<>();

        try {
            packageDataList = serviceFactory.getService(serviceType).findByCity(city);
        } catch (Exception e) {
            pkgResponse.setSuccess(false);
            pkgResponse.setError(new Error(e.getMessage()));
        }

        //post-check
        if (Objects.isNull(packageDataList) || packageDataList.size() == 0) {
            pkgResponse.setMessage("No such package data stored");
        } else {
            pkgResponse.setSuccess(true);
            pkgResponse.setPackageDatas(packageDataList);

        }
        return pkgResponse;
    }
}
