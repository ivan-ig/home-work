package com.sbrf.reboot.post_office;

import com.sbrf.reboot.post_office.parcels.BookPost;
import com.sbrf.reboot.post_office.parcels.Letter;
import com.sbrf.reboot.post_office.parcels.Parcel;
import com.sbrf.reboot.post_office.parcels.PostalPackage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class Carrier<T extends Parcel> {

    @NonNull
    private final T parcel;

    public BigDecimal getShipmentCost() {
        BigDecimal shipmentCost = new BigDecimal(5000);

        if (parcel instanceof Letter) {
            shipmentCost = new BigDecimal(100);
        }
        if (parcel instanceof BookPost) {
            shipmentCost = new BigDecimal(500);
        }
        if (parcel instanceof PostalPackage) {
            shipmentCost = new BigDecimal(1500);
        }
        return shipmentCost;
    }
}
