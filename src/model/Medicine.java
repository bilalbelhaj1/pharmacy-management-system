package model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author $(bilal belhaj)
 **/
public record Medicine(int id, String name, BigDecimal sellePrice, BigDecimal purchasePrice , String description, String form, int stock, LocalDate expiration_date) {
}
