package com.pkg.Model;

import java.util.List;

import lombok.Data;

/**
 * Created by via on 7/18/18.
 */
@Data
public class PkgResponse extends BaseResponse {
    private List<PackageData> packageDatas;
}
