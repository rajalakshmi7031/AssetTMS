package com.wipro.asset.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import com.wipro.asset.bean.AssetBean;
import com.wipro.asset.service.Administrator;

public class MainServlet extends HttpServlet {

    Administrator admin = new Administrator();

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");

        try {

            if (operation.equals("newRecord")) {

                AssetBean bean = new AssetBean();

                bean.setAssetName(request.getParameter("assetName"));
                bean.setAssetCode(request.getParameter("assetCode"));

                SimpleDateFormat f =
                        new SimpleDateFormat("yyyy-MM-dd");
                Date date = f.parse(request.getParameter("purchaseDate"));

                bean.setPurchaseDate( date);
                bean.setCondition(request.getParameter("condition"));
                bean.setDepartment(request.getParameter("department"));
                bean.setRemarks(request.getParameter("remarks"));
                String result = admin.addRecord(bean);

                if (result.equals("FAIL") ||
                    result.equals("INVALID INPUT") ||
                    result.equals("INVALID ASSET NAME") ||
                    result.equals("INVALID ASSET CODE") ||
                    result.equals("INVALID DEPARTMENT") ||
                    result.equals("INVALID CONDITION") ||
                    result.equals("ALREADY EXISTS"))
                {
                    response.sendRedirect("error.html");
                }
                else
                {
                    response.sendRedirect("success.html");
                }

            }

            else if (operation.equals("viewRecord")) {

                String code = request.getParameter("assetCode");

                SimpleDateFormat f =
                        new SimpleDateFormat("yyyy-MM-dd");
                Date date = f.parse(request.getParameter("purchaseDate"));

                AssetBean bean = admin.viewRecord(code, date);

                request.setAttribute("bean", bean);
                RequestDispatcher rd =
                        request.getRequestDispatcher("displayAsset.jsp");
                rd.forward(request, response);
            }

            else if (operation.equals("viewAllRecords")) {

                List<AssetBean> list =
                        admin.viewAllRecords();

                request.setAttribute("list", list);
                RequestDispatcher rd =
                        request.getRequestDispatcher("displayAllAssets.jsp");
                rd.forward(request, response);
            }

        } catch (Exception e) {
            response.sendRedirect("error.html");
        }
    }
}
