package com.sbrf.reboot.post_office;

import com.sbrf.reboot.post_office.parcels.Parcel;
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
        return parcel.getCarriageCost();
    }
}
