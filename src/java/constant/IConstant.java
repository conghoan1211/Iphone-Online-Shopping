/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constant;

/**
 *
 * @author admin
 */
public interface IConstant {

    public static String PAGINATION = "15";

    public static int IS_RETURN_OR_REFUND = -4;   // trả hàng\hoàn tiền delivered = -4
    public static int IS_DELIVERED_NO_SUCCESS = -3;
    public static int IS_NO_ACCEPTED_BY_ADMIN = -2;
    public static int IS_CANCELED = -1;             // đã hủy đơn accepted = -1
    public static int DEFAUT = 0;                   // đang giao hàng deliver = 0
    public static int IS_ACCEPTED = 1;            // đã xác nhận accepted = 1
    public static int IS_DELIVERED = 2;           // đã giao hàng deliver = 2
    public static int IS_FEEDBACKED = 3;          // đã feedback feedback = 3
    public static int IS_PURCHASED = 4;           // Đã thanh toán deliver = 4

}
