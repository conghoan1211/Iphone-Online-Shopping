/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;
import java.text.SimpleDateFormat;
    import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author admin
 */
public class Test1 {

    private static String get(String[] cate, String[] origin, String query, String other) {
        if (origin != null && other == null) {
            query += "WHERE (";

            for (int i = 0; i < origin.length; i++) {
                query += "origin = N'" + origin[i] + "'";
                if (i < origin.length - 1) {
                    query += " or ";
                }
            }
            query += ")";
        } else if (origin == null && other != null) {
            query += "where origin != N'Hà Nội' and origin != N'TP.Hồ Chí Minh' and origin != N'Hưng Yên' and origin != N'Thái Bình'";
        } else if (origin != null && other != null) {
            query += "all";
        }

        if (cate != null) {
            if (origin != null || other != null) {
                query += " and ";
            } else {
                query += "WHERE ";
            }
            if (cate.length == 1) {
                for (String cate1 : cate) {
                    if (cate1.equals("phone")) {
                        query += "category_id in (15, 14, 13, 12, 11)";
                    }
                    if (cate1.equals("assesory")) {
                        query += "category_id in (10, 9, 8)";
                    }
                }
            } else if (cate.length > 1) {
                query += " category_id in (15, 14, 13, 12, 11, 10,9,8)";
            }
        }
        return query;
    }

//    public static void main(String[] args) {
//        String query = "insert into ";
//        String[] origin = {"hoan", "rose", "lisa"};
//        String[] cate = {"phone"};
//        String wq = get(cate, null, query, null);
//        System.out.println(wq);
//    }



    public static int calculateAge(Date birthDate) {
        LocalDate birthLocalDate = LocalDate.of(birthDate.getYear() , birthDate.getMonth() , birthDate.getDate());
        LocalDate currentDate = LocalDate.now();
        
        Period period = Period.between(birthLocalDate, currentDate);
        return period.getYears();
    }
    
    public static void main(String[] args) {
     
         // Lấy ngày giờ hiện tại
        Date currentDate = new Date();

        // Định dạng ngày giờ theo ý muốn
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = formatter.format(currentDate);

        // In ra màn hình
        System.out.println("Ngày giờ hiện tại: " + formattedDateTime);
    }
}


