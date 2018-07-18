package com.pkg.Handler;

import com.pkg.Enum.ServiceType;
import com.pkg.Model.BaseResponse;
import com.pkg.Model.PackageData;
import com.pkg.Model.PkgResponse;

import org.springframework.stereotype.Component;

/**
 * Created by via on 7/18/18.
 */
@Component
public class ElkServiceHandler extends PkgServiceHandler {

    {
        this.serviceType = ServiceType.ELK;
    }

}
