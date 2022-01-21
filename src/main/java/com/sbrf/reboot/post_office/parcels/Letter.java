package com.sbrf.reboot.post_office.parcels;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class Letter extends Parcel {

    private BigDecimal carriageCost = new BigDecimal(100);

}
