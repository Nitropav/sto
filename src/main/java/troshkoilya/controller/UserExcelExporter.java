package troshkoilya.controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import troshkoilya.model.Order;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private List<Order> orders;

    public UserExcelExporter(List<Order> orders) {
        this.orders = orders;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Orders");
    }

    private void writeHeaderRow(){
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Order ID");
        Cell cell1 = row.createCell(1);
        cell1.setCellValue("Time Start");
        Cell cell2 = row.createCell(2);
        cell2.setCellValue("Time Finish");
        Cell cell3 = row.createCell(3);
        cell3.setCellValue("Amount");
        Cell cell4 = row.createCell(4);
        cell4.setCellValue("Status");
    }

    private void writeDataRows(){
        int rowsCount = 1;
        for (Order order : orders){
            Row row = sheet.createRow(rowsCount);
            Cell cell = row.createCell(0);
            cell.setCellValue(order.getIdorder());
            Cell cell1 = row.createCell(1);
            cell1.setCellValue(order.getTimestart());
            Cell cell2 = row.createCell(2);
            cell2.setCellValue(order.getTimefinish());
            Cell cell3 = row.createCell(3);
            cell3.setCellValue(order.getAmount());
            Cell cell4 = row.createCell(4);
            cell4.setCellValue(order.getStatus());

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderRow();
        writeDataRows();

        ServletOutputStream servletOutputStream = response.getOutputStream();
        workbook.write(servletOutputStream);
        workbook.close();
        servletOutputStream.close();
    }
}
