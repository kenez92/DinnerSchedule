package pl.kenez.communication.schedule;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ScheduleDto implements Serializable {
    @NotEmpty
    private String mailTo;

    @NotNull
    private Integer dinnerQuantity;

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public Integer getDinnerQuantity() {
        return dinnerQuantity;
    }

    public void setDinnerQuantity(Integer dinnerQuantity) {
        this.dinnerQuantity = dinnerQuantity;
    }

    public ScheduleDto mailTo(String mailTo) {
        this.mailTo = mailTo;
        return this;
    }

    public ScheduleDto dinnerQuantity(Integer dinnerQuantity) {
        this.dinnerQuantity = dinnerQuantity;
        return this;
    }
}
