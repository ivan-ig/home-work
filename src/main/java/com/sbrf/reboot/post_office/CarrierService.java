package com.sbrf.reboot.post_office;

import com.sbrf.reboot.post_office.parcels.BookPost;
import com.sbrf.reboot.post_office.parcels.Letter;
import com.sbrf.reboot.post_office.parcels.Parcel;
import com.sbrf.reboot.post_office.parcels.PostalPackage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class CarrierService<T extends Parcel> {

    @NonNull
    private List<T> parcels;

    public BigDecimal getSummarizeCarriageCost() {
        return parcels.stream()
                .reduce(BigDecimal.ZERO, (x, y) -> x.add(getCarriageCost(y)), BigDecimal::add);
    }

    private BigDecimal getCarriageCost(T parcel) {
        BigDecimal carriageCost = new BigDecimal(5000);

        if (parcel instanceof Letter) {
            carriageCost = new BigDecimal(100);
        }
        if (parcel instanceof BookPost) {
            carriageCost = new BigDecimal(500);
        }
        if (parcel instanceof PostalPackage) {
            carriageCost = new BigDecimal(1500);
        }
        return carriageCost;
    }
}
