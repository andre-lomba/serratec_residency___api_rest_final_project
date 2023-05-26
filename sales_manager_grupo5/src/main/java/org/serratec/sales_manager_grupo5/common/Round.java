package org.serratec.sales_manager_grupo5.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Round {

    public static final Double round(Double value, int places) {
        if (value == null || value == 0.0 || value.isNaN())
            return 0.0;
        if (places < 0)
            throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
