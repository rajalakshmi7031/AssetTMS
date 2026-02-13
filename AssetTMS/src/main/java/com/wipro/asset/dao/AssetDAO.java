package com.wipro.asset.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wipro.asset.bean.AssetBean;
import com.wipro.asset.util.DBUtil;

public class AssetDAO {

    public String createRecord(AssetBean bean) {

        String status = "FAIL";

        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO ASSET_TB VALUES(?,?,?,?,?,?,?)");

            ps.setString(1, bean.getAssetId());
            ps.setString(2, bean.getAssetName());
            ps.setString(3, bean.getAssetCode());
            ps.setDate(4, new java.sql.Date(bean.getPurchaseDate().getTime()));
            ps.setString(5, bean.getCondition());
            ps.setString(6, bean.getDepartment());
            ps.setString(7, bean.getRemarks());

            int n = ps.executeUpdate();

            if (n > 0)
                status = bean.getAssetId();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    public AssetBean fetchRecord(String assetCode, Date purchaseDate) {

        AssetBean bean = null;

        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM ASSET_TB WHERE ASSETCODE=? AND PURCHASEDATE=?");

            ps.setString(1, assetCode);
            ps.setDate(2, new java.sql.Date(purchaseDate.getTime()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                bean = new AssetBean();
                bean.setAssetId(rs.getString(1));
                bean.setAssetName(rs.getString(2));
                bean.setAssetCode(rs.getString(3));
                bean.setPurchaseDate(rs.getDate(4));
                bean.setCondition(rs.getString(5));
                bean.setDepartment(rs.getString(6));
                bean.setRemarks(rs.getString(7));
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bean;
    }

    public boolean recordExists(String assetCode, Date purchaseDate) {

        return fetchRecord(assetCode, purchaseDate) != null;
    }

    public String generateAssetID(String assetCode, Date purchaseDate) {

        String id = "";

        try {
            Connection con = DBUtil.getDBConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT ASSET_SEQ.NEXTVAL FROM DUAL");

            if (rs.next()) {
                int seq = rs.getInt(1);

                SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
                String datePart = f.format(purchaseDate);

                id = datePart + assetCode.substring(0, 2).toUpperCase() + seq;
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    public List<AssetBean> fetchAllRecords() {

        List<AssetBean> list = new ArrayList<>();

        try {
            Connection con = DBUtil.getDBConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ASSET_TB");

            while (rs.next()) {
                AssetBean bean = new AssetBean();
                bean.setAssetId(rs.getString(1));
                bean.setAssetName(rs.getString(2));
                bean.setAssetCode(rs.getString(3));
                bean.setPurchaseDate(rs.getDate(4));
                bean.setCondition(rs.getString(5));
                bean.setDepartment(rs.getString(6));
                bean.setRemarks(rs.getString(7));
                list.add(bean);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
