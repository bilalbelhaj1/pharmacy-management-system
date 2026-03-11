package view.pages;

import model.SaleItem;

import javax.swing.*;
import java.util.List;

/**
 * @author $(bilal belhaj)
 **/
public class PurchaseView extends SaleView{

    public PurchaseView(JPanel purchasePanel, List<SaleItem> items ) {
        super(purchasePanel, items);
    }
}
