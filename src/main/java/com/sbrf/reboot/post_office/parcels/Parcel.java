package com.sbrf.reboot.post_office.parcels;

import lombok.Data;

import java.math.BigDecimal;

@Data
public abstract class Parcel {

    private BigDecimal carriageCost = new BigDecimal(5000);

}
