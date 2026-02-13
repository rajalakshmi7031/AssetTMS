package com.wipro.asset.service;

import java.util.Date;
import java.util.List;

import com.wipro.asset.bean.AssetBean;
import com.wipro.asset.dao.AssetDAO;
import com.wipro.asset.util.InvalidInputException;

public class Administrator {

    AssetDAO dao = new AssetDAO();

    public String addRecord(AssetBean bean) {

        try {

            if (bean == null || bean.getAssetName() == null ||
                    bean.getAssetCode() == null ||
                    bean.getPurchaseDate() == null ||
                    bean.getCondition() == null)
                throw new InvalidInputException();

            if (bean.getAssetName().length() < 2)
                return "INVALID ASSET NAME";

            if (bean.getAssetCode().length() < 2)
                return "INVALID ASSET CODE";

            if (bean.getDepartment().length() < 2)
                return "INVALID DEPARTMENT";

            if (bean.getCondition().length() < 2)
                return "INVALID CONDITION";

            if (dao.recordExists(bean.getAssetCode(), bean.getPurchaseDate()))
                return "ALREADY EXISTS";

            String id = dao.generateAssetID(bean.getAssetCode(),
                    bean.getPurchaseDate());

            bean.setAssetId(id);

            return dao.createRecord(bean);

        } catch (InvalidInputException e) {
            return "INVALID INPUT";
        }
    }

    public AssetBean viewRecord(String assetCode, Date purchaseDate) {
        return dao.fetchRecord(assetCode, purchaseDate);
    }

    public List<AssetBean> viewAllRecords() {
        return dao.fetchAllRecords();
    }
}
