package com.pkg.Handler;

import com.pkg.Enum.ServiceType;
import com.pkg.Model.BaseResponse;
import com.pkg.Model.PackageData;
import com.pkg.Model.PkgResponse;
import com.pkg.Model.ServiceFactory;
import com.pkg.Service.PackageService;
import com.pkg.Utils.ApplicationContextProvider;
import com.pkg.Utils.ExecutorUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import lombok.Data;

/**
 * Created by via on 7/18/18.
 */
@Component
@Data
public class PkgServiceHandler {
    @Autowired
    private ServiceFactory serviceFactory ;



    protected ServiceType serviceType = ServiceType.ELK;
    private PackageService pkgService;

    public PkgResponse save(PackageData pkgData) {

        this.setPkgService(serviceFactory.getService(serviceType));

        PkgResponse pkgResponse = new PkgResponse();

        List<Future<PackageData>> pkgDataList = new ArrayList<>();

        FutureTask<PackageData> futureTask = new FutureTask<PackageData>(new Callable<PackageData>() {
            public PackageData call() {
                try {
                    return pkgService.save(pkgData);
                } catch (Exception e) {
                    return null;
                }
            }
        });
        pkgDataList.add((Future<PackageData>) ExecutorUtil.getThreadPool().submit(futureTask));

/*
        try {
            ExecutorUtil.getThreadPool().submit(() -> {
                pkgService.save(pkgData);
            });
        } catch (Exception e) {
            pkgResponse.setSuccess(false);
            pkgResponse.setError(new Error(e.getMessage()));
        }
*/

        try {
            pkgResponse.setPackageDatas(Arrays.asList(pkgDataList.get(0).get()));
        } catch (InterruptedException e) {
            pkgResponse.setSuccess(false);
            pkgResponse.setError(new Error(e.getMessage()));
        } catch (ExecutionException e) {
            pkgResponse.setSuccess(false);
            pkgResponse.setError(new Error(e.getMessage()));
        }

        pkgResponse.setSuccess(true);
        pkgResponse.setMessage("Your data has been stored");
        return pkgResponse;
    }

    public PkgResponse findByCity(String city) {
        this.setPkgService(serviceFactory.getService(serviceType));

        PkgResponse pkgResponse = new PkgResponse();
        List<PackageData> packageDatas = null;

        try{
            packageDatas = pkgService.findByCity(city);
        } catch (Exception e) {
            pkgResponse.setSuccess(false);
            pkgResponse.setError(new Error(e.getMessage()));
        }

        if(Objects.isNull(packageDatas) || packageDatas.size()==0) {
            pkgResponse.setMessage("No such package data stored");
        } else {
            pkgResponse.setSuccess(true);
            pkgResponse.setPackageDatas(packageDatas);

        }
        return pkgResponse;
    }
}
